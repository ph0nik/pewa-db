package com.pewa.anime;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import com.eclipsesource.json.ParseException;
import com.google.gson.Gson;
import com.pewa.*;
import com.pewa.anime.anilist.Media;
import com.pewa.anime.anilist.ResponseAnime;
import com.pewa.common.Genre;
import com.pewa.common.Person;
import com.pewa.config.ConfigFactory;
import com.pewa.util.SaveImage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.pewa.anime.Session.updateSession;

@Component
public class AnimeParser implements MediaParse<Anime, Integer> {

    private static final Logger log = LogManager.getLogger(AnimeParser.class);
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final String nullDate = "10000101";

    /*
    * Method takes AniList model id and returns object of type Anime
    * */
    @Deprecated
    public Anime getItem2(Integer aniListId) {
        AnimeAccessToken animeAccessToken = updateSession();

        Anime anime = new Anime();
        String url = new StringBuilder(ConfigFactory.get("search.aniListApiEndpoint"))
                        .append(ConfigFactory.get("item.aniListAnimeItem"))
                        .append(aniListId)
                        .append(ConfigFactory.get("item.aniListCharacters"))
                        .toString();
        log.info(url); // logging url
        Connection.Response getSingleItem = null;
        try {
            getSingleItem = Jsoup.connect(url)
                    .data("access_token", animeAccessToken.getAccessToken())
                    .userAgent(ConfigFactory.get("search.userAgent"))
                    .timeout(5 * 1000)
                    .ignoreContentType(true)
                    .ignoreHttpErrors(true)
                    .execute();
            if (getSingleItem.statusCode() != 200) {
                String errorMsg = new StringBuilder("Status code: ")
                        .append(getSingleItem.statusCode())
                        .append(", Status msg: ")
                        .append(getSingleItem.statusMessage())
                        .toString();
                log.error(errorMsg);
            } else {
                anime = parseItemToObject(getSingleItem, anime);
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } catch (ParseException e) {
            log.error(e.getMessage(), url, e);
        }
        return anime;
    }

    /*
    * Method takes Connection.Response object and returns Anime type object.
    * It's called only when server response code is 200.
    * */
    @Deprecated
    private Anime parseItemToObject(Connection.Response cr, Anime anime) throws IOException, ParseException {
        JsonObject jsonAnime;
        jsonAnime = Json.parse(cr.parse().text()).asObject();
        log.info(jsonAnime); // logging parsed object
        anime.setIdAnilist(jsonAnime.get("id").asInt());
        anime.setPoster(jsonAnime.get("image_url_lge").asString());
        String intPoster = SaveImage.getImage(anime);
        anime.setIntPoster(intPoster);
        anime.setDescription(jsonAnime.get("description").asString());
        LocalDate zdtEnd;
        LocalDate zdtStart;
        {
            JsonValue startDateJson = jsonAnime.get("start_date_fuzzy");
            JsonValue endDateJson = jsonAnime.get("end_date_fuzzy");
            String fuzzyDateStart = (startDateJson.isNull()) ? nullDate : startDateJson.toString();
            String fuzzyDateEnd = (endDateJson.isNull()) ? nullDate : endDateJson.toString();
            zdtStart = LocalDate.parse(fuzzyDateStart, formatter);
            zdtEnd = LocalDate.parse(fuzzyDateEnd, formatter);
            if (zdtStart.isAfter(zdtEnd)) {
                zdtEnd = zdtStart;
            }
        }
        anime.setStartDate(zdtStart);
        anime.setEndDate(zdtEnd);
        anime.setAnimeType(jsonAnime.get("type").asString());
        anime.setType(PewaType.ANIME);
        for (JsonValue val : jsonAnime.get("genres").asArray()) {
            anime.setGenres(new Genre(val.asString()));
        }
        anime.setTitleEng(jsonAnime.get("title_english").asString());
        anime.setTitleRom(jsonAnime.get("title_romaji").asString());

        for (JsonValue val : jsonAnime.get("staff").asArray()) {
            String firstName = val.asObject().getString("name_first", "");
            String lastName = val.asObject().getString("name_last", "");
            String role = val.asObject().getString("role", "");
            anime.setStaff(new Person(firstName, lastName, role));
        }
        anime.setEps(jsonAnime.get("total_episodes").asInt());
        if (jsonAnime.get("duration").isNull()) {
            anime.setDuration(0);
        } else {
            anime.setDuration(jsonAnime.getInt("duration", 0));
        }
        anime.setAiringStatus(jsonAnime.getString("airing_status", ""));
        return anime;
    }

    @Override
    public Anime getItem(Integer id) {
        Anime anime = new Anime();
        try {
            AnimeMangaQuery animeMangaQuery = setQueryObject(id);
            String s = sendRequest(animeMangaQuery);
            anime = parseResponse(s, new Anime());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return anime;
    }

    private Anime parseResponse(String responseObject, Anime anime) {
        Gson gson = new Gson();
        ResponseAnime responseAnime = gson.fromJson(responseObject, ResponseAnime.class);
        Media animeMedia = responseAnime.getData().getMedia();
        anime.setType(PewaType.ANIME);
        anime.setAiringStatus(animeMedia.getStatus());
        anime.setDuration(animeMedia.getDuration());
        anime.setTitleRom(animeMedia.getTitle().getRomaji());
        String eng = animeMedia.getTitle().getEnglish();
        if (eng == null || "".equals(eng)) {
            anime.setTitleEng(anime.getTitleRom());
        } else {
            anime.setTitleEng(eng);
        }
        animeMedia.getGenres().forEach(g -> anime.setGenres(new Genre(g)));
        animeMedia.getStaff().getEdges().forEach(s ->
                anime.setStaff(new Person(
                        s.getNode().getName().getFirst(),
                        s.getNode().getName().getLast(),
                        s.getRole().toLowerCase())));
        animeMedia.getCharacters().getEdges().forEach(ch ->
                ch.getVoiceActors().forEach(va ->
                        anime.setStaff(new Person(
                                va.getName().getFirst(),
                                va.getName().getLast(),
                                "actor")))
                );
        anime.setAnimeType(animeMedia.getType());
        anime.setDescription(animeMedia.getDescription());
        anime.setEps(animeMedia.getEpisodes());
        String startDate = animeMedia.getStartDate().getYear().toString()
                + String.format("%02d", animeMedia.getStartDate().getMonth())
                + String.format("%02d", animeMedia.getStartDate().getDay());
        anime.setStartDate(LocalDate.parse(startDate, formatter));
        String endDate = animeMedia.getEndDate().getYear().toString()
                + String.format("%02d", animeMedia.getEndDate().getMonth())
                + String.format("%02d", animeMedia.getEndDate().getDay());
        anime.setEndDate(LocalDate.parse(endDate, formatter));
        anime.setIdAnilist(animeMedia.getId());
        anime.setPoster(animeMedia.getCoverImage().getLarge());
        String intPoster = SaveImage.getImage(anime);
        anime.setIntPoster(intPoster);
        return anime;
    }

    private String sendRequest(AnimeMangaQuery request) throws IOException {
        String url = "https://graphql.anilist.co";
        Document response = Jsoup.connect(url)
                .userAgent(ConfigFactory.get("search.userAgent"))
                .data("query", request.getQuery())
                .data("variables", request.getVariables())
                .timeout(5 * 1000)
                .ignoreContentType(true)
                .post();
        return response.text();
    }

    private AnimeMangaQuery setQueryObject(int id) throws IOException {
        String filePath = "src/main/resources/graphql/anime-object.graphql";
        String variables = "{\"id\":"+ id +",\"type\":\"ANIME\"}";
        StringBuilder query = new StringBuilder("");
        Files.readAllLines(Paths.get(filePath))
                .forEach(query::append);
        AnimeMangaQuery amq = new AnimeMangaQuery();
        amq.setVariables(variables);
        amq.setQuery(query.toString());
        return amq;
    }
}

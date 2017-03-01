package com.pewa.anime;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.TreeSet;

import static com.pewa.config.ConfigReader.*;
import static com.pewa.config.Session.updateSession;

public class AnimeParseToObject {
    private AnimeParseToObject() {
    }

    private static Anime anime;
    private static String url;
    private static AnimeAccessToken animeAccessToken;

    /*
    * Method takes AniList model id and returns object of type Anime
    * */
    public static Anime getAnimeItem(int aniListId) {
        animeAccessToken = updateSession();
        anime = new Anime();
        url = aniListApiEndpoint + aniListAnimeItem + aniListId;
        Connection.Response getSingleItem = null;
        try {
            getSingleItem = Jsoup.connect(url)
                    .data("access_token", animeAccessToken.getAccessToken())
                    .userAgent(userAgent)
                    .timeout(5 * 1000)
                    .ignoreContentType(true)
                    .ignoreHttpErrors(true)
                    .execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (getSingleItem.statusCode() != 200) {
            System.out.println(getSingleItem.statusCode());
            System.out.println(getSingleItem.statusMessage());
        } else {
            anime = parseItemToObject(getSingleItem, anime);
        }
        return anime;
    }

    /*
    * Method takes Connection.Response object and returns Anime type object.
    * It's called only when server response code is 200.
    * */
    private static Anime parseItemToObject(Connection.Response cr, Anime anime) {
        JsonObject jsonAnime = null;
        try {
            jsonAnime = Json.parse(cr.parse().text()).asObject();
            anime.setId(jsonAnime.get("id").asInt());
            anime.setPoster(jsonAnime.get("image_url_lge").asString());
            anime.setDescription(jsonAnime.get("description").asString());
            anime.setDuration(jsonAnime.getInt("duration", 0));
            JsonValue startDate = jsonAnime.get("start_date_fuzzy");
            JsonValue endDate = jsonAnime.get("end_date_fuzzy");
            LocalDate zdtStart;
            LocalDate zdtEnd;
            if (startDate.isNull()) {
                zdtStart = LocalDate.parse("0000-01-01");
                zdtEnd = LocalDate.parse("0000-01-01");
            } else {
                String fuzzyDateStart = jsonAnime.get("end_date_fuzzy").toString();
                String fuzzyDateEnd = jsonAnime.get("start_date_fuzzy").toString();
                if (fuzzyDateStart.length() == 4) {
                    fuzzyDateStart = fuzzyDateStart + "0101";
                    fuzzyDateEnd = fuzzyDateEnd + "0101";
                }
                zdtStart = LocalDate.parse(fuzzyDateStart, DateTimeFormatter.ofPattern("yyyyMMdd"));
                zdtEnd = LocalDate.parse(fuzzyDateEnd, DateTimeFormatter.ofPattern("yyyyMMdd"));
            }
            anime.setEndDate(zdtStart);
            anime.setStartDate(zdtEnd);
            anime.setEps(jsonAnime.get("total_episodes").asInt());
            anime.setType(jsonAnime.get("type").asString());
            Set<String> genres = new TreeSet<>();
            for (JsonValue val : jsonAnime.get("genres").asArray()) {
                genres.add(val.asString());
            }
            anime.setGenres(genres);
            anime.setTitleEng(jsonAnime.get("title_english").asString());
            anime.setTitleRom(jsonAnime.get("title_romaji").asString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return anime;
    }
}

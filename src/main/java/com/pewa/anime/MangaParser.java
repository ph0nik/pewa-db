package com.pewa.anime;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import com.eclipsesource.json.ParseException;
import com.pewa.Genre;
import com.pewa.MediaParse;
import com.pewa.Person;
import com.pewa.PewaType;
import com.pewa.util.SaveImage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.TreeSet;

import static com.pewa.config.ConfigReader.*;
import static com.pewa.config.Session.updateSession;

public class MangaParser implements MediaParse<Manga, Integer> {

    private static final Logger log = LogManager.getLogger(MangaParser.class);
    /*
    * Method takes AniList model id and returns object of type Anime
    * */
    public Manga getItem(Integer aniListId) {
        String url;
        Manga manga;
        AnimeAccessToken animeAccessToken = updateSession();
        manga = new Manga();
        url = new StringBuilder(aniListApiEndpoint)
                .append(aniListMangaItem)
                .append(aniListId)
                .append(aniListCharacters)
                .toString();
        Connection.Response getSingleItem = null;
        try {
            getSingleItem = Jsoup.connect(url)
                    .data("access_token", animeAccessToken.getAccessToken())
                    .userAgent(userAgent)
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
                manga = parseItemToObject(getSingleItem, manga);
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } catch (ParseException e) {
            log.error("Incorrect json structure @ " + url, e);
        }
        return manga;
    }

    /*
    * Method takes Connection.Response object and returns Manga type object.
    * It's called only when server response code is 200.
    * */
    private Manga parseItemToObject(Connection.Response cr, Manga manga) throws IOException {
        JsonObject jsonManga = Json.parse(cr.parse().text()).asObject();
        manga.setIdAnilist(jsonManga.get("id").asInt());
        manga.setPoster(jsonManga.get("image_url_lge").asString());
        SaveImage si = new SaveImage();
        String intPoster = si.getImage(manga);
        manga.setIntPoster(intPoster);
        manga.setDescription(jsonManga.get("description").asString());
        String fuzzyDateStart;
        String fuzzyDateEnd;
        JsonValue startDateJson = jsonManga.get("start_date_fuzzy");
        JsonValue endDateJson = jsonManga.get("end_date_fuzzy");
        fuzzyDateStart = startDateJson.toString(); // yyyyMMdd
        if (!endDateJson.isNull()) {
            fuzzyDateEnd = endDateJson.toString();
        } else {
            fuzzyDateEnd = "";
        }
        if (fuzzyDateStart.length() == 4) {
            fuzzyDateStart = fuzzyDateStart + "0101";
        }
        if (fuzzyDateEnd.length() == 4) {
            fuzzyDateEnd = fuzzyDateEnd + "0101";
        }
        LocalDate zdtEnd;
        LocalDate zdtStart = LocalDate.parse(fuzzyDateStart, DateTimeFormatter.ofPattern("yyyyMMdd"));
        if (fuzzyDateEnd.isEmpty()) {
            zdtEnd = LocalDate.now();
        } else {
            zdtEnd = LocalDate.parse(fuzzyDateEnd, DateTimeFormatter.ofPattern("yyyyMMdd"));
        }
        manga.setStartDate(zdtStart);
        manga.setEndDate(zdtEnd);
        manga.setType(jsonManga.get("type").asString());
        for (JsonValue val : jsonManga.get("genres").asArray()) {
            manga.setGenres(new Genre(val.asString()));
        }
        manga.setTitleEng(jsonManga.get("title_english").asString());
        manga.setTitleRom(jsonManga.get("title_romaji").asString());
        Set<Person> staff = new TreeSet<>();
        Person person;

        for (JsonValue val : jsonManga.get("staff").asArray()) {
            String firstName = val.asObject().getString("name_first", "");
            String lastName = val.asObject().getString("name_last", "");
            String role = val.asObject().getString("role", "");
            person = new Person(firstName, lastName, role);
            staff.add(person);
        }
        manga.setStaff(staff);
        manga.setTotalVolumes(jsonManga.getInt("total_volumes", 0));
        manga.setTotalChapters(jsonManga.getInt("total_chapters", 0));
        manga.setPublishingStatus(jsonManga.getString("publishing_status", ""));
        return manga;
    }
}

package com.pewa.anime;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import com.pewa.Person;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.TreeSet;

import static com.pewa.config.ConfigReader.*;
import static com.pewa.config.Session.updateSession;

public class MangaParseToObject {
    private MangaParseToObject() {
    }

    private static Manga manga;
    private static String url;
    private static AnimeAccessToken animeAccessToken;

    /*
    * Method takes AniList model id and returns object of type Anime
    * */
    public static Manga getMangaItem(int aniListId) {
        animeAccessToken = updateSession();
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (getSingleItem.statusCode() != 200) {
            System.out.println(getSingleItem.statusCode());
            System.out.println(getSingleItem.statusMessage());
        } else {
            manga = parseItemToObject(getSingleItem, manga);
        }
        return manga;
    }

    /*
    * Method takes Connection.Response object and returns Anime type object.
    * It's called only when server response code is 200.
    * */
    private static Manga parseItemToObject(Connection.Response cr, Manga manga) {
        JsonObject jsonManga;
        try {
            jsonManga = Json.parse(cr.parse().text()).asObject();
            manga.setId(jsonManga.get("id").asInt());
            manga.setPoster(jsonManga.get("image_url_lge").asString());
            manga.setDescription(jsonManga.get("description").asString());
            String fuzzyDateStart = "19000101";
            String fuzzyDateEnd = "19000101";
            if (!jsonManga.get("start_date_fuzzy").isNull()) {
                fuzzyDateStart = jsonManga.get("start_date_fuzzy").toString(); // yyyyMMdd
            }
            if (!jsonManga.get("end_date_fuzzy").isNull()) {
                fuzzyDateEnd = jsonManga.get("end_date_fuzzy").toString();
            }
            if (fuzzyDateStart.length() == 4) {
                fuzzyDateStart = fuzzyDateStart + "0101";
            }
            if (fuzzyDateEnd.length() == 4) {
                fuzzyDateEnd = fuzzyDateEnd + "0101";
            }
            LocalDate zdtStart = LocalDate.parse(fuzzyDateStart, DateTimeFormatter.ofPattern("yyyyMMdd"));
            LocalDate zdtEnd = LocalDate.parse(fuzzyDateEnd, DateTimeFormatter.ofPattern("yyyyMMdd"));
            manga.setEndDate(zdtStart);
            manga.setStartDate(zdtEnd);
            manga.setType(jsonManga.get("type").asString());
            Set<String> genres = new TreeSet<>();
            for (JsonValue val : jsonManga.get("genres").asArray()) {
                genres.add(val.asString());
            }
            manga.setGenres(genres);
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

            manga.setTotalVolumes(jsonManga.getInt("total_volumes",0));
            manga.setTotalChapters(jsonManga.getInt("total_chapters",0));
            manga.setPublishingStatus(jsonManga.getString("publishing_status",""));



        } catch (IOException e) {
            e.printStackTrace();
        }
        return manga;
    }

}

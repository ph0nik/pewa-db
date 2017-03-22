package com.pewa.anime;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import com.pewa.Genre;
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
        url = new StringBuilder(aniListApiEndpoint)
                .append(aniListAnimeItem)
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
            anime = parseItemToObject(getSingleItem, anime);
        }
        return anime;
    }

    /*
    * Method takes Connection.Response object and returns Anime type object.
    * It's called only when server response code is 200.
    * */
    private static Anime parseItemToObject(Connection.Response cr, Anime anime) {
        JsonObject jsonAnime;
        try {
            jsonAnime = Json.parse(cr.parse().text()).asObject();
            anime.setIdAnilist(jsonAnime.get("id").asInt());
            anime.setPoster(jsonAnime.get("image_url_lge").asString());
            anime.setDescription(jsonAnime.get("description").asString());
            String fuzzyDateStart = "19000101";
            String fuzzyDateEnd = "19000101";
            if (!jsonAnime.get("start_date_fuzzy").isNull()) {
                fuzzyDateStart = jsonAnime.get("start_date_fuzzy").toString(); // yyyyMMdd
            }
            if (!jsonAnime.get("end_date_fuzzy").isNull()) {
                fuzzyDateEnd = jsonAnime.get("end_date_fuzzy").toString();
            }
            if (fuzzyDateStart.length() == 4) {
                fuzzyDateStart = fuzzyDateStart + "0101";
            }
            if (fuzzyDateEnd.length() == 4) {
                fuzzyDateEnd = fuzzyDateEnd + "0101";
            }
            LocalDate zdtStart = LocalDate.parse(fuzzyDateStart, DateTimeFormatter.ofPattern("yyyyMMdd"));
            LocalDate zdtEnd = LocalDate.parse(fuzzyDateEnd, DateTimeFormatter.ofPattern("yyyyMMdd"));
            anime.setEndDate(zdtStart);
            anime.setStartDate(zdtEnd);
            anime.setType(jsonAnime.get("type").asString());
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
            anime.setDuration(jsonAnime.getInt("duration", 0));
            anime.setAiringStatus(jsonAnime.getString("airing_status", ""));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return anime;
    }
}

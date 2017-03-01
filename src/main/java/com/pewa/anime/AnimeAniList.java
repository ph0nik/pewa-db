package com.pewa.anime;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import com.pewa.SingleSearchResult;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.TreeSet;

import static com.pewa.config.ConfigReader.*;
import static com.pewa.config.Session.updateSession;

public class AnimeAniList {

    public static AnimeAccessToken aniListAuth() {
        AnimeAccessToken animeAccessToken = new AnimeAccessToken();
        String clientId = aniListClientId;
        String clientSecret = aniListClientSecret;
        String url = aniListApiEndpoint + aniListPostTokenReq;

        try {
            final String getToken = Jsoup.connect(url)
                    .data("grant_type", "client_credentials", "client_id", clientId, "client_secret", clientSecret)
                    .userAgent(userAgent)
                    .timeout(5 * 1000)
                    .ignoreContentType(true)
                    .post()
                    .text();
            JsonObject auth = Json.parse(getToken).asObject();
            animeAccessToken.setAccessToken(auth.get("access_token").asString());
            animeAccessToken.setTokenType(auth.get("token_type").asString());
            animeAccessToken.setExpireTime(auth.get("expires").asLong());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return animeAccessToken;
    }

    public static Set<SingleSearchResult> aniListSearchAnime(String query) {
        AnimeAccessToken animeAccessToken = updateSession();
        Set<SingleSearchResult> output = new TreeSet<>();
        String url = aniListApiEndpoint + aniListSearchAnime + query;

        try {
            final String getResults = Jsoup.connect(url)
                    .data("access_token", animeAccessToken.getAccessToken())
                    .userAgent(userAgent)
                    .timeout(5 * 1000)
                    .ignoreContentType(true)
                    .get()
                    .text();

            JsonArray searchResults = Json.parse(getResults).asArray();
            for (JsonValue member : searchResults) {
                SingleSearchResult singleSearchResult = new SingleSearchResult();
                String titleRom = member.asObject().get("title_romaji").asString();
                String titleEng = member.asObject().get("title_english").asString();
                String type = member.asObject().get("type").asString();
                int id = member.asObject().get("id").asInt();
                String poster = member.asObject().get("image_url_lge").asString();
                int fuzzyDateStart = member.asObject().get("start_date_fuzzy").asInt(); // YYYYDDMM
                int fuzzyDateEnd = member.asObject().get("end_date_fuzzy").asInt();
                String fuzzyDateStartString = Integer.toString(fuzzyDateStart);
                String fuzzyDateEndString = Integer.toString(fuzzyDateEnd);
                String year;
                if (fuzzyDateStartString.length() == 4) {
                    fuzzyDateStartString = fuzzyDateStartString + "0101";
                    fuzzyDateEndString = fuzzyDateEndString + "0101";
                }
                if (fuzzyDateStart == fuzzyDateEnd) {
                    LocalDate zdt = LocalDate.parse(fuzzyDateStartString, DateTimeFormatter.ofPattern("yyyyMMdd"));
                    year = Integer.toString(zdt.getYear());
                } else {
                    LocalDate zdtStart = LocalDate.parse(fuzzyDateStartString, DateTimeFormatter.ofPattern("yyyyMMdd"));
                    LocalDate zdtEnd = LocalDate.parse(fuzzyDateEndString, DateTimeFormatter.ofPattern("yyyyMMdd"));
                    year = "[" + zdtStart.getYear() + " - " + zdtEnd.getYear() + "]";
                }
                int eps = member.asObject().get("total_episodes").asInt();
                String duration = member.asObject().get("duration").toString();
                String desc = new StringBuilder(titleRom)
                        .append(" (")
                        .append(titleEng)
                        .append(") ")
                        .append(type)
                        .append("(eps.")
                        .append(eps)
                        .append(") ")
                        .append(duration)
                        .append(" min.")
                        .append(" - ")
                        .append(year)
                        .toString();
                singleSearchResult.setDesc(desc);
                singleSearchResult.setIdInt(id);
                singleSearchResult.setPoster(poster);
                output.add(singleSearchResult);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }
}


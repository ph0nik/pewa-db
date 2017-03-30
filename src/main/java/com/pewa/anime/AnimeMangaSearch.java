package com.pewa.anime;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import com.pewa.PewaType;
import com.pewa.SingleSearchResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.TreeSet;

import static com.pewa.config.ConfigReader.*;
import static com.pewa.config.Session.updateSession;

public class AnimeMangaSearch {

    private final static Logger log = LogManager.getLogger(AnimeMangaSearch.class);

    public Set<SingleSearchResult> aniListSearch(String query, String searchType) {
        Set<SingleSearchResult> output = new TreeSet<>();
        String url;
        AnimeAccessToken animeAccessToken = updateSession();

        if (searchType.equals("anime")) {
            url = aniListApiEndpoint + aniListSearchAnime + query;
        } else {
            url = aniListApiEndpoint + aniListSearchManga + query;
        }
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
                JsonObject singleElement = member.asObject();
                String titleRom = singleElement.get("title_romaji").asString();
                String titleEng = singleElement.get("title_english").asString();
                String type = singleElement.get("type").asString();
                int id = singleElement.get("id").asInt();
                String poster = singleElement.get("image_url_lge").asString();
                String fuzzyDateStart = "19700101";
                String fuzzyDateEnd;
                if (!singleElement.get("start_date_fuzzy").isNull()) {
                    fuzzyDateStart = singleElement.get("start_date_fuzzy").toString(); // YYYYDDMM
                }
                if (!singleElement.get("end_date_fuzzy").isNull()) {
                    fuzzyDateEnd = singleElement.get("end_date_fuzzy").toString();
                } else {
                    fuzzyDateEnd = fuzzyDateStart;
                }
                String year;
                if (fuzzyDateStart.length() == 4) {
                    fuzzyDateStart = fuzzyDateStart + "0101";
                }
                if (fuzzyDateEnd.length() == 4) {
                    fuzzyDateEnd = fuzzyDateEnd + "0101";
                }
                if (fuzzyDateStart.equals(fuzzyDateEnd)) {
                    LocalDate zdt = LocalDate.parse(fuzzyDateStart, DateTimeFormatter.ofPattern("yyyyMMdd"));
                    year = Integer.toString(zdt.getYear());
                } else {
                    LocalDate zdtStart = LocalDate.parse(fuzzyDateStart, DateTimeFormatter.ofPattern("yyyyMMdd"));
                    LocalDate zdtEnd = LocalDate.parse(fuzzyDateEnd, DateTimeFormatter.ofPattern("yyyyMMdd"));
                    year = "[" + zdtStart.getYear() + " - " + zdtEnd.getYear() + "]";
                }
                String addInfo;
                if (searchType.equals("anime")) {
                    int eps = singleElement.getInt("total_episodes", 0);
                    int duration = 0;
                    if (!singleElement.get("duration").isNull()) {
                        duration = singleElement.get("duration").asInt();
                    }
                    addInfo = new StringBuilder(" / eps.")
                            .append(eps)
                            .append(" / ")
                            .append(duration)
                            .append("min.")
                            .toString();
                    singleSearchResult.setType(PewaType.ANIME);
                } else {
                    int chapters = member.asObject().getInt("total_chapters", 0);
                    int volumes = member.asObject().getInt("total_volumes", 0);
                    addInfo = new StringBuilder(" / ")
                            .append(chapters)
                            .append(" chapters / ")
                            .append(volumes)
                            .append(" volumes")
                            .toString();
                    singleSearchResult.setType(PewaType.MANGA);
                }
                String desc = new StringBuilder(titleRom)
                        .append(" <")
                        .append(titleEng)
                        .append("> (")
                        .append(year)
                        .append(") ")
                        .append(type)
                        .append(addInfo)
                        .toString();
                singleSearchResult.setDesc(desc);
                singleSearchResult.setIdInt(id);
                singleSearchResult.setPoster(poster);
                output.add(singleSearchResult);
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return output;
    }
}


package com.pewa.anime;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import com.pewa.PewaType;
import com.pewa.common.Results;
import com.pewa.common.SingleSearchResult;
import com.pewa.config.ConfigFactory;
import com.pewa.movie.tmdb.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.TreeSet;

import static com.pewa.anime.Session.updateSession;

@Component
public class AnimeMangaSearch {

    private final static Logger log = LogManager.getLogger(AnimeMangaSearch.class);
    private PewaType searchType;

    /**
     * Performs search on external web-api and returns Set of SingleSearchResult objects based on query and searchType.
     *
     * @param query      an {@code String}
     * @param searchType an {@code PewaType}
     */

    public Results aniListSearch(String query, PewaType searchType) {
        this.searchType = searchType;
        Results results = new Results();
        Set<SingleSearchResult> output = new TreeSet<>();
        AnimeAccessToken animeAccessToken = updateSession();
        String anime = new StringBuilder(ConfigFactory.get("search.aniListApiEndpoint"))
                .append(ConfigFactory.get("search.aniListSearchAnime"))
                .append(query)
                .toString();
        String manga = new StringBuilder(ConfigFactory.get("search.aniListApiEndpoint"))
                .append(ConfigFactory.get("search.aniListSearchManga"))
                .append(query)
                .toString();
        String url = (searchType.equals(PewaType.ANIME)) ? anime : manga;
        try {
            final String getResults = Jsoup.connect(url)
                    .data("access_token", animeAccessToken.getAccessToken())
                    .userAgent(ConfigFactory.get("search.userAgent"))
                    .timeout(5 * 1000)
                    .ignoreContentType(true)
                    .get()
                    .text();
            JsonValue objResults = Json.parse(getResults);
            if (!objResults.isObject()) {
                output = aniListSearch(objResults.asArray());
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        output.forEach(results::setEncounters);
        return results;
    }

    private Set<SingleSearchResult> aniListSearch(JsonArray resultsArray) {
        Set<SingleSearchResult> output = new TreeSet<>();
        for (JsonValue member : resultsArray) {
            SingleSearchResult singleSearchResult = new SingleSearchResult();
            JsonObject singleElement = member.asObject();
            String titleRom = singleElement.get("title_romaji").asString();
            String titleEng = singleElement.get("title_english").asString();
            int id = singleElement.get("id").asInt();
            String fuzzyDateStart = (!singleElement.get("start_date_fuzzy").isNull())
                    ? singleElement.get("start_date_fuzzy").toString()
                    : "19700101";
            if (fuzzyDateStart.length() == 4 || fuzzyDateStart.substring(4, 8).equals("0000")) {
                fuzzyDateStart = fuzzyDateStart.substring(0, 4) + "0101";
            }
            LocalDate zdt = LocalDate.parse(fuzzyDateStart, DateTimeFormatter.ofPattern("yyyyMMdd"));
            String addInfo;
            if (searchType.equals(PewaType.ANIME)) {
                String animeType = singleElement.getString("type", "");
                String desc = (singleElement.get("description").isNull())
                        ? "N/A"
                        : singleElement.getString("description","");
                addInfo = new StringBuilder("[")
                        .append(animeType)
                        .append("] ")
                        .append(desc)
                        .toString();
                singleSearchResult.setType(searchType);
            } else {
                addInfo = (singleElement.get("description").isNull())
                        ? "N/A"
                        : singleElement.getString("description","");
                singleSearchResult.setType(searchType);
            }
            singleSearchResult.setTitle(titleRom);
            singleSearchResult.setAltTitle(titleEng);
            singleSearchResult.setDescription(addInfo);
            singleSearchResult.setIdInt(id);
            singleSearchResult.setDate(zdt);
            output.add(singleSearchResult);
        }
        return output;
    }
}


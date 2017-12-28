package com.pewa.tv;

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

/**
 * Created by phonik on 2017-03-31.
 */
@Component
public class TvShowSearch {

    private static final Logger log = LogManager.getLogger(TvShowSearch.class);

    public Results searchTv(String query, Results results) {
        Set<SingleSearchResult> output = new TreeSet<>();
        String searchUrl = ConfigFactory.get("search.tvMazeSearchUrl").replaceAll("<query>", query).replaceAll(" ", "+");
        log.info(searchUrl);
        try {
            final String searchResult = Jsoup.connect(searchUrl)
                    .userAgent(ConfigFactory.get("search.userAgent"))
                    .timeout(5 * 1000)
                    .ignoreContentType(true)
                    .get()
                    .text();
            JsonArray parseResults = Json.parse(searchResult).asArray();
            for (JsonValue result : parseResults) {
                SingleSearchResult singleSearchResult = new SingleSearchResult();
                JsonObject singleElement = result.asObject().get("show").asObject();
                int tvmazeId = singleElement.getInt("id",0);
                singleSearchResult.setIdInt(tvmazeId);
                String title = singleElement.getString("name","");
                singleSearchResult.setTitle(title);
                String desc = singleElement.getString("summary","");
                singleSearchResult.setDescription(desc);
                String poster = singleElement.get("image").asObject().getString("medium","");
                singleSearchResult.setPoster(poster);
                String rawDate = singleElement.getString("premiered","19000101");
                LocalDate date = LocalDate.parse(rawDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                singleSearchResult.setDate(date);
                singleSearchResult.setType(PewaType.TVSERIES);
                log.info(singleSearchResult);
                output.add(singleSearchResult);
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        output.forEach(results::setEncounters);
        return results;
    }
}

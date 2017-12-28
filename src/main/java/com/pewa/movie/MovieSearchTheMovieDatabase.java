package com.pewa.movie;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import com.google.gson.Gson;
import com.pewa.PewaType;
import com.pewa.common.Request;
import com.pewa.common.Results;
import com.pewa.common.SingleSearchResult;
import com.pewa.config.ConfigFactory;
import com.pewa.movie.tmdb.Result;
import com.pewa.movie.tmdb.TmdbSearchResult;
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
 * Created by phonik on 2017-10-18.
 */
@Component
public class MovieSearchTheMovieDatabase implements MovieSearch {

    private static final Logger log = LogManager.getLogger(MovieSearchTheMovieDatabase.class);

    public Results externalMovieSearch(String request, Results results) {
        Set<SingleSearchResult> searchResultSet = new TreeSet<>();
        //TODO przywrócić string w ciągu, bez sensowne pakowanie i wypakowywanie z obiektu request
        try {
            String url = new StringBuilder(ConfigFactory.get("themoviedb.url"))
                    .append(ConfigFactory.get("themoviedb.search"))
                    .toString()
                    .replaceAll("<api-key>", ConfigFactory.get("themoviedb.apiKey"))
                    .replaceAll("<query>", request);

            String tmdbSearch = Jsoup.connect(url)
                    .userAgent(ConfigFactory.get("search.userAgentMB"))
                    .timeout(5 * 1000)
                    .ignoreContentType(true)
                    .get()
                    .text();
            searchResultSet = createReturnSetFromSearch(tmdbSearch);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        searchResultSet.forEach(results::setEncounters);
        return results;
    }

    @Override
    public Integer idImdbString2Int(String idImdb) {
        return null;
    }

    private Set<SingleSearchResult> createReturnSetFromSearch(String tmdbSearch) {
        Gson gson = new Gson();
        TmdbSearchResult tmdbSearchResult = gson.fromJson(tmdbSearch, TmdbSearchResult.class);
        Set<SingleSearchResult> searchResultSet = new TreeSet<>();
        for (Result sr : tmdbSearchResult.getResults()) {
            SingleSearchResult ssr = new SingleSearchResult();
            ssr.setIdInt(sr.getId());
            ssr.setTitle(sr.getOriginalTitle());
            if (sr.getPosterPath() != null) ssr.setPoster(sr.getPosterPath());
            if (sr.getReleaseDate().length() == 0)
                ssr.setDate(LocalDate.now());
            else
                ssr.setDate(LocalDate.parse(sr.getReleaseDate(), DateTimeFormatter.ISO_LOCAL_DATE));
            if (sr.getOverview() != null && !"".equals(sr.getOverview())) {
                ssr.setDescription(sr.getOverview());
            } else {
                ssr.setDescription("");
            }
            ssr.setType(PewaType.MOVIE);
            searchResultSet.add(ssr);
        }
        return searchResultSet;
    }

/*    private Set<SingleSearchResult> createReturnSetFromSearch(String tmdbSearch) {
        JsonArray tmdbArray = Json.parse(tmdbSearch).asObject().get("results").asArray();
        Set<SingleSearchResult> searchResultSet = new TreeSet<>();

        for (JsonValue valueObject : tmdbArray) {
            JsonObject tmdbObject = valueObject.asObject();
            SingleSearchResult singleSearchResult = new SingleSearchResult();
            Integer id = tmdbObject.getInt("id",0);
            singleSearchResult.setIdInt(id);
            String movieTitle = tmdbObject.getString("title", "");
            singleSearchResult.setTitle(movieTitle);
            JsonValue poster_path = tmdbObject.get("poster_path");
            String moviePoster = poster_path.isNull() ? "" : poster_path.asString();
            singleSearchResult.setPoster(moviePoster);
            String movieDescription = tmdbObject.getString("overview","");
            singleSearchResult.setDescription(movieDescription);
            String movieDate = tmdbObject.getString("release_date","");
            if (movieDate.length() == 0)
                singleSearchResult.setDate(LocalDate.now());
            else
                singleSearchResult.setDate(LocalDate.parse(movieDate, DateTimeFormatter.ISO_LOCAL_DATE));
            singleSearchResult.setType(PewaType.MOVIE);
            searchResultSet.add(singleSearchResult);
        }
        return searchResultSet;
    }*/
}

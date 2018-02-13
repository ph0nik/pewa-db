package com.pewa.movie;

import com.google.gson.Gson;
import com.pewa.PewaType;
import com.pewa.common.Results;
import com.pewa.common.SingleSearchResult;
import com.pewa.config.ConfigFactory;
import com.pewa.movie.tmdb.Result;
import com.pewa.movie.tmdb.TmdbSearchResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Connection;
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
    int totalPages;

/**
* Creates proper url addres for TheMovieDB api.
*/
    private String buildUrl(String query, int resultsPage) {
        return new StringBuilder(ConfigFactory.get("themoviedb.url"))
                .append(ConfigFactory.get("themoviedb.search"))
                .toString()
                .replaceAll("<api-key>", ConfigFactory.get("themoviedb.apiKey"))
                .replaceAll("<query>", query)
                .replaceAll("<page>", Integer.toString(resultsPage));
    }

/**
 * Returns request response as String, based on given url
 *
 * @param   url   properly formatted url address.
 * @return  server response in json format for status code 200, otherwise empty String.
 *
 * */
    private String getPage(String url) throws IOException {
        Connection.Response response = Jsoup.connect(url)
                .userAgent(ConfigFactory.get("search.userAgentMB"))
                .timeout(5 * 1000)
                .ignoreContentType(true)
                .execute();
        if (response.statusCode() != 200) {
            log.error("Error " + response.statusCode() + " : " + response.statusMessage());
            return "";
        } else {
            return response.parse().text();
        }
    }

    public Results externalMovieSearch(String request, Results results) {
        Set<SingleSearchResult> searchResultSet = new TreeSet<>();
        totalPages = 1;
        try {
            String url = buildUrl(request, totalPages);
            String tmdbSearch = getPage(url);
            if (tmdbSearch.length() != 0) {
                searchResultSet = createReturnSetFromSearch(tmdbSearch);
                if (totalPages > 1) {
                    int maximumPageRange = (totalPages > 20) ? 20 : totalPages;
                    for (int i = 2; i <= maximumPageRange; i++) {
                        log.debug("i = " + i + " total = " + maximumPageRange);
                        url = buildUrl(request, i);
                        searchResultSet.addAll(createReturnSetFromSearch(getPage(url)));
                    }
                }
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        searchResultSet.forEach(results::setEncounters);
        return results;
    }

    private Set<SingleSearchResult> createReturnSetFromSearch(String tmdbSearch) {
        Gson gson = new Gson();
        TmdbSearchResult tmdbSearchResult = gson.fromJson(tmdbSearch, TmdbSearchResult.class);
        if (totalPages == 1) totalPages = tmdbSearchResult.getTotalPages();
        Set<SingleSearchResult> searchResultSet = new TreeSet<>();
        for (Result sr : tmdbSearchResult.getResults()) {
            SingleSearchResult ssr = new SingleSearchResult();
            ssr.setIdInt(sr.getId());
            ssr.setTitle(sr.getOriginalTitle());
            ssr.setAltTitle(sr.getTitle());
//            if (sr.getPosterPath() != null) ssr.setPoster(sr.getPosterPath());
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
}

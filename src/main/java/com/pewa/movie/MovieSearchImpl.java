package com.pewa.movie;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import com.pewa.PewaType;
import com.pewa.config.ConfigFactory;
import com.pewa.common.SingleSearchResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;

@Component
public class MovieSearchImpl implements MovieSearch {

    private static final Logger log = LogManager.getLogger(MovieSearchImpl.class);

    /*
    * Method takes parameters:
    * - query - searched phrase
    * - type - "movie" or "tv" String which determines searching scope
    * - searchResultSet - empty Set of elements of SingleSearchResult type.
    * Method sends GET request to given url, and passes returned String element
    * to createReturnSetFromSearch method.
    * */
    @Override
    public Set<SingleSearchResult> externalMovieSearch(String query) {
        Set<SingleSearchResult> searchResultSet = new TreeSet<>();
        try {
            String url = new StringBuilder(ConfigFactory.get("search.imdbgetid"))
                    .append(query.replaceAll(" ", "+"))
                    .append(ConfigFactory.get("search.imdbmovies"))
                    .toString();
            String imdbSearch = Jsoup.connect(url)
                    .userAgent(ConfigFactory.get("search.userAgent"))
                    .timeout(5 * 1000)
                    .get()
                    .text();
            searchResultSet = createReturnSetFromSearch(imdbSearch);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return searchResultSet;
    }

    /*
    * Method takes server response object in json format. It parses through it and combines its parameters into
    * SingleSearchResult object. Then every element is added into Set collection, which is being returned.
    *
    * */
    private Set<SingleSearchResult> createReturnSetFromSearch(String imdbSearch) {
        JsonObject imdbObject = Json.parse(imdbSearch).asObject();
        Set<SingleSearchResult> searchResultSet = new TreeSet<>();

        for (JsonObject.Member valueObject : imdbObject) {
            JsonArray imdbValues = valueObject.getValue().asArray();
            for (JsonValue value : imdbValues) {
                SingleSearchResult singleSearchResult = new SingleSearchResult();
                String idImdb = value.asObject().getString("id", "");
                String movieTitle = value.asObject().getString("title", "");
                singleSearchResult.setTitle(movieTitle);
                String titleDescription = value.asObject().getString("title_description", "");
                String[] desc = titleDescription.split(", ");
                String year = desc[0];
                singleSearchResult.setDate(LocalDate.now());
                String director;
                if (desc.length > 1) {
                    director = desc[1];
                } else director = "";
                StringBuilder basicInfo = new StringBuilder("(")
                        .append(year)
                        .append(") reż. ")
                        .append(director);
                singleSearchResult.setUrl(idImdb);
                singleSearchResult.setDesc(basicInfo.toString());
                singleSearchResult.setType(PewaType.MOVIE);
                singleSearchResult.setIdInt(Integer.parseInt(idImdb.replaceAll("tt", "")));
                singleSearchResult.setPoster("");
                searchResultSet.add(singleSearchResult);
                basicInfo.setLength(0);
            }
        }
        return searchResultSet;
    }

}

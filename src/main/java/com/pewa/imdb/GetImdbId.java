package com.pewa.imdb;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import com.pewa.SingleSearchResult;
import com.pewa.config.ConfigReader;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

import static com.pewa.config.ConfigReader.*;

public class GetImdbId {
    private static StringBuilder url;
    private static StringBuilder basicInfo = new StringBuilder();

    private GetImdbId() {
        /*
        prywatny konstruktor uniemożliwia utworzenie obiektu
        */
    }
/*
* Method takes parameters:
* - query - searched phrase
* - type - "movie" or "tv" String which determines searching scope
* - searchResultSet - empty Set of elements of SingleSearchResult type.
* Method sends GET request to given url, and passes returned String element
* to createReturnSetFromSearch method.
* */
    public static Set<SingleSearchResult> externalMovieSearch(String query, String type, Set<SingleSearchResult> searchResultSet) {
        try {
            if (type.equals("movie")) {
                url = new StringBuilder(searchUrl).append(query.replaceAll(" ", "+"))
                        .append(searchMovie);
            }
            if (type.equals("tv")) {
                url = new StringBuilder(searchUrl).append(query.replaceAll(" ", "+"))
                        .append(searchTv);
            }
            String imdbSearch = Jsoup.connect(url.toString())
                    .userAgent(userAgent)
                    .timeout(5 * 1000)
                    .get()
                    .text();
            searchResultSet = createReturnSetFromSearch(imdbSearch);
        } catch (IOException e) {
            System.out.printf("Nie znaleziono adresu: " + e);
        } finally {
            return searchResultSet;
        }
    }
/*
* Method takes server response object in json format. It parses through it and combines its parameters into
* SingleSearchResult object. Then every element is added into Set collection, which is being returned.
*
* */
    private static Set<SingleSearchResult> createReturnSetFromSearch(String imdbSearch) {
        JsonObject imdbObject = Json.parse(imdbSearch).asObject();
        Set<SingleSearchResult> searchResultSet = new TreeSet<>();

        for (JsonObject.Member valueObject : imdbObject) {
            JsonArray imdbValues = valueObject.getValue().asArray();
            for (JsonValue value : imdbValues) {
                String idImdb = value.asObject().getString("id", "");
                String movieTitle = value.asObject().getString("title", "");
                String titleDescription = value.asObject().getString("title_description", "");
                String[] desc = titleDescription.split(", ");
                String year = (desc[0]);
                String director = "";
                if (desc.length > 1) {
                    director = desc[1];
                } else director = "";
                basicInfo.append(movieTitle)
                        .append(" (")
                        .append(year)
                        .append(") reż. ")
                        .append(director);
                SingleSearchResult singleSearchResult = new SingleSearchResult();
                singleSearchResult.setUrl(idImdb);
                singleSearchResult.setDesc(basicInfo.toString());
                searchResultSet.add(singleSearchResult);
                basicInfo.setLength(0);
            }
        }
        return searchResultSet;
    }

}

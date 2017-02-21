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

public class GetImdbId {
    private static String idImdb = "";
    private static StringBuilder url;
    private static String movieTitle = "";
    private static String titleDescription = "";
    private static String director = "";
    private static String year = "";
    private static StringBuilder basicInfo = new StringBuilder();
    private static String[] desc;

    private GetImdbId() {
        /*
        prywatny konstruktor uniemożliwia utworzenie obiektu
        */
    }

    public static Set<SingleSearchResult> mapOfItems(String query, String type, Set<SingleSearchResult> searchResultSet) {
        try {
            if (type.equals("movie")) {
                url = new StringBuilder(ConfigReader.searchUrl).append(query.replaceAll(" ", "+"))
                        .append(ConfigReader.searchMovie);
            }
            if (type.equals("tv")) {
                url = new StringBuilder(ConfigReader.searchUrl).append(query.replaceAll(" ", "+"))
                        .append(ConfigReader.searchTv);
            }
            String imdbSearch = Jsoup.connect(url.toString())
                    .userAgent(ConfigReader.userAgent)
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

    private static Set<SingleSearchResult> createReturnSetFromSearch(String imdbSearch) {
        JsonObject imdbObject = Json.parse(imdbSearch).asObject();
        Set<SingleSearchResult> searchResultSet = new TreeSet<>();

        for (JsonObject.Member valueObject : imdbObject) {
            JsonArray imdbValues = valueObject.getValue().asArray();
            for (JsonValue value : imdbValues) {
                idImdb = value.asObject().getString("id", "");
                movieTitle = value.asObject().getString("title", "");
                titleDescription = value.asObject().getString("title_description", "");
                desc = titleDescription.split(", ");
                year = (desc[0]);
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

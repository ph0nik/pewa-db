package com.pewa.anime;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import com.google.gson.Gson;
import com.pewa.PewaType;
import com.pewa.anime.anilist.SearchData;
import com.pewa.common.Results;
import com.pewa.common.SingleSearchResult;
import com.pewa.config.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.TreeSet;

import static com.pewa.anime.Session.updateSession;

@Component
public class AnimeMangaSearch {

    private final static Logger log = LogManager.getLogger(AnimeMangaSearch.class);
    private final String animeSearchQueryFile = "src/main/resources/graphql/anime-search.graphql";
    private final String mangaSearchQueryFiles = "src/main/resources/graphql/manga-search.graphql";
    private PewaType searchType;


    @Deprecated
    private String getPage(String url) throws IOException {
        AnimeAccessToken animeAccessToken = updateSession();
        Connection.Response response = Jsoup.connect(url)
                .data("access_token", animeAccessToken.getAccessToken())
                .userAgent(ConfigFactory.get("search.userAgent"))
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

    /**
     * Performs search on external web-api and returns Set of SingleSearchResult objects based on query and searchType.
     *
     * @param query      an {@code String}
     * @param searchType an {@code PewaType}
     */

    @Deprecated
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
            String getResults = getPage(url);
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

    @Deprecated
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
                        : singleElement.getString("description", "");
                addInfo = new StringBuilder("[")
                        .append(animeType)
                        .append("] ")
                        .append(desc)
                        .toString();
                singleSearchResult.setType(searchType);
            } else {
                addInfo = (singleElement.get("description").isNull())
                        ? "N/A"
                        : singleElement.getString("description", "");
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

    public Results aniListSearchV2(String query, PewaType type) {
        Results results = new Results();
        AnimeMangaQuery animeMangaQuery;
        try {
            animeMangaQuery = setQueryObject(query, type);
            Set<SingleSearchResult> singleSearchResults = parseSearchResult(sendRequest(animeMangaQuery));
            singleSearchResults.forEach(results::setEncounters);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }

    /**
     * Prepares query object based on search phrase
     *
     * @param title an {@code String}
     */
    private AnimeMangaQuery setQueryObject(String title, PewaType type) throws IOException {
        AnimeMangaQuery queryObject = new AnimeMangaQuery();
        String filePath = type == PewaType.ANIME ? animeSearchQueryFile : mangaSearchQueryFiles;
        StringBuilder query = new StringBuilder("");
        Files.readAllLines(Paths.get(filePath))
                .forEach(query::append);
        queryObject.setQuery(query.toString());
        String variables = "{\"title\":\"" + title + "\"}";
        queryObject.setVariables(variables);
        return queryObject;
    }

    private String sendRequest(AnimeMangaQuery amq) throws IOException {
        String url = "https://graphql.anilist.co";
        Document response = Jsoup.connect(url)
                .userAgent(ConfigFactory.get("search.userAgent"))
                .data("query", amq.getQuery())
                .data("variables", amq.getVariables())
                .timeout(5 * 1000)
                .ignoreContentType(true)
                .post();
        return response.text();
    }

    private Set<SingleSearchResult> parseSearchResult(String object) {
        Set<SingleSearchResult> output = new TreeSet<>();
        Gson gsonPrser = new Gson();
        SearchData searchData = gsonPrser.fromJson(object, SearchData.class);
        searchData.getData().getPage().getMedia().forEach(r -> {
            SingleSearchResult ssr = new SingleSearchResult();
            if ("anime".toUpperCase().equals(r.getType())) {
                ssr.setType(PewaType.ANIME);
            } else {
                ssr.setType(PewaType.MANGA);
            }
            ssr.setIdInt(r.getId());
            ssr.setTitle(r.getTitle().getRomaji());
            if (r.getTitle().getEnglish() == null || "".equals(r.getTitle().getEnglish())) {
                ssr.setAltTitle(r.getTitle().getRomaji());
            } else {
                ssr.setAltTitle(r.getTitle().getEnglish());
            }
            String format = r.getFormat();
            String desc = "[" + format + "] " + r.getDescription();
            ssr.setDescription(desc);
            String startDate = r.getStartDate().getYear() != null ? r.getStartDate().getYear() + "0101" : "10000101";
            ssr.setDate(LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyyMMdd")));
            output.add(ssr);
        });
        return output;
    }


}


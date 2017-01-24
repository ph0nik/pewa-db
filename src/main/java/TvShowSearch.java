import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by phonik on 2017-01-13.
 */
public class TvShowSearch {

    private TvShowSearch() {
    }

    public static TvShowSummary sTvShow(String userInput) {
        String urlSummary = "";
        StringBuilder urlEpisodeList = new StringBuilder();
        Map<Integer, List<String>>  foundShowTitle;
        int element;
        String query = userInput;

        TvShowSummary tvshowItem = new TvShowSummary();


        try {
            foundShowTitle = GetImdbId.mapOfItems(query, "tv");

            if(foundShowTitle.isEmpty()) {
                System.out.println("Nie znaleziono szukanego wyrażenia.");
            }
            else {
                for(Map.Entry<Integer, List<String>> entry : foundShowTitle.entrySet()) {
                    System.out.println("[" + entry.getKey() + "] " + entry.getValue().get(1));
                }
                element = UserInterface.pickElement(foundShowTitle);

                urlSummary = ConfigReader.tvMaze.concat(ConfigReader.tvMazeByImdbId).concat(foundShowTitle.get(element).get(0));
                String getSummaryByImdbId = Jsoup.connect(urlSummary)
                        .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36 OPR/42.0.2393.94")
                        .timeout(5*1000)
                        .ignoreContentType(true)
                        .get()
                        .text();
                JsonValue tvmazeSum = Json.parse(getSummaryByImdbId);
                tvshowItem.setTvMazeId(tvmazeSum.asObject().getInt("id",0));
                tvshowItem.setTvMazeUrl(tvmazeSum.asObject().getString("url",""));
                tvshowItem.setTitle(tvmazeSum.asObject().getString("name",""));
                tvshowItem.setType(tvmazeSum.asObject().getString("type",""));
                tvshowItem.setLanguage(tvmazeSum.asObject().getString("language",""));
            /*
            * odczyta tablicy gatunków z wykorzystaniem tablicy tymczasowej
            * */
                List<String> tempGenresList = new ArrayList<String>();
                JsonArray jsonGenresArray = tvmazeSum.asObject().get("genres").asArray();
                for(JsonValue item : jsonGenresArray) {
                    tempGenresList.add(item.asString());
                }
                tvshowItem.setGenres(tempGenresList);
                tvshowItem.setStatus(tvmazeSum.asObject().getString("status",""));
                tvshowItem.setRuntime(tvmazeSum.asObject().getInt("runtime",0));

                String firstAiredTemp = tvmazeSum.asObject().getString("premiered","0000-00-00");
                DateFormat firstAiredDF = new SimpleDateFormat("yyyy-d-MM", Locale.ENGLISH);
                tvshowItem.setPremiered(firstAiredDF.parse(firstAiredTemp));

                tvshowItem.setRatingAvg(tvmazeSum.asObject().get("rating").asObject().getDouble("average",0.0));
                if(tvmazeSum.asObject().get("network").isNull()) {
                    tvshowItem.setNetwork(tvmazeSum.asObject().get("webChannel").asObject().getString("name",""));
                    tvshowItem.setCountry(tvmazeSum.asObject().get("webChannel").asObject().get("country").asObject().getString("name",""));
                } else {
                    tvshowItem.setNetwork(tvmazeSum.asObject().get("network").asObject().getString("name",""));
                    tvshowItem.setCountry(tvmazeSum.asObject().get("network").asObject().get("country").asObject().getString("name",""));
                }


                JsonObject tvmazeExternals = tvmazeSum.asObject().get("externals").asObject();
                tvshowItem.setImdbLink(tvmazeExternals.getString("imdb",""));
                if(tvmazeExternals.get("tvrage").isNull()) {
                    tvshowItem.setTvrageLink(0);
                } else {
                    tvshowItem.setTvrageLink(tvmazeExternals.getInt("tvrage",0));
                }
                if(tvmazeExternals.get("thetvdb").isNull()) {
                    tvshowItem.setThetvdbLink(0);
                } else {
                    tvshowItem.setThetvdbLink(tvmazeExternals.getInt("thetvdb",0));
                }
                JsonObject tvmazePosters = tvmazeSum.asObject().get("image").asObject();
                tvshowItem.setPosterMed(tvmazePosters.getString("medium",""));
                tvshowItem.setPosterOrg(tvmazePosters.getString("original",""));
                tvshowItem.setSummary(tvmazeSum.asObject().getString("summary",""));

                urlEpisodeList.append(ConfigReader.tvMaze).append(ConfigReader.tvMazeSummary).append(tvshowItem.getTvMazeId()).append(ConfigReader.tvMazeEpisodeList);
                String getEpisodes = Jsoup.connect(urlEpisodeList.toString())
                        .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36 OPR/42.0.2393.94")
                        .timeout(5*1000)
                        .ignoreContentType(true)
                        .get()
                        .text();
                JsonArray tvmazeEpisodes = Json.parse(getEpisodes).asArray();
                for(JsonValue ep : tvmazeEpisodes) {
                    int tvMazeId = ep.asObject().getInt("id",0);
                    String tvMazeUrl = ep.asObject().getString("url","");
                    String epTitle = ep.asObject().getString("name","");
                    int season = ep.asObject().getInt("season",0);
                    int episode = ep.asObject().getInt("number",0);
                    firstAiredTemp = ep.asObject().getString("airdate","0000-00-00");
                    firstAiredDF = new SimpleDateFormat("yyyy-d-MM", Locale.ENGLISH);
                    Date firstAired = firstAiredDF.parse(firstAiredTemp);
                    String summary = ep.asObject().getString("summary","");
                    tvshowItem.setEpisodes(new TvShowEpisode(tvMazeId, tvMazeUrl, epTitle, season, episode, firstAired, summary));
                }

                return tvshowItem;
            }

        } catch (IOException e) {
            System.out.println("Nie można nawiązać połączenia z: " + urlSummary);
        } catch (ParseException e) {
            System.out.println("Błędny format daty (Locale) " + e);
        }
        return tvshowItem;
    }
}
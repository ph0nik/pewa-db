import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalINIConfiguration;
import org.jsoup.Jsoup;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by phonik on 2017-01-13.
 */
public class GetImdbId {
    private static File configFile = new File("pewa-config.ini");
    private static Map<Integer, List<String>> returnId = new TreeMap();
    private static String idImdb = "";
    private static StringBuilder url;
    private static String movieTitle = "";
    private static String titleDescription = "";
    private static String director = "";
    private static String year = "";
    private static StringBuilder basicInfo = new StringBuilder();
    private static String[] desc;
    private static int num = 0;

    private GetImdbId() {
        /*
        prywatny konstruktor uniemożliwia utworzenie obiektu
        */
    }

    public static Map<Integer, List<String>> mapOfItems(String query, String type) {
        try {
            if(type=="movie") {
                 url = new StringBuilder(ConfigReader.searchUrl).append(query.replaceAll(" ","+"))
                                                            .append("*")
                                                            .append(ConfigReader.searchMovie);
            }
            if(type=="tv") {
                 url = new StringBuilder(ConfigReader.searchUrl).append(query.replaceAll(" ","+"))
                                                            .append(ConfigReader.searchTv);
            }

            String imdbSearch = Jsoup.connect(url.toString())
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36 OPR/42.0.2393.94")
                    .timeout(5*1000)
                    .get()
                    .text();

        getImdbInfo2(imdbSearch);

        }  catch (IOException e) {
            System.out.printf("Nie znaleziono adresu: " + e);
        }
        return returnId;
    }
    private static void getImdbInfo2(String imdbSearch) {
        JsonObject imdbObject = Json.parse(imdbSearch).asObject();

        for(JsonObject.Member valueObject : imdbObject) {
            JsonArray imdbValues = valueObject.getValue().asArray();
            for(JsonValue value : imdbValues) {
                idImdb = value.asObject().getString("id", "");
                movieTitle = value.asObject().getString("title", "");
                titleDescription = value.asObject().getString("title_description", "");
                desc = titleDescription.split(", ");
                year = (desc[0]);
                if(desc.length > 1) {
                    director = desc[1];
                } else director = "";
                basicInfo.append(movieTitle)
                        .append(" (")
                        .append(year)
                        .append(") reż. ")
                        .append(director);
                returnId.put(num, Arrays.asList(idImdb, basicInfo.toString()));
                basicInfo.setLength(0);
                num++;
            }
        }
    }
    private static void getImdbInfo(String imdbSearch, String field) {
        JsonArray imdbValues = Json.parse(imdbSearch).asObject().get(field).asArray();
        for(JsonValue value : imdbValues) {
            idImdb = value.asObject().getString("id", "");
            movieTitle = value.asObject().getString("title", "");
            titleDescription = value.asObject().getString("title_description", "");
            desc = titleDescription.split(", ");
            year = (desc[0]);
            if(desc.length > 1) {
                director = desc[1];
            } else director = "";
            basicInfo.append(movieTitle)
                    .append(" (")
                    .append(year)
                    .append(") reż. ")
                    .append(director);
            returnId.put(num, Arrays.asList(idImdb, basicInfo.toString()));
            basicInfo.setLength(0);
            num++;
        }
    }
}

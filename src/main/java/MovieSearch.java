import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonValue;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalINIConfiguration;
import org.jsoup.Jsoup;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by phonik on 2017-01-12.
 */
public class MovieSearch {

    private MovieSearch() {

    }

    public static Movie mSearch(String userInput) {
        String url = "";
        Map<Integer, List<String>> foundMovieTitle;
        String query = userInput;

        Movie movieItem = new Movie();

        try {
            foundMovieTitle = GetImdbId.mapOfItems(query, "movie");

            if(foundMovieTitle.isEmpty()) {
                System.out.println("Nie znaleziono szukanego wyrażenia.");
            }
            else {
                for(Map.Entry<Integer, List<String>> entry : foundMovieTitle.entrySet()) {
                    System.out.println("[" + entry.getKey() + "] " + entry.getValue().get(1));
                }

                int element = UserInterface.pickElement(foundMovieTitle);

                url = ConfigReader.omdbUrl.concat(foundMovieTitle.get(element).get(0));
                String omdbItem = Jsoup.connect(url)
                        .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36 OPR/42.0.2393.94")
                        .timeout(5*1000)
                        .ignoreContentType(true)
                        .get()
                        .text();
                JsonValue omdbValues = Json.parse(omdbItem);

                movieItem.setTitle(omdbValues.asObject().getString("Title",""));
                movieItem.setYear(Integer.parseInt(omdbValues.asObject().getString("Year","")));
                movieItem.setAgeRating(omdbValues.asObject().getString("Rated",""));
                movieItem.setRelDate(omdbValues.asObject().getString("Released",""));
                movieItem.setRuntime(omdbValues.asObject().getString("Runtime",""));
                movieItem.setGenre(omdbValues.asObject().getString("Genre","").split(", "));
                movieItem.setDirector(omdbValues.asObject().getString("Director","").split(", "));
                movieItem.setWriter(omdbValues.asObject().getString("Writer","").split(", "));
                movieItem.setActors(omdbValues.asObject().getString("Actors","").split(", "));
                movieItem.setPlot(omdbValues.asObject().getString("Plot",""));
                movieItem.setLanguage(omdbValues.asObject().getString("Language","").split(", "));
                movieItem.setCountry(omdbValues.asObject().getString("Country","").split(", "));
                movieItem.setAwards(omdbValues.asObject().getString("Awards",""));
                movieItem.setPoster(omdbValues.asObject().getString("Poster",""));
                movieItem.setMetascore(omdbValues.asObject().getString("Metascore",""));
                movieItem.setImdbRating(omdbValues.asObject().getString("imdbRating",""));
                movieItem.setImdbVotes(omdbValues.asObject().getString("imdbVotes",""));
//                movieItem.setImdbID(omdbValues.asObject().getString("imdbID",""));
                movieItem.setImdbID(foundMovieTitle.get(element).get(0));
            }
            return movieItem;

        } catch (IOException e) {
            System.out.println("Nie można nawiązać połączenia z: " + url);
        }
        return movieItem;
    }
}

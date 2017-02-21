import com.pewa.DatabaseInit;
import com.pewa.DatabaseMovie;
import com.pewa.config.ConfigReader;
import com.pewa.movie.Movie;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class DbTest {
    private static Movie sample = new Movie();

    @BeforeClass
    public static void setUp() {
        ConfigReader.load();
    }

    @BeforeClass
    public static void objectInit() {
        List<String> actors = Arrays.asList(("Keanu Reeves, Laurence Fishburne, Carrie-Anne Moss, Hugo Weaving").split(", "));
        List<String> dir = Arrays.asList("Lana Wachowski, Lilly Wachowski".split(", "));
        List<String> wri = Arrays.asList("Lilly Wachowski, Lana Wachowski".split(", "));
        String[] genre = "Action, Sci-Fi".split(", ");
        String[] country = "USA".split(", ");
        String[] language = "English".split(", ");
        sample.setTitle("Matrix");
        sample.setYear(1999);
        sample.setActors(actors);
        sample.setDirector(dir);
        sample.setWriter(wri);
        sample.setAgeRating("R");
        sample.setAwards("Won 4 Oscars. Another 33 wins & 43 nominations.");
        sample.setRelDate("31 Mar 1999");
        sample.setCountry(country);
        sample.setLanguage(language);
        sample.setGenre(genre);
        sample.setImdbID("tt0133093");
        sample.setImdbRating("8.7");
        sample.setImdbVotes("1,266,485");
        sample.setRuntime("136 min");
        sample.setMetascore("73");
        sample.setPoster("https://images-na.ssl-images-amazon.com/images/M/MV5BMDMyMmQ5YzgtYWMxOC00OTU0LWIwZjEtZWUwYTY5MjVkZjhhXkEyXkFqcGdeQXVyNDYyMDk5MTU@._V1_SX300.jpg");
        sample.setPlot("A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.");
    }


    @Test
    public void modifyDbIfNeeded() throws Exception {
        DatabaseInit.dbInit();
    }

    @Test
    public void createGenreTable() throws Exception {
        DatabaseInit.createTables();
    }
    @Test
    public void insertElementsToDb() throws Exception {
        DatabaseMovie.userConnection(sample);

    }
}

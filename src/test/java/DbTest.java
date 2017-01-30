import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import javax.xml.crypto.Data;

/**
 * Created by phonik on 2017-01-20.
 */
public class DbTest {

    @BeforeClass
    public static void setUp() {
        ConfigReader.load();

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
        String[] actors = "Keanu Reeves, Laurence Fishburne, Carrie-Anne Moss, Hugo Weaving".split(", ");
        String[] dir = "Lana Wachowski, Lilly Wachowski".split(", ");
        String[] wri = "Lilly Wachowski, Lana Wachowski".split(", ");
        String[] genre = "Action, Sci-Fi".split(", ");
        String[] country = "USA".split(", ");
        String[] language = "English".split(", ");
        Movie sample = new Movie();
        sample.setTitle("Matrix");
        sample.setYear(1999);
        sample.setActors(actors);
        sample.setDirector(dir);
        sample.setWriter(wri);
        sample.peopleAsList();
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
        DatabaseMovie.userConnection(sample);

    }
}

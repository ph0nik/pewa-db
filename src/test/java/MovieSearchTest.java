import com.pewa.config.ConfigReader;
import com.pewa.movie.Movie;
import com.pewa.movie.MovieDAO;
import com.pewa.movie.MovieDAOImpl;
import com.pewa.movie.MovieParseToObject;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Set;

public class MovieSearchTest {
    // SingleSearchResult{url='tt0115530', desc='American Buffalo (1996) reż. Michael Corrente'}

    @BeforeClass
    public static void initTest() {
        ConfigReader.load();
    }

    @Test
    public void parseSelectedPositionMovie() {
        Movie americanBuffalo = MovieParseToObject.parseSelected("tt0115530");
        System.out.println(americanBuffalo.toString());
        MovieDAO dbm = new MovieDAOImpl();
        dbm.addMovie(americanBuffalo);
    }

    @Test
    public void variousSearch() throws Exception {
        MovieDAO dbm = new MovieDAOImpl();
        Set<Movie> ppl = dbm.moviesByPerson("hoff");
        System.out.println("person search: ");
        ppl.forEach(System.out::println);

        Set<Movie> year = dbm.moviesByYear(1990, 1999);
        System.out.println("year search: ");
        year.forEach(System.out::println);

        Set<Movie> language = dbm.moviesByLanguage("gli");
        System.out.println("language search: ");
        language.forEach(System.out::println);

        Set<Movie> genre = dbm.moviesByGenre("act");
        System.out.println("genre search:");
        genre.forEach(System.out::println);

        Set<Movie> sprawdzam = dbm.moviesByTitle("mat");
        System.out.println("title search:");
        sprawdzam.forEach(System.out::println);
    }

    @Test
    public void parseErrorCheck() {
        Movie americanBuffalo = MovieParseToObject.parseSelected("asdasda");
        System.out.println(americanBuffalo.toString());
    }
}

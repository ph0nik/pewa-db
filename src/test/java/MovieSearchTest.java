import com.pewa.config.ConfigReader;
import com.pewa.movie.Movie;
import com.pewa.movie.MovieParseToObject;
import org.junit.BeforeClass;
import org.junit.Test;

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
    }

    @Test
    public void parseErrorCheck() {
        Movie americanBuffalo = MovieParseToObject.parseSelected("asdasda");
        System.out.println(americanBuffalo.toString());
    }
}

import com.pewa.anime.Anime;
import com.pewa.anime.AnimeDAO;
import com.pewa.anime.AnimeDAOImpl;
import com.pewa.config.ConfigReader;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.pewa.anime.AnimeParseToObject.getAnimeItem;

/**
 * Created by phonik on 2017-03-22.
 */
public class AnimeDAOImplTest {

    @BeforeClass
    public static void initTest() {
        ConfigReader.load();
    }

    @Test
    public void addAnimeToDB() {
        AnimeDAO animeDAO = new AnimeDAOImpl();
        Anime test = getAnimeItem(43);
        animeDAO.addAnime(test);
    }

    @Test
    public void animeByYear() {


        AnimeDAO animeDAO = new AnimeDAOImpl();
        String yearStart = "1993-01-01";
        String yearEnd = "1998-01-01";
        List<Anime> out = animeDAO.animeByYear(yearStart,yearEnd);
        out.forEach(System.out::println);
    }
    @Test
    public void animeByGenre() {
        AnimeDAO animeDAO = new AnimeDAOImpl();
        List<Anime> out = animeDAO.animeByGenre("mech");
        out.forEach(System.out::println);
    }
    @Test
    public void searchAnime() {
        AnimeDAO animeDAO = new AnimeDAOImpl();
        List<Anime> out = animeDAO.getAnime("ghost");
        out.forEach(System.out::println);
    }
    @Test
    public void animeByPerson() {
        AnimeDAO animeDAO = new AnimeDAOImpl();
        List<Anime> out = animeDAO.animeByPerson("ogura");
        out.forEach(System.out::println);
    }
    @Test
    public void animeById() {
        AnimeDAO animeDAO = new AnimeDAOImpl();
        List<Anime> out = animeDAO.getAnimeById(43);
        out.forEach(System.out::println);
    }





}

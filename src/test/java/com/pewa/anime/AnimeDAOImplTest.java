package com.pewa.anime;

import com.pewa.MediaParse;
import com.pewa.common.Genre;
import com.pewa.common.Person;
import com.pewa.common.Results;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import java.util.List;



/**
 * Created by phonik on 2017-03-22.
 */
public class AnimeDAOImplTest {

    private Results results;

    @BeforeEach
    public void setObjects() {
        results = new Results();
    }

    @Disabled
    @Test
    public void addAnimeToDB() {
        AnimeDAO animeDAO = new AnimeDAOImpl();
        MediaParse<Anime, Integer> getAnime = new AnimeParser();
        Anime test = getAnime.getItem(43);
        animeDAO.addAnime(test);
        test = getAnime.getItem(45);
        animeDAO.addAnime(test);
        test = getAnime.getItem(47);
        animeDAO.addAnime(test);
    }
    @Disabled
    @Test
    public void searchAnime() {
        AnimeDAO animeDAO = new AnimeDAOImpl();
        Results out = animeDAO.getAnime("ghost", results);
        out.getAnimes().forEach(System.out::println);
    }
    @Disabled
    @Test
    public void animeById() {
        AnimeDAO animeDAO = new AnimeDAOImpl();
        Anime out = animeDAO.getAnimeById(43);
        System.out.println(out);
    }
    @Disabled
    @Test
    public void animeByYear() {
        AnimeDAO animeDAO = new AnimeDAOImpl();
        String yearStart = "1993-01-01";
        String yearEnd = "";
        Results out = animeDAO.getAnimeByYear(yearStart,yearEnd,results);
        out.getAnimes().forEach(System.out::println);

    }
    @Disabled
    @Test
    public void animeByGenre() {
        AnimeDAO animeDAO = new AnimeDAOImpl();
        Genre genre = new Genre();
        genre.setId(4);
        Results out = animeDAO.getAnimeByGenre(genre,results);
        out.getAnimes().forEach(System.out::println);
    }
    @Disabled
    @Test
    public void animeByPerson() {
        AnimeDAO animeDAO = new AnimeDAOImpl();
        Person person = new Person();
        person.setId(80);
        Results out = animeDAO.getAnimeByPerson(person, results);
        out.getAnimes().forEach(System.out::println);
    }


/*    // Anime Mapper test
    @Test
    public void tryAnimeMappterInterface() {
        try (SqlSession session = MyBatisFactory.connectionUser().openSession(false)) {
            AnimeMapper animeMapper = session.getMapper(AnimeMapper.class);
            List<Anime> out = animeMapper.byTitleAni("%ghost%");
            out.forEach(System.out::println);
            session.commit();
        }
    }*/

}

package com.pewa.anime;

import com.pewa.MediaParse;
import com.pewa.common.Request;
import com.pewa.common.Results;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


/**
 * Created by phonik on 2017-03-22.
 */
public class AnimeDAOImplTest {

    private Results results;
    private Request request;
    private Integer itemId;
    private AnimeDAO animeDAO;

    @BeforeEach
    public void setObjects() {
        results = new Results();
        request = new Request();
        animeDAO = new AnimeDAOImpl();
    }

    @Disabled
    @Test
    public void addAnimeToDB() {
        MediaParse<Anime, Integer> getAnime = new AnimeParser();
        Anime test = getAnime.getItem(43);
        animeDAO.addAnime(test, results);
        test = getAnime.getItem(45);
        animeDAO.addAnime(test, results);
        test = getAnime.getItem(47);
        animeDAO.addAnime(test, results);
    }
    @Disabled
    @Test
    public void searchAnime() {
        Results out = animeDAO.getAnimeByTitle("ghost", results);
        out.getEncounters().forEach(System.out::println);
    }
    @Disabled
    @Test
    public void animeById() {
        itemId = 43;
        results = animeDAO.getAnimeById(itemId, results);
        System.out.println(results);
    }
    @Disabled
    @Test
    public void animeByYear() {
        request.setYear(1995);
        Results out = animeDAO.getAnimeByYear(request,results);
        out.getEncounters().forEach(System.out::println);

    }
    @Disabled
    @Test
    public void animeByGenre() {
        itemId = 4;
        Results out = animeDAO.getAnimeByGenreId(itemId,results);
        out.getEncounters().forEach(System.out::println);
    }
    @Disabled
    @Test
    public void animeByPerson() {
        itemId = 80;
        Results out = animeDAO.getAnimeByPersonId(itemId, results);
        out.getEncounters().forEach(System.out::println);
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

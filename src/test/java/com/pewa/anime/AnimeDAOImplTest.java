package com.pewa.anime;

import com.pewa.MediaParse;
import com.pewa.config.ConfigReader;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;



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
        MediaParse<Anime, Integer> getAnime = new AnimeParser();
        Anime test = getAnime.getItem(43);
        animeDAO.addAnime(test);
    }

    @Test
    public void animeByYear() {
        AnimeDAO animeDAO = new AnimeDAOImpl();
        String yearStart = "1993-01-01";
        String yearEnd = "";
        List<Anime> out = animeDAO.getAnimeByYear(yearStart,yearEnd);
        out.forEach(System.out::println);
    }
    @Test
    public void animeByGenre() {
        AnimeDAO animeDAO = new AnimeDAOImpl();
        List<Anime> out = animeDAO.getAnimeByGenre("mech");
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
        List<Anime> out = animeDAO.getAnimeByPerson("ogura");
        out.forEach(System.out::println);
    }
    @Test
    public void animeById() {
        AnimeDAO animeDAO = new AnimeDAOImpl();
        List<Anime> out = animeDAO.getAnimeById(43);
        out.forEach(System.out::println);
    }





}

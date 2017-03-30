package com.pewa.anime;

import com.pewa.MediaParse;
import com.pewa.config.ConfigReader;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;


public class MangaDAOImplTest {
    @BeforeClass
    public static void initTest() {
        ConfigReader.load();
    }

    @Test
    public void addMangaToDb() {
        MediaParse<Manga, Integer> mangaParser = new MangaParser();
        MangaDAO mangaDAO = new MangaDAOImpl();
        Manga test = mangaParser.getItem(30002);
        System.out.println(test);
        mangaDAO.addManga(test);
    }
    @Test
    public void mangaByYear() {
        MangaDAO mangaDAO = new MangaDAOImpl();
        String yearStart = "1970-01-01";
        String yearEnd = "";
        List<Manga> out = mangaDAO.getMangaByYear(yearStart,yearEnd);
        System.out.println("Search by date from " + yearStart + " to " + yearEnd +":");
        out.forEach(System.out::println);
    }
    @Test
    public void mangaSearchQuery() {
        MangaDAO mangaDAO = new MangaDAOImpl();
        String query = "berse";
        List<Manga> out = mangaDAO.getManga(query);
        System.out.println("Search by title \"" + query + "\":");
        out.forEach(System.out::println);
    }
    @Test
    public void mangaById() {
        MangaDAO mangaDAO = new MangaDAOImpl();
        int id = 30002;
        List<Manga> out = mangaDAO.getMangaById(id);
        System.out.println("Search by anilist id " + id + ":");
        out.forEach(System.out::println);
    }
    @Test
    public void animeByPerson() {
        MangaDAO mangaDAO = new MangaDAOImpl();
        String person = "Kent";
        List<Manga> out = mangaDAO.getMangaByPerson(person);
        System.out.println("Search by person \"" + person + "\":");
        out.forEach(System.out::println);
    }
    @Test
    public void mangaByGenre() {
        MangaDAO mangaDAO = new MangaDAOImpl();
        String genre = "Advent";
        List<Manga> out = mangaDAO.getMangaByGenre(genre);
        System.out.println("Search by genre \"" + genre + "\":");
        out.forEach(System.out::println);
    }
}

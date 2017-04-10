package com.pewa.anime;

import com.pewa.MediaParse;
import com.pewa.common.Genre;
import com.pewa.common.Person;
import com.pewa.common.Results;
import com.pewa.config.ConfigReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;


public class MangaDAOImplTest {

    private Results results;
    private MangaDAO mangaDAO;

    @BeforeEach
    public void setObjects() {
        results = new Results();
        mangaDAO = new MangaDAOImpl();
    }

    @Disabled
    @Test
    public void addMangaToDb() {
        MediaParse<Manga, Integer> mangaParser = new MangaParser();
        Manga test = mangaParser.getItem(30002);
        System.out.println(test);
        results = mangaDAO.addManga(test, results);
        System.out.println(results.getRowsAffected());
    }
    @Disabled
    @Test
    public void mangaByYear() {
        String yearStart = "1970-01-01";
        String yearEnd = "";
        Results out = mangaDAO.getMangaByYear(yearStart,yearEnd, results);
        System.out.println("Search by date from " + yearStart + " to " + yearEnd +":");
        out.getMangas().forEach(System.out::println);
    }
    @Disabled
    @Test
    public void mangaSearchQuery() {
        String query = "berse";
        Results out = mangaDAO.getManga(query, results);
        System.out.println("Search by title \"" + query + "\":");
        out.getMangas().forEach(System.out::println);
    }
    @Disabled
    @Test
    public void mangaById() {
        int id = 30002;
        Results out = mangaDAO.getMangaById(id, results);
        System.out.println("Search by anilist id " + id + ":");
        out.getMangas().forEach(System.out::println);
    }
    @Disabled
    @Test
    public void mangaByPerson() {
        Person person = new Person();
        person.setId(30);
        Results out = mangaDAO.getMangaByPerson(person, results);
        System.out.println("Search by person \"" + person + "\":");
        out.getMangas().forEach(System.out::println);
    }
    @Disabled
    @Test
    public void mangaByGenre() {
        Genre genre = new Genre();
        genre.setId(3);
        Results out = mangaDAO.getMangaByGenre(genre, results);
        System.out.println("Search by genre \"" + genre + "\":");
        out.getMangas().forEach(System.out::println);
    }
}

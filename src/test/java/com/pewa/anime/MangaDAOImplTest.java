package com.pewa.anime;

import com.pewa.MediaParse;
import com.pewa.common.Request;
import com.pewa.common.Results;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


public class MangaDAOImplTest {

    private Results results;
    private MangaDAO mangaDAO;
    private Request request;
    private Integer itemId;

    @BeforeEach
    public void setObjects() {
        results = new Results();
        mangaDAO = new MangaDAOImpl();
        request = new Request();
    }

    @Disabled
    @Test
    public void addMangaToDb() {
        MediaParse<Manga, Integer> mangaParser = new MangaParser();
        Manga test = mangaParser.getItem(30002);
        System.out.println(test);
        mangaDAO.addManga(test);

    }
    @Disabled
    @Test
    public void mangaByYear() {
        String yearStart = "1970-01-01";
        String yearEnd = "";
        request.setYear(1970);
        Results out = mangaDAO.getMangaByYear(request);
        System.out.println("Search by date from " + yearStart + " to " + yearEnd +":");
        out.getEncounters().forEach(System.out::println);
    }
    @Disabled
    @Test
    public void mangaSearchQuery() {
        String query = "berse";
        Results out = mangaDAO.getMangaByTitle(query);
        System.out.println("Search by title \"" + query + "\":");
        out.getEncounters().forEach(System.out::println);
    }
    @Disabled
    @Test
    public void mangaById() {
        itemId = 30002;
        Results out = mangaDAO.getMangaById(itemId);
        System.out.println("Search by anilist id " + request + ":");
        out.getEncounters().forEach(System.out::println);
    }
    @Disabled
    @Test
    public void mangaByPerson() {
        itemId = 30;
        Results out = mangaDAO.getMangaByPerson(itemId);
        System.out.println("Search by person \"" + request + "\":");
        out.getEncounters().forEach(System.out::println);
    }
    @Disabled
    @Test
    public void mangaByGenre() {
        itemId = 3;
        Results out = mangaDAO.getMangaByGenre(itemId);
        System.out.println("Search by genre \"" + request + "\":");
        out.getEncounters().forEach(System.out::println);
    }
}

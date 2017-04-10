package com.pewa.controller;

import com.pewa.MediaParse;
import com.pewa.PewaType;
import com.pewa.anime.*;
import com.pewa.common.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by phonik on 2017-04-10.
 */
@RestController
@RequestMapping("/manga/")
public class MangaController {

    @Autowired
    private AnimeMangaSearch animeMangaSearch;

    @Autowired
    @Qualifier(value = "mangaParser")
    private MediaParse<Manga, Integer> mangaParse;

    @Autowired
    private MangaDAO mangaDAO;

    @Autowired
    private Results results;

    private Manga manga;

    @GetMapping(value = "search/{query}")
    public Set<SingleSearchResult> searchExternal(@PathVariable String query) {
        Set<SingleSearchResult> resultSet = animeMangaSearch.aniListSearch(query, PewaType.MANGA);
        return resultSet;
    }

    @GetMapping(value = "searchdb/{query}")
    public Results searchDb(@PathVariable String query) {
        return mangaDAO.getManga(query, results).setMessage();
    }

    @PostMapping(value = "add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Results addAnime(@RequestBody Request anidId) {
        manga = mangaParse.getItem(anidId.getAniId());
        mangaDAO.addManga(manga, results);
        return results.setMessage();
    }
    /*
    * zapytanie POST w body musi mieć jsona, json może mieć tylko jeden pametr, np. {"id":4} i też zostanie prawidłowo zmapowany
    *
    * */
    @PostMapping(value = "person", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Results searchByPerson(@RequestBody Person person) {
        mangaDAO.getMangaByPerson(person, results);
        return results.setMessage();

    }

    @PostMapping(value = "genre", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Results searchByGenre(@RequestBody Genre genre) {
        mangaDAO.getMangaByGenre(genre, results);
        return results.setMessage();
    }

    @PostMapping(value = "date", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Results searchByYear(@RequestBody Request dateSearch) {
        mangaDAO.getMangaByYear(dateSearch.getDateInString(), dateSearch.getDateOutString(), results);
        return results.setMessage();

    }
}

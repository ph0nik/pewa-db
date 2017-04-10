package com.pewa.controller;

import com.pewa.MediaParse;
import com.pewa.PewaType;
import com.pewa.anime.Anime;
import com.pewa.anime.AnimeDAO;
import com.pewa.anime.AnimeMangaSearch;
import com.pewa.common.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by phonik on 2017-04-09.
 */
@RestController
@RequestMapping("/anime/")
public class AnimeController {

    @Autowired
    private AnimeMangaSearch animeMangaSearch;

    @Autowired
    @Qualifier(value = "animeParser")
    private MediaParse<Anime, Integer> animeParse;

    @Autowired
    private AnimeDAO animeDAO;

    @Autowired
    private Results results;

    private Anime anime;

    @GetMapping(value = "search/{query}")
    public Set<SingleSearchResult> searchExternal(@PathVariable String query) {
        Set<SingleSearchResult> resultSet = animeMangaSearch.aniListSearch(query, PewaType.ANIME);
        return resultSet;
    }

    @GetMapping(value = "searchdb/{query}")
    public Results searchDb(@PathVariable String query) {
        return animeDAO.getAnime(query, results).setMessage();
    }

    @PostMapping(value = "add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Results addAnime(@RequestBody Request anidId) {
        anime = animeParse.getItem(anidId.getAniId());
        animeDAO.addAnime(anime);
        return results;
    }

    /*
    * zapytanie POST w body musi mieć jsona, json może mieć tylko jeden pametr, np. {"id":4} i też zostanie prawidłowo zmapowany
    *
    * */
    @PostMapping(value = "person", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Results searchByPerson(@RequestBody Person person) {
        animeDAO.getAnimeByPerson(person, results);
        return results.setMessage();

    }

    @PostMapping(value = "genre", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Results searchByGenre(@RequestBody Genre genre) {
        animeDAO.getAnimeByGenre(genre, results);
        return results.setMessage();
    }

    @PostMapping(value = "date", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Results searchByYear(@RequestBody Request dateSearch) {
        animeDAO.getAnimeByYear(dateSearch.getDateInString(), dateSearch.getDateOutString(), results);
        return results.setMessage();

    }
}

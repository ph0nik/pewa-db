package com.pewa.controller;

import com.pewa.InitAllTables;
import com.pewa.MediaParse;
import com.pewa.PewaType;
import com.pewa.anime.Anime;
import com.pewa.anime.AnimeDAO;
import com.pewa.anime.AnimeMangaSearch;
import com.pewa.common.*;
import com.pewa.request.StatusRequest;
import com.pewa.status.Status;
import com.pewa.status.StatusDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Arrays;
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
    @Qualifier(value = "statusDAOImpl")
    private StatusDAO statusDAO;

    private final String json = MediaType.APPLICATION_JSON_VALUE;

    private PewaType animeType = PewaType.ANIME;
    private final String missingParameters = "Missing parameters: ";
    private final String emptyStatus = "Error: Empty status";

    private Results results;
    private Status status;
    private Anime anime;

    @GetMapping(value = "search/{query}")
    public Results searchExternal(@PathVariable String query) {
        return animeMangaSearch.aniListSearchV2(query, animeType);
    }

    @GetMapping(value = "searchdb/{query}")
    public Results searchDb(@PathVariable String query) {
        return animeDAO.getAnimeByTitle(query);
    }

    @GetMapping(value = "id/{id}")
    public Results getAnime(@PathVariable Integer id) {
        return animeDAO.getAnimeById(id);
    }

    @PostMapping(value = "add", consumes = json)
    public Results addAnime(@RequestBody Status request) {
        if (request == null) {
            return results.setReturnMessage(emptyStatus);
        } else {
            anime = animeParse.getItem(request.getEncounterId());
            Results addAnimeResults = animeDAO.addAnime(anime);
            Results addStatusResults = statusDAO.addStatus(request);
            addAnimeResults.setRowsAffected(addAnimeResults.getRowsAffected() + addStatusResults.getRowsAffected());
            addAnimeResults.setReturnMessage(addAnimeResults.getMessage() + "; " + addStatusResults.getMessage());
            return addAnimeResults;
        }
    }

    @PostMapping(value = "update", consumes = json)
    public Results updateAnime(@RequestBody Request request) {
        anime = animeParse.getItem(request.getExternalId());
        return animeDAO.updateAnime(anime);
    }

    @GetMapping(value = "delete/{id}")
    public Results deleteAnime(@PathVariable Integer id) {
        return animeDAO.deleteAnime(id);
    }

    @GetMapping(value = "person/{personId}")
    public Results searchByPerson(@PathVariable Integer personId) {
        return animeDAO.getAnimeByPersonId(personId);
    }

    @GetMapping(value = "genre/{genreId}")
    public Results searchByGenre(@PathVariable Integer genreId) {
        return animeDAO.getAnimeByGenreId(genreId);
    }

    @GetMapping(value = "date/{year}", consumes = json)
    public Results searchByYear(@PathVariable Integer year) {
        return animeDAO.getAnimeByYear(year);
    }


}

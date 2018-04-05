package com.pewa.controller;

import com.pewa.InitAllTables;
import com.pewa.MediaParse;
import com.pewa.PewaType;
import com.pewa.anime.*;
import com.pewa.common.*;
import com.pewa.request.StatusRequest;
import com.pewa.status.Status;
import com.pewa.status.StatusDAO;
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

    private final String json = MediaType.APPLICATION_JSON_VALUE;
    private final String missingParameters = "Missing parameters: ";
    private final String emptyStatus = "Error: Empty status";

    @Autowired
    private AnimeMangaSearch animeMangaSearch;

    @Autowired
    @Qualifier(value = "mangaParser")
    private MediaParse<Manga, Integer> mangaParse;

    @Autowired
    private MangaDAO mangaDAO;

    @Autowired
    private StatusDAO statusDAO;

    private Results results;
    private PewaType mangaType = PewaType.MANGA;
    private Manga manga;
    private Status status;

    @GetMapping(value = "search/{query}")
    public Results searchExternal(@PathVariable String query) {
        return animeMangaSearch.aniListSearchV2(query, mangaType).setReturnMessage();
    }

    @GetMapping(value = "searchdb/{query}")
    public Results searchDb(@PathVariable String query) {
        return mangaDAO.getMangaByTitle(query);
    }

    @GetMapping(value = "id/{id}")
    public Results getManga(@PathVariable Integer id) {
        return mangaDAO.getMangaById(id);
    }

    @PostMapping(value = "add", consumes = json)
    public Results addAnime(@RequestBody Status request) {
        if (request == null) {
            return results.setReturnMessage(emptyStatus);
        } else {
            manga = mangaParse.getItem(request.getEncounterId());
            Results addMangaResults = mangaDAO.addManga(manga);
            Results addStatusResults = statusDAO.addStatus(request);
            addMangaResults.setRowsAffected(addMangaResults.getRowsAffected() + addStatusResults.getRowsAffected());
            addMangaResults.setReturnMessage(addMangaResults.getMessage() + "; " + addStatusResults.getMessage());
            return addMangaResults;
        }
    }

    @PostMapping(value = "update", consumes = json)
    public Results updateManga(@RequestBody Request request) {
        manga = mangaParse.getItem(request.getExternalId());
        return mangaDAO.updateManga(manga);
    }

    @GetMapping(value = "delete/{id}")
    public Results deleteAnime(@PathVariable Integer id) {
        return mangaDAO.deleteManga(id);
    }


    @GetMapping(value = "person/{personId}")
    public Results searchByPerson(@PathVariable Integer personId) {
        return mangaDAO
                .getMangaByPerson(personId)
                .setReturnMessage();
    }

    @GetMapping(value = "genre/{genreId}")
    public Results searchByGenre(@PathVariable Integer genreId) {
        return mangaDAO
                .getMangaByGenre(genreId)
                .setReturnMessage();
    }

    @PostMapping(value = "date", consumes = json)
    public Results searchByYear(@RequestBody Request dateSearch) {
        return mangaDAO
                .getMangaByYear(dateSearch)
                .setReturnMessage();
    }
}

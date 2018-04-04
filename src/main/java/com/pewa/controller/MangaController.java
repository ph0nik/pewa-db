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

    @Autowired
    private InitAllTables initAllTables;

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
        return mangaDAO
                .getMangaByTitle(query)
                .setReturnMessage();
    }

    @GetMapping(value = "id/{id}")
    public Results getManga(@PathVariable Integer id) {
        return mangaDAO
                .getMangaById(id)
                .setReturnMessage();
    }

    @PostMapping(value = "add", consumes = json)
    public Results addAnime(@RequestBody StatusRequest request) {
        results = new Results();
        status = new Status();
        if (request == null) {
            return results.setReturnMessage(emptyStatus);
        } else if (!request.checkRequiredParameters().isEmpty()) {
            String returnMessage = missingParameters + request.checkRequiredParameters().toString();
            return results.setReturnMessage(returnMessage);
        } else {
            status.setElementType(request.getElementType());
            status.setEncounterId(request.getEncounterId());
            status.setComment(request.getComment());
            status.setEncounterRating(request.getEncounterRating());
            status.setEncounterDate(request.getEncounterDate());
            status.setMediaSource(request.getMediaSource());
            manga = mangaParse.getItem(request.getEncounterId());
            results = mangaDAO.addManga(manga);
            return statusDAO.addStatus(status);
        }
    }

    @PostMapping(value = "update", consumes = json)
    public Results updateManga(@RequestBody Request request) {
        manga = mangaParse.getItem(request.getExternalId());
        return mangaDAO.updateManga(manga);
    }

    @GetMapping(value = "delete/{id}")
    public Results deleteAnime(@PathVariable Integer id) {
        results = mangaDAO.deleteManga(id);
        return initAllTables.cleanAll(results);
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

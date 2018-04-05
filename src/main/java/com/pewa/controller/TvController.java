package com.pewa.controller;

import com.pewa.InitAllTables;
import com.pewa.MediaParse;
import com.pewa.PewaType;
import com.pewa.common.Request;
import com.pewa.common.Results;
import com.pewa.common.SingleSearchResult;
import com.pewa.movie.Movie;
import com.pewa.request.StatusRequest;
import com.pewa.status.Status;
import com.pewa.status.StatusDAO;
import com.pewa.tv.TvShowDAO;
import com.pewa.tv.TvShowEpisode;
import com.pewa.tv.TvShowSearch;
import com.pewa.tv.TvShowSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by phonik on 2017-11-20.
 */
@RestController
@RequestMapping("/tv/")
public class TvController {

    @Autowired
    private TvShowSearch tvShowSearch;

    @Autowired
    private TvShowDAO tvShowDAO;

    @Autowired
    private StatusDAO statusDAO;

    @Autowired
    @Qualifier(value = "tvShowParser")
    private MediaParse<TvShowSummary, Integer> tvShowParser;

    private final PewaType tvType = PewaType.TVSERIES;
    private final String json = MediaType.APPLICATION_JSON_VALUE;
    private final String missingParameters = "Missing parameters: ";
    private final String emptyStatus = "Error: Empty status";
    private Results results;
    private Status status;
    private TvShowSummary tvShowSummary;
    private TvShowEpisode tvShowEpisode;


    @GetMapping(value = "search/{query}")
    public Results searchExternal(@PathVariable String query) {
        return tvShowSearch
                .searchTv(query, new Results())
                .setReturnMessage();
    }

    @GetMapping(value = "searchdb/{query}")
    public Results searchDb(@PathVariable String query) {
        return tvShowDAO.tvshowByTitle(query);
    }

    @GetMapping(value = "id/{id}")
    public Results searchById(@PathVariable Integer id) {
        return tvShowDAO.tvshowById(id);
    }

    @PostMapping(value = "add", consumes = json)
    public Results addTvShow(@RequestBody Status request) {
        if (request == null) {
            return results.setReturnMessage(emptyStatus);
        } else {
            tvShowSummary = tvShowParser.getItem(request.getEncounterId());
            Results addTvResults = tvShowDAO.addTvShow(tvShowSummary);
            Results addStatusResults = statusDAO.addStatus(request);
            addTvResults.setRowsAffected(addTvResults.getRowsAffected() + addStatusResults.getRowsAffected());
            addTvResults.setReturnMessage(addTvResults.getMessage() + "; " + addStatusResults.getMessage());
            return addTvResults;
        }
    }

    @GetMapping(value = "update/{id}")
    public Results updateTvShow(@PathVariable Integer id) {
        tvShowSummary = tvShowParser.getItem(id);
        return tvShowDAO.updateTvShow(tvShowSummary);
    }

    @GetMapping(value = "delete/{id}")
    public Results deleteTvShow(@PathVariable Integer id) {
        return tvShowDAO.deleteTvShow(id);
    }


    @GetMapping(value = "personId")
    public Results searchByPerson(@PathVariable Integer personId) {
        return tvShowDAO.tvshowByPersonId(personId);
    }

    @GetMapping(value = "genreId")
    public Results searchByGenre(@PathVariable Integer genreId) {
        return tvShowDAO.tvshowByGenreId(genreId);
    }

    @GetMapping(value = "langId")
    public Results searchByLanguage(@PathVariable Integer langId) {
        return tvShowDAO.tvshowByLanguageId(langId);
    }

    @GetMapping(value = "countryId")
    public Results searchByCountry(@PathVariable Integer countryId) {
        return tvShowDAO.tvshowByCountryId(countryId);
    }

    @GetMapping(value = "year")
    public Results searchByYear(@PathVariable Integer year) {
        return tvShowDAO.tvshowByYear(year);
    }
}

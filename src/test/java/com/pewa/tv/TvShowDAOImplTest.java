package com.pewa.tv;

import com.pewa.MediaParse;
import com.pewa.MediaSource;
import com.pewa.PewaType;
import com.pewa.common.Results;
import com.pewa.status.Status;
import com.pewa.status.StatusDAO;
import com.pewa.status.StatusDAOImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by phonik on 2017-03-31.
 */
public class TvShowDAOImplTest {

    private Results results;
    private TvShowDAO tvShowDAO;
    private TvShowSearch tvShowSearch;
    private StatusDAO statusDAO;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @BeforeEach
    public void sart() {
        results = new Results();
        tvShowDAO = new TvShowDAOImpl();
        tvShowSearch = new TvShowSearch();
        statusDAO = new StatusDAOImpl();
    }

    @Disabled
    @Test
    public void searchTvSeries() {
        // Narcos id = 2705
        String query = "narcos";
        Results singleSearchResults = tvShowSearch.searchTv(query, new Results());
        singleSearchResults.getEncounters().forEach(System.out::println);
    }

    @Disabled
    @Test
    public void addTvtoDB() {
    // narcos 2705
        //buffy 427
        MediaParse<TvShowSummary, Integer> tvShow = new TvShowParser();
//        TvShowSummary buffy = tvShow.getItem(427);
        Integer id = 427;
        TvShowSummary item = tvShow.getItem(id);
        tvShowDAO.addTvShow(item);
//        System.out.println(item.getEpisodes().get(3));
    }

    @Disabled
    @Test
    public void addTvStatus() {
        Status tvStatus = new Status();
        tvStatus.setElementType(PewaType.TVSERIES);
        tvStatus.setEncounterId(427);
        tvStatus.setEncounterRating(8);
        tvStatus.setEncounterDate(LocalDate.parse("15-11-2017",formatter));
        tvStatus.setMediaSource(MediaSource.COMPUTER);
        tvStatus.setSeason(1);
//        tvStatus.setStatusId();
        String comment = "dobry pierwszy sezon";
        tvStatus.setComment(comment);
        System.out.println(tvStatus);
        Results statusAdded = statusDAO.addStatus(tvStatus);
    }

    @Disabled
    @Test
    public void getTvShow() {
        TvShowDAO tvShowDAO = new TvShowDAOImpl();
        String query = "buffy";
        Results tvShows = tvShowDAO.tvshowByTitle(query);
        System.out.println(tvShows);
    }
    @Disabled
    @Test
    public void getTvShowById() {
        TvShowDAO tvShowDAO = new TvShowDAOImpl();
        Integer tvid = 5;
        Results tvShows = tvShowDAO.tvshowById(tvid);
        System.out.println(tvShows);
    }
    @Disabled
    @Test
    public void getTvShowByPerson() {
        // Person{id=679, name='David Greenwalt', job='executive producer'}
        int personId = 679;
        Results tvshows = tvShowDAO.tvshowByPersonId(personId);
        System.out.println(tvshows);
    }
    @Disabled
    @Test
    public void getTvShowByGenre() {
        // Genre{id=54, genre='Drama'}
        int genreId = 54;
        Results tvshows = tvShowDAO.tvshowByGenreId(genreId);
        System.out.println(tvshows);
    }

    @Disabled
    @Test
    public void getTvShowsByLanguage() {
        // language=Language{id=1, language='English'}
        int langId = 1;
        Results tvshows = tvShowDAO.tvshowByLanguageId(langId);
        System.out.println(tvshows);
    }

    @Disabled
    @Test
    public void getTvShowsbyContry() {
        // country=Country{id=3, country='United States'}
        int countryId = 3;
        Results tvshows = tvShowDAO.tvshowByCountryId(countryId);
        System.out.println(tvshows);
    }

    @Disabled
    @Test
    public void getTvShowByYear() {
        int year = 1997;
        Results tvshow = tvShowDAO.tvshowByYear(year);
        System.out.println(tvshow);
    }



}
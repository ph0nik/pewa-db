package com.pewa.tv;

import com.pewa.MediaParse;
import com.pewa.config.ConfigReader;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Created by phonik on 2017-03-31.
 */
public class TvShowDAOImplTest {
    @Disabled
    @Test
    public void addTvtoDB() {
        TvShowDAO tvShowDAO = new TvShowDAOImpl();
        MediaParse<TvShowSummary, Integer> tvShow = new TvShowParser();
        TvShowSummary buffy = tvShow.getItem(427);
        tvShowDAO.addTvShow(buffy);
    }
    @Disabled
    @Test
    public void getTvShow() throws Exception {
        TvShowDAO tvShowDAO = new TvShowDAOImpl();
        List<TvShowSummary> tvShows = tvShowDAO.getTvShow("uffy");
        System.out.println(tvShows.get(0).getEpisodes().get(0));
    }
    @Disabled
    @Test
    public void getTvShowById() throws Exception {

    }
    @Disabled
    @Test
    public void getTvShowByPerson() throws Exception {

    }
    @Disabled
    @Test
    public void getTvShowByGenre() throws Exception {

    }
    @Disabled
    @Test
    public void getTvShowByYear() throws Exception {

    }



}
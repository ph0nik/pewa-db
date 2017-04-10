package com.pewa.tv;

import com.pewa.anime.Anime;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Created by phonik on 2017-03-31.
 */
public interface TvShowDAO {


        void addTvShow(TvShowSummary tvshow);
        List<TvShowSummary> getTvShow(String query);
        List<TvShowSummary> getTvShowById(int id);
        List<TvShowSummary> getTvShowByPerson(String query);
        List<TvShowSummary> getTvShowByGenre(String query);
        List<TvShowSummary> getTvShowByYear(String x, String y);
}

package com.pewa.tv;

import com.pewa.anime.Anime;
import com.pewa.common.PewaSelect;
import com.pewa.common.Request;
import com.pewa.common.Results;
import com.pewa.movie.Movie;
import com.pewa.movie.tmdb.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Created by phonik on 2017-03-31.
 */
public interface TvShowDAO {

        //TODO przygotować kontroler seriali wraz zodznaczaniem obejrzanych odcinków
        Results addTvShow(TvShowSummary tvshow);

        Results updateTvShow(TvShowSummary tvshow);

        Results deleteTvShow(Integer tvshowid);

        Results tvshowByTitle(String query);

        Results tvshowById(Integer dbId);

        Results tvshowByPersonId(Integer person);

        Results tvshowByGenreId(Integer genre);

        Results tvshowByLanguageId(Integer lang);

        Results tvshowByCountryId(Integer count);

        Results tvshowByYear(Integer date);

        TvShowSummary getTvShowByExternalId(Integer id);

}

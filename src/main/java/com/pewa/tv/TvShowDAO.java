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
        Results addTvShow(TvShowSummary tvshow, Results results);

        Results updateTvShow(TvShowSummary tvshow, Results results);

        Results deleteTvShow(Integer tvshowid, Results results);

        Results tvshowByTitle(String query, Results results);

        Results tvshowById(Integer dbId, Results results);

        Results tvshowByPersonId(Integer person, Results results);

        Results tvshowByGenreId(Integer genre, Results results);

        Results tvshowByLanguageId(Integer lang, Results results);

        Results tvshowByCountryId(Integer count, Results results);

        Results tvshowByYear(Integer date, Results results);

        Results getObjectById(PewaSelect id, Results results);

        TvShowSummary getTvShowByExternalId(Integer id);

}

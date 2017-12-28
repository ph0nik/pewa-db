package com.pewa.anime;

/*

*@author phonik
*@see AnimeDAOImpl
*
* */

import com.pewa.common.Genre;
import com.pewa.common.Person;
import com.pewa.common.Request;
import com.pewa.common.Results;
import com.pewa.movie.tmdb.Result;
import org.springframework.web.bind.annotation.GetMapping;

import java.lang.ref.ReferenceQueue;
import java.util.List;

 public interface AnimeDAO {

    /*
    * Adds Anime element into database
    * */
    Results addAnime(Anime anime, Results results);

    Results updateAnime(Anime anime, Results results);

    Results deleteAnime(Integer anime, Results results);
    /*
    * Returns List of elements of Anime type based on query of String type.
    * */
    Results getAnimeByTitle(String query, Results results);
    /*
    * Returns List of elements of Anime type based on query ofInteger type.
    * */

    Results getAnimeById(Integer databaseId, Results results);
    /*
    * Returns List of elements of Anime type based on query of String type.
    * */
    Results getAnimeByPersonId(Integer person, Results results);
    /*
    * Returns List of elements of Anime type based on query of String type.
    * */
    Results getAnimeByGenreId(Integer genre, Results results);
    /*
    * Return List of Elements of Anime type based on queriees of String type.
    * Inputs are converted internally into LocalDate objects.
    * */
    Results getAnimeByYear(Request date, Results results);


}

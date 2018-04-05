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
    Results addAnime(Anime anime);

    Results updateAnime(Anime anime);

    Results deleteAnime(Integer anime);
    /*
    * Returns List of elements of Anime type based on query of String type.
    * */
    Results getAnimeByTitle(String query);
    /*
    * Returns List of elements of Anime type based on query ofInteger type.
    * */

    Results getAnimeById(Integer databaseId);
    /*
    * Returns List of elements of Anime type based on query of String type.
    * */
    Results getAnimeByPersonId(Integer person);
    /*
    * Returns List of elements of Anime type based on query of String type.
    * */
    Results getAnimeByGenreId(Integer genre);
    /*
    * Return List of Elements of Anime type based on queriees of String type.
    * Inputs are converted internally into LocalDate objects.
    * */
    Results getAnimeByYear(Integer year);


}

package com.pewa.anime;

/*

*@author phonik
*@see AnimeDAOImpl
*
* */

import com.pewa.common.Genre;
import com.pewa.common.Person;
import com.pewa.common.Results;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

 public interface AnimeDAO {

    /*
    * Adds Anime element into database
    * */
    void addAnime(Anime anime);
    /*
    * Returns List of elements of Anime type based on query of String type.
    * */
    Results getAnime(String query, Results results);
    /*
    * Returns List of elements of Anime type based on query ofInteger type.
    * */
    Anime getAnimeById(Integer id);
    /*
    * Returns List of elements of Anime type based on query of String type.
    * */
    Results getAnimeByPerson(Person person, Results results);
    /*
    * Returns List of elements of Anime type based on query of String type.
    * */
    Results getAnimeByGenre(Genre genre, Results results);
    /*
    * Return List of Elements of Anime type based on queriees of String type.
    * Inputs are converted internally into LocalDate objects.
    * */
    Results getAnimeByYear(String x, String y, Results results);
}

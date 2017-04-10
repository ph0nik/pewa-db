package com.pewa.anime;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by phonik on 2017-04-03.
 */
public interface AnimeMapper {

    /*
    * Adds Anime element into database
    * */
    void insertAnime(Anime anime);

    /*
    * Returns List of elements of Anime type based on query of String type.
    * */
    List<Anime> byTitleAni(String query);

    /*
    * Returns List of elements of Anime type based on query ofInteger type.
    * */
    Anime ByIdAni(Integer id);

    /*
    * Returns List of elements of Anime type based on query of String type.
    * */
    List<Anime> byPersonAni(String query);
    /*
    * Returns List of elements of Anime type based on query of String type.
    * */
    List<Anime> byGenreAni(String query);

    /*
    * Return List of Elements of Anime type based on queriees of String type.
    * Inputs are converted internally into LocalDate objects.
    * */
    List<Anime> byYearAni(String x, String y);
}

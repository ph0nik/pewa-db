package com.pewa.controller;

import com.pewa.anime.Anime;
import com.pewa.book.Book;
import com.pewa.movie.Movie;
import com.pewa.music.Album;
import com.pewa.tv.TvShowSummary;

/**
 * Created by phonik on 2017-03-15.
 */
public interface EncounterDAO {

    Boolean addEncounter(Movie movie);
    Boolean addEncounter(Anime movie);
    Boolean addEncounter(Book movie);
    Boolean addEncounter(Album movie);
    Boolean addEncounter(TvShowSummary tvshow);
    void editEncounter(int id);


}

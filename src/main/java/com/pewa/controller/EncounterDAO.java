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

    public Boolean addEncounter(Movie movie);
    public Boolean addEncounter(Anime movie);
    public Boolean addEncounter(Book movie);
    public Boolean addEncounter(Album movie);
    public Boolean addEncounter(TvShowSummary tvshow);
    public void editEncounter(int id);


}

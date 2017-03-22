package com.pewa.anime;

import com.pewa.book.Book;

import java.util.List;

/**
 * Created by phonik on 2017-03-22.
 */
public interface AnimeDAO {
    void addAnime(Anime anime);
    List<Anime> getAnime(String query);
    List<Anime> getAnimeById(int id);
    List<Anime> animeByPerson(String query);
    List<Anime> animeByGenre(String query);
    List<Anime> animeByYear(String x, String y);
}

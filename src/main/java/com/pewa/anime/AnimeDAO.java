package com.pewa.anime;

import java.util.List;

public interface AnimeDAO {
    void addAnime(Anime anime);
    List<Anime> getAnime(String query);
    List<Anime> getAnimeById(int id);
    List<Anime> getAnimeByPerson(String query);
    List<Anime> getAnimeByGenre(String query);
    List<Anime> getAnimeByYear(String x, String y);
}

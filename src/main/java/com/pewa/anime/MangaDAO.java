package com.pewa.anime;

import java.util.List;

public interface MangaDAO {

        void addManga(Manga anime);

        List<Manga> getManga(String query);
        List<Manga> getMangaById(int id);
        List<Manga> getMangaByPerson(String query);
        List<Manga> getMangaByGenre(String query);
        List<Manga> getMangaByYear(String x, String y);

}

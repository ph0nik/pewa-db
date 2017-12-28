package com.pewa.anime;

import com.pewa.common.Request;
import com.pewa.common.Results;

public interface MangaDAO {

        Results addManga(Manga anime, Results results);

        Results updateManga(Manga manga, Results results);

        Results deleteManga(Integer manga, Results results);

        Results getMangaByTitle(String query, Results results);

        Results getMangaById(Integer mangaid, Results results);

        Results getMangaByPerson(Integer person, Results results);

        Results getMangaByGenre(Integer genre, Results results);

        Results getMangaByYear(Request date, Results results);

}

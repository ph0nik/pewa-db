package com.pewa.anime;

import com.pewa.common.Request;
import com.pewa.common.Results;

public interface MangaDAO {

        Results addManga(Manga anime);

        Results updateManga(Manga manga);

        Results deleteManga(Integer manga);

        Results getMangaByTitle(String query);

        Results getMangaById(Integer mangaid);

        Results getMangaByPerson(Integer person);

        Results getMangaByGenre(Integer genre);

        Results getMangaByYear(Request date);

}

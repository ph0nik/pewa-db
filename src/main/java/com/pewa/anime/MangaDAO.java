package com.pewa.anime;

import com.pewa.common.Genre;
import com.pewa.common.Person;
import com.pewa.common.Results;

import java.util.List;

public interface MangaDAO {

        Results addManga(Manga anime, Results results);

        Results getManga(String query, Results results);

        Results getMangaById(int id, Results results);

        Results getMangaByPerson(Person person, Results results);

        Results getMangaByGenre(Genre genre, Results results);

        Results getMangaByYear(String x, String y, Results results);

}

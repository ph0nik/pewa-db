package com.pewa.book;


import com.pewa.anime.Manga;
import com.pewa.common.*;
import com.pewa.movie.tmdb.Result;
import com.pewa.status.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Set;

/**
 * Created by phonik on 2017-03-16.
 */
public interface BookDAO {

    Results addBook(Book book, Results results);

    Results delBook(Integer book, Results results);

    Results udpateBook(Book book, Results results);

    Results getBook(String query, Results results);
    /*
    * Returns List of object Book based on String id parameter (biblionetka id).
    * */
    Results getBookById(Integer id, Results results);
    /*
    * Returns List of Book objects based on String query, search through people parameter
    * */
    Results booksByPerson(Integer person, Results results);
    Results booksByGenre(Integer genre, Results results);
    Results booksByLanguage(String language, Results results);
    Results booksByYear(Request date, Results results);
}

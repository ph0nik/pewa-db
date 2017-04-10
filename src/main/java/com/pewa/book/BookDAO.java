package com.pewa.book;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Set;

/**
 * Created by phonik on 2017-03-16.
 */
public interface BookDAO {


/*
* Inserts Book object into database
* */
    void addBook(Book movieInfo);

    /*
    * Returns List of object Book based on String query, searches through original_title and polish_title parameters.
    * */
    List<Book> getBook(String query);
    /*
    * Returns List of object Book based on String id parameter (biblionetka id).
    * */
    List<Book> getBookById(int id);
    /*
    * Returns List of Book objects based on String query, search through people parameter
    * */
    List<Book> booksByPerson(String query);
    List<Book> booksByGenre(String query);
    List<Book> booksByLanguage(String query);
    List<Book> booksByYear(int x, int y);
}

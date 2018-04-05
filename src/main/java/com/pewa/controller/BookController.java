package com.pewa.controller;

import com.pewa.MediaParse;
import com.pewa.PewaType;
import com.pewa.book.Book;
import com.pewa.book.BookDAO;
import com.pewa.book.BookSearch;
import com.pewa.common.*;
import com.pewa.status.Status;
import com.pewa.status.StatusDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by phonik on 2017-05-28.
 */

@RestController
@RequestMapping("/book/")
public class BookController {

    @Autowired
    private BookSearch bookSearch;

    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private StatusDAO statusDAO;

    @Autowired
    @Qualifier(value = "bookParser")
    MediaParse<Book, Integer> bookParser;

    private Results results;
    private Request request;
    private Status status;
    private PewaType bookType = PewaType.BOOK;
    private final String json = MediaType.APPLICATION_JSON_VALUE;
    private final String missingParameters = "Missing parameters: ";
    private final String emptyStatus = "Error: Empty status";

    Book book;

    @GetMapping(value = "search/{query}")
    public Results searchExternal(@PathVariable String query) {
        return bookSearch
                .bookSearchResultSet(query, new Results())
                .setReturnMessage();
    }

    @GetMapping(value = "searchdb/{query}")
    public Results searchDb(@PathVariable String query) {
        return bookDAO.getBook(query);
    }

    @GetMapping(value = "id/{id}")
    public Results getBookById(@PathVariable Integer id) {
        return bookDAO.getBookById(id);
    }

    @PostMapping(value = "add", consumes = json)
    public Results addBook(@RequestBody Status request) {
        if (request == null) {
            return results.setReturnMessage(emptyStatus);
        } else {
            book = bookParser.getItem(request.getEncounterId());
            Results addBookResults = bookDAO.addBook(book);
            Results addStatusResults = statusDAO.addStatus(request);
            addBookResults.setRowsAffected(addBookResults.getRowsAffected() + addStatusResults.getRowsAffected());
            addBookResults.setReturnMessage(addBookResults.getMessage() + "; " + addStatusResults.getMessage());
            return addBookResults;
        }
    }

    @GetMapping(value = "delete/{id}")
    public Results deleteBook(@PathVariable Integer id) {
        return bookDAO.deleteBook(id);
    }

    @PostMapping(value = "update", consumes = json)
    public Results updateBook(@RequestBody Request request) {
        book = bookParser.getItem(request.getExternalId());
        return bookDAO.udpateBook(book);
    }

    @GetMapping(value = "person/{personId}")
    public Results searchByPerson(@PathVariable Integer personId) {
        return bookDAO.booksByPerson(personId);
    }

    @GetMapping(value = "genre/{genreId}")
    public Results searchByGenre(@PathVariable Integer genreId) {
        return bookDAO.booksByGenre(genreId);
    }

    @PostMapping(value = "date", consumes = json)
    public Results searchByYear(@RequestBody Request dateSearch) {
        return bookDAO.booksByYear(dateSearch);
    }

    @GetMapping(value = "language/{language}")
    public Results searchByLanguage(@PathVariable String language) {
        return bookDAO.booksByLanguage(language);
    }

}

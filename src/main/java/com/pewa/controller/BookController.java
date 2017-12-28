package com.pewa.controller;

import com.pewa.InitAllTables;
import com.pewa.MediaParse;
import com.pewa.PewaType;
import com.pewa.book.Book;
import com.pewa.book.BookDAO;
import com.pewa.book.BookSearch;
import com.pewa.common.*;
import com.pewa.movie.tmdb.Result;
import com.pewa.request.StatusRequest;
import com.pewa.status.Status;
import com.pewa.status.StatusDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

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

    @Autowired
    private InitAllTables initAllTables;

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
        return bookDAO
                .getBook(query, new Results());
    }

    @GetMapping(value = "id/{id}")
    public Results getBookById(@PathVariable Integer id) {
        return bookDAO
                .getBookById(id, new Results())
                .setReturnMessage();
    }

    @PostMapping(value = "add", consumes = json)
    public Results addBook(@RequestBody StatusRequest request) {
        results = new Results();
        status = new Status();
        if (request == null) {
            return results.setReturnMessage(emptyStatus);
        } else if (!request.checkRequiredParameters().isEmpty()) {
            String returnMessage = missingParameters + request.checkRequiredParameters().toString();
            return results.setReturnMessage(returnMessage);
        } else {
            status.setElementType(request.getElementType());
            status.setEncounterId(request.getEncounterId());
            status.setComment(request.getComment());
            status.setEncounterRating(request.getEncounterRating());
            status.setEncounterDate(request.getEncounterDate());
            status.setMediaSource(request.getMediaSource());
            book = bookParser.getItem(request.getEncounterId());
            results = bookDAO.addBook(book, new Results());
            return statusDAO.addStatus(status, results);
        }
    }

    @GetMapping(value = "delete/{id}")
    public Results deleteBook(@PathVariable Integer id) {
        results = bookDAO.delBook(id, new Results());
        return initAllTables.cleanAll(results);
    }

    @PostMapping(value = "update", consumes = json)
    public Results updateBook(@RequestBody Request request) {
        book = bookParser.getItem(request.getExternalId());
        results = bookDAO.udpateBook(book, new Results());
        return results;
    }

    @GetMapping(value = "person/{personId}")
    public Results searchByPerson(@PathVariable Integer personId) {
        return bookDAO
                .booksByPerson(personId, new Results())
                .setReturnMessage();
    }

    @GetMapping(value = "genre/{genreId}")
    public Results searchByGenre(@PathVariable Integer genreId) {
        return bookDAO
                .booksByGenre(genreId, new Results())
                .setReturnMessage();
    }

    @PostMapping(value = "date", consumes = json)
    public Results searchByYear(@RequestBody Request dateSearch) {
        return bookDAO
                .booksByYear(dateSearch, new Results())
                .setReturnMessage();
    }

    @GetMapping(value = "language/{language}")
    public Results searchByLanguage(@PathVariable String language) {
        return bookDAO
                .booksByLanguage(language, new Results())
                .setReturnMessage();
    }

}

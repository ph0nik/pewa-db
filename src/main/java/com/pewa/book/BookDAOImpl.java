package com.pewa.book;

import com.pewa.InitAllTables;
import com.pewa.PewaType;
import com.pewa.common.AbstractMediaDAO;
import com.pewa.common.Request;
import com.pewa.common.Results;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class BookDAOImpl extends AbstractMediaDAO implements BookDAO {

    public static final Logger log = LogManager.getLogger(BookDAOImpl.class);
    private List<String> mapperList = new ArrayList<>();
    private String infoField = "";

    public BookDAOImpl() {
        super(PewaType.BOOK);
        tableManagement = new InitAllTables(PewaType.BOOK);
    }

    public List<String> getMapperList() {
        return mapperList;
    }

    public String getInfoField() {
        return infoField;
    }

    public Results addBook(Book book) {
        infoField = book.getOriginalTitle();
        mapperList = Arrays.asList(
                "book-mapper.insertBook",
                "book-mapper.insertPeopleBoo",
                "book-mapper.insertPeopleBridgeBoo",
                "book-mapper.insertGenreBoo",
                "book-mapper.insertGenreBridgeBoo",
                "book-mapper.insertForm"
        );
        return add(book);
    }

    // deletes book object from database
    public Results deleteBook(Integer bookid) {
        mapperList = Arrays.asList("book-mapper.deleteBook");
        Results delete = delete(bookid);
        return getTablesManagement().cleanAll(delete);
    }

    public Results udpateBook(Book book) {
        infoField = book.getOriginalTitle();
        mapperList = Arrays.asList("book-mapper.updateBook");
        return update(book);
    }

    public Results getBook(String request) {
        mapperList = Arrays.asList("book-mapper.byTitle");
        return search(request);
    }

    public Results getBookById(Integer bookId) {
        mapperList = Arrays.asList("book-mapper.ById");
        return get(bookId);
    }

    public Results booksByPerson(Integer personId) {
        mapperList = Arrays.asList("book-mapper.byPerson");
        return get(personId);
    }

    public Results booksByGenre(Integer genreId)  {
        mapperList = Arrays.asList("book-mapper.byGenre");
        return get(genreId);
    }

    public Results booksByYear(Request date) {
        Integer year = date.getYear();
        mapperList = Arrays.asList("book-mapper.byYear");
        return get(year);
    }

    public Results booksByLanguage(String language) {
        mapperList = Arrays.asList("book-mapper.byLanguage");
        return language(language);
    }


}

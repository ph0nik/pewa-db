package com.pewa.book;

import com.pewa.PewaType;
import com.pewa.common.MediaDAO;
import com.pewa.common.Request;
import com.pewa.common.Results;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class BookDAOImpl extends MediaDAO implements BookDAO {

    public static final Logger log = LogManager.getLogger(BookDAOImpl.class);
    private List<String> mapperList = new ArrayList<>();
    private String infoField = "";

    public BookDAOImpl() {
        super(PewaType.BOOK);
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
        return super.add(book);
    }

    public Results delBook(Integer bookid) {
        mapperList = Arrays.asList("book-mapper.deleteBook");
        return super.delete(bookid);
    }

    public Results udpateBook(Book book) {
        infoField = book.getOriginalTitle();
        mapperList = Arrays.asList("book-mapper.updateBook");
        return super.update(book);
    }

    public Results getBook(String request) {
        mapperList = Arrays.asList("book-mapper.byTitle");
        return super.search(request);
    }

    public Results getBookById(Integer bookId) {
        mapperList = Arrays.asList("book-mapper.ById");
        return super.get(bookId);
    }

    public Results booksByPerson(Integer personId) {
        mapperList = Arrays.asList("book-mapper.byPerson");
        return super.get(personId);
    }

    public Results booksByGenre(Integer genreId)  {
        mapperList = Arrays.asList("book-mapper.byGenre");
        return super.get(genreId);
    }

    public Results booksByYear(Request date) {
        Integer year = date.getYear();
        mapperList = Arrays.asList("book-mapper.byYear");
        return super.get(year);
    }

    public Results booksByLanguage(String language) {
        mapperList = Arrays.asList("book-mapper.byLanguage");
        return super.language(language);
    }


}

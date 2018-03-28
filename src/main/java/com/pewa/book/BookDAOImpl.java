package com.pewa.book;

import com.pewa.PewaType;
import com.pewa.common.Encounter;
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
    private final PewaType bookType = PewaType.BOOK;
    private List<Encounter> output;
    private List<String> mapperList = new ArrayList<>();
    private String returnMessage = "";
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

    public Results addBook(Book book, Results results) {
        infoField = book.getOriginalTitle();
        mapperList = Arrays.asList(
                "book-mapper.insertBook",
                "book-mapper.insertPeopleBoo",
                "book-mapper.insertPeopleBridgeBoo",
                "book-mapper.insertGenreBoo",
                "book-mapper.insertGenreBridgeBoo",
                "book-mapper.insertForm"
        );
        return super.add(book, results);
    }

    public Results delBook(Integer bookid, Results results) {
        mapperList = Arrays.asList("book-mapper.deleteBook");
        return super.delete(bookid, results);
    }

    public Results udpateBook(Book book, Results results) {
        infoField = book.getOriginalTitle();
        mapperList = Arrays.asList("book-mapper.updateBook");
        return super.update(book, results);
    }

    public Results getBook(String request, Results results) {
        mapperList = Arrays.asList("book-mapper.byTitle");
        return super.search(request, results);
    }

    public Results getBookById(Integer bookId, Results results) {
        mapperList = Arrays.asList("book-mapper.ById");
        return super.get(bookId, results);
    }

    public Results booksByPerson(Integer personId, Results results) {
        mapperList = Arrays.asList("book-mapper.byPerson");
        return super.get(personId, results);
    }

    public Results booksByGenre(Integer genreId, Results results)  {
        mapperList = Arrays.asList("book-mapper.byGenre");
        return super.get(genreId, results);
    }

    public Results booksByYear(Request date, Results results) {
        Integer year = date.getYear();
        mapperList = Arrays.asList("book-mapper.byYear");
        return super.get(year, results);
    }

    public Results booksByLanguage(String language, Results results) {
        mapperList = Arrays.asList("book-mapper.byLanguage");
        return super.language(language, results);
    }


}

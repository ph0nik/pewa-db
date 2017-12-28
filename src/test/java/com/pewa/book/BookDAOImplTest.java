package com.pewa.book;

import com.pewa.MediaParse;
import com.pewa.MediaSource;
import com.pewa.PewaType;
import com.pewa.common.Genre;
import com.pewa.common.Person;
import com.pewa.common.Request;
import com.pewa.common.Results;
import com.pewa.status.Status;
import com.pewa.status.StatusDAO;
import com.pewa.status.StatusDAOImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;


/**
 * Created by phonik on 2017-03-19.
 */
public class BookDAOImplTest {

    private Results results;
    private BookDAO nowaKsiazka;
    private StatusDAO statusDAO;
    private Request request;

    @BeforeEach
    public void setObjects() {
        results = new Results();
        nowaKsiazka = new BookDAOImpl();
        statusDAO = new StatusDAOImpl();
        request = new Request();
    }

    // book.aspx?id=78
    // book.aspx?id=301
    @Disabled
    @Test
    public void insertBook() {
        MediaParse<Book, Integer> bookScraper = new BookParser();
        Integer id = 301;
        Book ksiazka = bookScraper.getItem(id);
        nowaKsiazka.addBook(ksiazka, results);
        Status status = new Status();
        status.setElementType(PewaType.BOOK);
        status.setComment("w pustyni");
        status.setEncounterDate(LocalDate.now());
        status.setMediaSource(MediaSource.EBOOK);
        status.setEncounterRating(9);
        status.setEncounterId(ksiazka.getExternalBookId());
        Results rs  = statusDAO.addStatus(status, results);
        System.out.println(rs);
    }
    @Disabled
    @Test
    public void checkBasicSQL() {
        String title = "szcz";
        Results test = nowaKsiazka.getBook(title, results);
        System.out.println(test.toString());
    }

    // 'book.aspx?id=301' - w pustyni i w puszczy
    @Disabled
    @Test
    public void checkBookById(){
        Integer id = 1;
        Results test = nowaKsiazka.getBookById(id, results);
        System.out.println(test.toString());
    }
    @Disabled
    @Test
    public void checkBooksByPerson() {

        Integer person = 54;
        Results test = nowaKsiazka.booksByPerson(person, results);
        System.out.println(test.toString());
    }
    @Disabled
    @Test
    public void checkBooksByGenre() {
        Integer genre = 2;
        Results test = nowaKsiazka.booksByGenre(genre, results);
        System.out.println(test.toString());
    }
    @Disabled
    @Test
    public void checkBooksByLanguage() {
        String language = "angielski";
        Results test = nowaKsiazka.booksByLanguage(language, results);
        System.out.println(test.toString());
    }
    @Disabled
    @Test
    public void checkBooksByYear() {
        Request year = new Request();
        year.setYear(2000);
        Results test = nowaKsiazka.booksByYear(year, results);
        System.out.println(test.toString());
    }


}

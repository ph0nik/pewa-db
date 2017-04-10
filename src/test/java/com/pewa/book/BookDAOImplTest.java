package com.pewa.book;

import com.pewa.MediaParse;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;


/**
 * Created by phonik on 2017-03-19.
 */
public class BookDAOImplTest {


    // book.aspx?id=78
    // book.aspx?id=301
    @Disabled
    @Test
    public void insertBook() {
        BookDAO nowaKsiazka = new BookDAOImpl();
        MediaParse<Book, Integer> bookScraper = new BookParser();
        Book ksiazka = bookScraper.getItem(301);
        nowaKsiazka.addBook(ksiazka);
    }
    @Disabled
    @Test
    public void checkBasicSQL() {
        BookDAO nowaKsiazka = new BookDAOImpl();
        List<Book> test = nowaKsiazka.getBook("obbi");
        test.forEach(System.out::println);
    }

    // 'book.aspx?id=301' - w pustyni i w puszczy
    @Disabled
    @Test
    public void checkBookById(){
        BookDAO nowaKsiazka = new BookDAOImpl();
        List<Book> test = nowaKsiazka.getBookById(78);
        test.forEach(System.out::println);
    }
    @Disabled
    @Test
    public void checkBooksByPerson() {
        BookDAO nowaKsiazka = new BookDAOImpl();
        List<Book> test = nowaKsiazka.booksByPerson("s");
        test.forEach(System.out::println);
    }
    @Disabled
    @Test
    public void checkBooksByGenre() {
        BookDAO nowaKsiazka = new BookDAOImpl();
        List<Book> test = nowaKsiazka.booksByGenre("fant");
        test.forEach(System.out::println);
    }
    @Disabled
    @Test
    public void checkBooksByLanguage() {
        BookDAO nowaKsiazka = new BookDAOImpl();
        List<Book> test = nowaKsiazka.booksByLanguage("angie");
        test.forEach(System.out::println);
    }
    @Disabled
    @Test
    public void checkBooksByYear() {
        BookDAO nowaKsiazka = new BookDAOImpl();
        List<Book> test = nowaKsiazka.booksByYear(1900,2000);
        test.forEach(System.out::println);
    }


}

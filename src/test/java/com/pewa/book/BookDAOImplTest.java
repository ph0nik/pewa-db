package com.pewa.book;

import com.pewa.MediaParse;
import com.pewa.config.ConfigReader;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.print.attribute.standard.Media;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by phonik on 2017-03-19.
 */
public class BookDAOImplTest {

    @BeforeClass
    public static void initTest() {

        ConfigReader.load();
    }

    @Test
    public void checkBookInit() {
        BookDAO nowaKsiazka = new BookDAOImpl();
        nowaKsiazka.initBook();
    }

    // book.aspx?id=78
    // book.aspx?id=301
    @Test
    public void insertBook() {
        BookDAO nowaKsiazka = new BookDAOImpl();
        MediaParse<Book, Integer> bookScraper = new BookParser();
        Book ksiazka = bookScraper.getItem(78);
        nowaKsiazka.addBook(ksiazka);
    }
    @Test
    public void checkBasicSQL() {
        BookDAO nowaKsiazka = new BookDAOImpl();
        List<Book> test = nowaKsiazka.getBook("obbi");
        test.forEach(System.out::println);
    }

    // 'book.aspx?id=301' - w pustyni i w puszczy
    @Test
    public void checkBookById(){
        BookDAO nowaKsiazka = new BookDAOImpl();
        List<Book> test = nowaKsiazka.getBookById(78);
        test.forEach(System.out::println);
    }
    @Test
    public void checkBooksByPerson() {
        BookDAO nowaKsiazka = new BookDAOImpl();
        List<Book> test = nowaKsiazka.booksByPerson("s");
        test.forEach(System.out::println);
    }
    @Test
    public void checkBooksByGenre() {
        BookDAO nowaKsiazka = new BookDAOImpl();
        List<Book> test = nowaKsiazka.booksByGenre("fant");
        test.forEach(System.out::println);
    }
    @Test
    public void checkBooksByLanguage() {
        BookDAO nowaKsiazka = new BookDAOImpl();
        List<Book> test = nowaKsiazka.booksByLanguage("angie");
        test.forEach(System.out::println);
    }
    @Test
    public void checkBooksByYear() {
        BookDAO nowaKsiazka = new BookDAOImpl();
        List<Book> test = nowaKsiazka.booksByYear(1900,2000);
        test.forEach(System.out::println);
    }


}

package com.pewa.book;

import com.pewa.MediaParse;
import com.pewa.PewaType;
import com.pewa.common.Request;
import com.pewa.common.Results;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookSearchTest {

    private Request request;

    @BeforeEach
    public void init() {
        request = new Request();
    }

    @Disabled
    @Test
    public void showSearchResults() {
        BookSearch bookSearch = new BookSearch();
        String request = "człowiek dick";
        Results wynik = bookSearch.bookSearchResultSet(request, new Results());
        wynik.getEncounters().stream().limit(5).forEach(System.out::println);
        int iloscWynikow = wynik.getEncounters().size();
        assertEquals(wynik.getEncounters().size(), iloscWynikow);
        System.out.println("ilosc wynikow: " + wynik.getEncounters().size());
    }

    @Disabled
    @Test
    public void tryWithEmptyResults() {
        BookSearch bookSearch = new BookSearch();
        String request = "asdasd";
        Results wynik = bookSearch.bookSearchResultSet(request, new Results());
        int emptyTreeSetSize = 0;
        assertEquals(wynik.getEncounters().size(), emptyTreeSetSize);
    }
    @Disabled
    // SingleSearchResult{url='book.aspx?id=335096', desc='Car Maksymilian: Widowisko ludowe na Rusi (Gołąbek Józef)'}
    @Test
    public void scrapeFoundItem() {
        MediaParse<Book, Integer> bookScraper = new BookParser();
        Integer id = 78;
        Book carMaksymilian = bookScraper.getItem(id);
        String tytul = "Car Maksymilian: Widowisko ludowe na Rusi";
        //assertEquals(carMaksymilian.getOriginalTitle(), tytul);
        System.out.println(carMaksymilian);

    }
    @Disabled
    @Test
    public void scrapeFoundItem2() {
        MediaParse<Book, Integer> bookScraper = new BookParser();
        Integer id = 78;
        Book carMaksymilian = bookScraper.getItem(id);
        System.out.println(carMaksymilian);

    }

    @Disabled
    @Test
    public void pewaTest() {
        PewaType movie = PewaType.MOVIE;
        System.out.println(movie.getPewaTypeName());
        System.out.println(movie.getPewaTypeValue());

    }
}

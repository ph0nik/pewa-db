import com.pewa.SingleSearchResult;
import com.pewa.book.Book;
import com.pewa.book.BookScraper;
import com.pewa.book.BookSearch;
import com.pewa.config.ConfigReader;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;

public class BookSearchTest {

    @BeforeClass
    public static void initTest() {
        ConfigReader.load();
    }

    @Test
    public void showSearchResults() {
        Set<SingleSearchResult> wynik = BookSearch.bookSearchResultSet("widowisko");
        wynik.stream().limit(5).forEach(System.out::println);
        int iloscWynikow = wynik.size();
        assertEquals(wynik.size(), iloscWynikow);
        System.out.println("ilosc wynikow: " + wynik.size());
    }

    @Test
    public void tryWithEmptyResults() {
        Set<SingleSearchResult> wynik = BookSearch.bookSearchResultSet("asdasd");
        int emptyTreeSetSize = 0;
        assertEquals(wynik.size(), emptyTreeSetSize);
    }

    // SingleSearchResult{url='book.aspx?id=335096', desc='Car Maksymilian: Widowisko ludowe na Rusi (Gołąbek Józef)'}
    @Test
    public void scrapeFoundItem() {
        Book carMaksymilian = BookScraper.scrapedIt("book.aspx?id=335096");
        String tytul = "Car Maksymilian: Widowisko ludowe na Rusi";
        assertEquals(carMaksymilian.getOriginalTitle(), tytul);
    }
}

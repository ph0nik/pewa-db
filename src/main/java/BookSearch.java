import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalINIConfiguration;
import org.apache.commons.lang.math.NumberUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.jws.soap.SOAPBinding;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;


/**
 * Created by phonik on 2017-01-10.
 *
 * opcja wyszukiwania książek w bazie oraz wskazanie właściwego tytułu
 * zwracanie parametrów wybranej książki
 */
public class BookSearch {

    private BookSearch() {
    }

    public static Book bSearch(String userInput) {
        String scrapeUrl,
                url;
        int n = 0;
        Map<Integer, Element> foundBookTitle = new TreeMap();
        String newBook = userInput;
        Book output = new Book();

        try {
            url = ConfigReader.searchBook.concat((newBook.replaceAll(" ","+")));

            final Document searchResults = Jsoup.connect(url)
                                                .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36 OPR/42.0.2393.94")
                                                .timeout(5*1000)
                                                .get();
            Elements titleOrg = searchResults.getElementById(ConfigReader.titleOrgId)
                                             .getElementsByTag("li");
            Elements titlePL = searchResults.getElementById(ConfigReader.titlePlId)
                                            .getElementsByTag("li");

            for(Element x : titleOrg) {
                foundBookTitle.put(n, x);
                n++;
            }

            for(Element x : titlePL) {
                foundBookTitle.put(n, x);
                n++;
            }

            if(foundBookTitle.isEmpty()) {
                System.out.println("Nie znaleziono szukanego wyrażenia.");
            }
            else {
                for(Integer x : foundBookTitle.keySet()) {
                    System.out.println("[" + x + "] " + foundBookTitle.get(x).text());
                }

                int element = UserInterface.pickElement(foundBookTitle);

                System.out.println("Wybrana pozycja: "+foundBookTitle.get(element).text());
                scrapeUrl = foundBookTitle.get(element).getElementsByTag("a").attr("href");
                output = BookScraper.scrapedIt(scrapeUrl);

            }
//            System.out.println(output);


        } catch (IOException e) {
            System.out.println("Nie można nawiązać połączenia z: " + e);
        } finally {
            return output;
        }
    }
}

package com.pewa.book;

import com.pewa.config.ConfigReader;
import com.pewa.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;


/**
 * Created by phonik on 2017-01-10.
 * <p>
 * opcja wyszukiwania książek w bazie oraz wskazanie właściwego tytułu
 * zwracanie parametrów wybranej książki
 * </p>
 */
public class BookSearch {
    private Set<SingleSearchResult> searchResultSet = new TreeSet<>();
    private static final Logger log = LogManager.getLogger(BookSearch.class);

    public BookSearch() {
    }

    public Set<SingleSearchResult> bookSearchResultSet(String userInput) {
        String url = ConfigReader.searchBookAlt.concat(userInput.replaceAll(" ", "+"));
        try {
            final Document searchResults = Jsoup.connect(url)
                    .userAgent(ConfigReader.userAgent)
                    .timeout(5 * 1000)
                    .get();
            Elements titlePL = searchResults.getElementById(ConfigReader.titlePlId)
                    .getElementsByTag("li");

            for (Element x : titlePL) {
                SingleSearchResult singleSearchResult = new SingleSearchResult();
                String biblionetkaIdString = x.getElementsByTag("a")
                                        .attr("href");
                int biblionetkaId = Integer.parseInt(
                        biblionetkaIdString.substring(biblionetkaIdString.lastIndexOf("=") + 1)
                );
                singleSearchResult.setType(PewaType.BOOK);
                singleSearchResult.setIdInt(biblionetkaId);
                singleSearchResult.setDesc(x.text());
                searchResultSet.add(singleSearchResult);
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return searchResultSet;
    }
}

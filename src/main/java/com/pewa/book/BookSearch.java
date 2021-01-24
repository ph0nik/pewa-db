package com.pewa.book;

import com.pewa.PewaType;
import com.pewa.common.ExternalMediaResult;
import com.pewa.common.Results;

import com.pewa.config.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;


/**
 * Created by phonik on 2017-01-10.
 * <p>
 * opcja wyszukiwania książek w bazie oraz wskazanie właściwego tytułu
 * zwracanie parametrów wybranej książki
 * </p>
 */
@Component
public class BookSearch {
    private static final Logger log = LogManager.getLogger(BookSearch.class);

    public BookSearch() {
    }

    public Results bookSearchResultSet(String request, Results results) {
        Set<ExternalMediaResult> searchResultSet = new TreeSet<>();
        String url = ConfigFactory.get("search.bookSearchUrlAlt").concat(request.replaceAll(" ", "+"));
        try {
            final Document searchResults = Jsoup.connect(url)
                    .userAgent(ConfigFactory.get("search.userAgent"))
                    .timeout(5 * 1000)
                    .get();

            Elements titlePL = searchResults.getElementById(ConfigFactory.get("search.bookTitlePlId"))
                    .getElementsByTag("li");

            for (Element x : titlePL) {
                ExternalMediaResult singleSearchResult = new ExternalMediaResult();
                String author = x.select("a[href*=author]").stream().map(Element::text).collect(Collectors.joining(", "));

                String title = x.select("a[href*=book]").eq(0).text();
                String link = x.select("a[href*=book]").eq(0).attr("href");
                int biblionetkaId = Integer.parseInt(
                        link.substring(link.lastIndexOf("=") + 1)
                );
                singleSearchResult.setType(PewaType.BOOK);
                singleSearchResult.setIdInt(biblionetkaId);
                singleSearchResult.setTitle(title);
                singleSearchResult.setDescription(author);
                // zapytanie nie zwraca dat, tymczasowa data
                singleSearchResult.setDate(LocalDate.now());
                log.info(singleSearchResult);
                searchResultSet.add(singleSearchResult);
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        searchResultSet.forEach(results::setEncounters);
        return results;
    }
}

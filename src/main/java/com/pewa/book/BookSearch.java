package com.pewa.book;

import com.pewa.config.ConfigReader;
import com.pewa.*;
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
 */
public class BookSearch {

    private BookSearch() {
    }

    public static Set<SingleSearchResult> bookSearchResultSet(String userInput) {
        String url;
        Set<SingleSearchResult> searchResultSet = new TreeSet<>();
        url = ConfigReader.searchBookAlt.concat(userInput.replaceAll(" ", "+"));
        try {
            final Document searchResults = Jsoup.connect(url)
                    .userAgent(ConfigReader.userAgent)
                    .timeout(5 * 1000)
                    .get();
            Elements titlePL = searchResults.getElementById(ConfigReader.titlePlId)
                    .getElementsByTag("li");

            for (Element x : titlePL) {
                SingleSearchResult singleSearchResult = new SingleSearchResult();
                String biblionetkaId = x.getElementsByTag("a").attr("href");
                singleSearchResult.setUrl(biblionetkaId);
                singleSearchResult.setDesc(x.text());
                searchResultSet.add(singleSearchResult);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchResultSet;
    }
}

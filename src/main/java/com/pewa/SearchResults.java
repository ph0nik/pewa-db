package com.pewa;

import com.pewa.config.ConfigReader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

//TODO zamienić metody statyczne na metody tej klasy (?)
public class SearchResults {
    private Set<SingleSearchResult> searchResultSet;

    SearchResults() {
        searchResultSet = new TreeSet<>();
    }

    public Set<SingleSearchResult> getResults() {
        return searchResultSet;
    }

    public Set<SingleSearchResult> bookSearchResultSet(String userInput, Set<SingleSearchResult> searchResultSet) {
        //searchResultSet = new TreeSet<>();
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

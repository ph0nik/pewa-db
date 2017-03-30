package com.pewa.book;

import com.pewa.Form;
import com.pewa.Genre;
import com.pewa.MediaParse;
import com.pewa.Person;
import com.pewa.config.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sun.java2d.loops.GeneralRenderer;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static com.pewa.config.ConfigReader.*;

/**
 * Created by phonik on 2017-01-02.
 * test book id: 14853
 */
public class BookParser implements MediaParse<Book, Integer> {

    private static final Logger log = LogManager.getLogger(BookParser.class);

    public Book getItem(Integer id) {
        Book bookItem = new Book();
        try {
            String url = bookItemUrl.replaceAll("query", String.valueOf(id));
            final Document document = Jsoup.connect(url)
                    .timeout(5 * 1000)
                    .get();
            Elements basicInfo = document.getElementsByClass("taksiazka").get(0).children();
            String itemTitle = document.getElementsByClass("fn").text();
            Set<String> autor = extractStringList(basicInfo.select("li:contains(Autor)").text());
            for (String a : autor) {
                bookItem.setPeople(new Person(a, "", "author"));
            }
            String tytulOrg = extractString(basicInfo.select("li:contains(tuł oryg)").text());
            if (tytulOrg.isEmpty()) {
                bookItem.setOriginalTitle(itemTitle);
            } else {
                bookItem.setOriginalTitle(tytulOrg);
            }
            bookItem.setPolishTitle(itemTitle);
            String jezykOrg = extractString(basicInfo.select("li:contains(zyk oryg)").text());
            bookItem.setOriginalLanguage(jezykOrg);
            Set<String> tlumacz = extractStringList(basicInfo.select("li:contains(umacz:)").text());
            for (String t : tlumacz) {
                bookItem.setPeople(new Person(t, "", "translator"));
            }
            String kategoria = extractString(basicInfo.select("li:contains(ategoria:)").text());
            bookItem.setCategory(kategoria);
            Set<String> gatunek = extractStringList(basicInfo.select("li:contains(atunek:)").text());
            for (String g : gatunek) {
                bookItem.setGenre(new Genre(g));
            }
            Set<String> forma = extractStringList(basicInfo.select("li:contains(Forma:)").text());
            for (String f : forma) {
                bookItem.setForm(new Form(f));
            }
            int rokWydPierwsze = extractInt(basicInfo.select("li:contains(ego wydania:)").text());
            bookItem.setFirstPubDate(rokWydPierwsze);
            int rokWydPl = extractInt(basicInfo.select("li:contains(ego wydania pol)").text());
            bookItem.setPlPubDate(rokWydPl);
            //TODO odczyt kolejnego elementu po wskazanym (ul)
            /*Elements elem = basicInfo.select("ul.taksiazka");
            Iterator<Element> iter = elem.iterator();
            while(iter.hasNext()) {
                System.out.println(iter.next());
            }*/
            String alternVer = extractString(basicInfo.select("li:contains(Inne wersje książki) + li").text());
            bookItem.setAltVersion(alternVer);

            Elements rating = document.getElementsByClass("average");
            Double ocena = Double.parseDouble(rating.text().replace(",", "."));
            bookItem.setRating(ocena);
            int glosy = Integer.decode(document.getElementsByClass("votes").text());
            bookItem.setVotes(glosy);

            Elements other = document.getElementsByClass("taksiazka").select("li:contains(agi i dodatkow)");

            String info = Jsoup.parse(other.html().replaceAll("<br>", "br2n")).text().replaceAll("br2n", "<br>");
            bookItem.setAdditionalInfo(info);
            bookItem.setIdBiblion(id);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return bookItem;
    }

    private static String extractString(String el) {
        if (el.contains(":")) {
            int n = el.indexOf(":");
            return el.substring(n).replaceFirst(":", "").replaceFirst("\\s", "");
        } else return "";
    }

    private static Set<String> extractStringList(String el) {
        String[] out = new String[1];
        Set<String> output = new TreeSet<>();
        if (el.contains(":")) {
            int n = el.indexOf(":");
            if (el.contains(",")) {
                out = el.substring(n)
                        .replaceFirst(":", "")
                        .replaceFirst("\\s", "")
                        .trim()
                        .split(",");
            } else if (el.contains("/")) {
                out = el.substring(n)
                        .replaceFirst(":", "")
                        .replaceFirst("\\s", "")
                        .trim()
                        .split("/");
            } else if (el.contains("br2n")) {
                out = el.trim().split("br2n");
            } else {
                out[0] = el.substring(n)
                        .replaceFirst(":", "")
                        .replaceFirst("\\s", "")
                        .trim();
            }
            output = new TreeSet<>(Arrays.asList(out));
            output = output.stream()
                    .map(x -> x.trim())
                    .collect(Collectors.toSet());
        }
        return output;
    }

    private static int extractInt(String el) {
        String temp;
        if (el.contains(":")) {
            int n = el.indexOf(":");
            temp = el.substring(n)
                    .replaceFirst(":", "")
                    .replaceFirst("\\s", "");
            return Integer.decode(temp);
        } else return 0;
    }
}

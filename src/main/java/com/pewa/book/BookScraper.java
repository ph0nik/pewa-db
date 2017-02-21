package com.pewa.book;

import com.pewa.config.ConfigReader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by phonik on 2017-01-02.
 * test book id: 14853
 */
public class BookScraper {
    private String url;

    private BookScraper() {
    }

    public static Book scrapedIt(String id) {
        Book bookItem = new Book();
        try {
            String url = ConfigReader.bookItemUrl.concat(id);
            final Document document = Jsoup.connect(url).get();
            Elements basicInfo = document.getElementsByClass("taksiazka").get(0).children();
            String itemTitle = document.getElementsByClass("fn").text();
            String[] autor = extractStringList(basicInfo.select("li:contains(Autor)").text());
            bookItem.setAuthor(autor);
            String tytulOrg = extractString(basicInfo.select("li:contains(tuł oryg)").text());
            if (tytulOrg.isEmpty()) {
                bookItem.setOriginalTitle(itemTitle);
            } else {
                bookItem.setOriginalTitle(tytulOrg);
            }
            bookItem.setPolishTitle(itemTitle);
            String jezykOrg = extractString(basicInfo.select("li:contains(zyk oryg)").text());
            bookItem.setOriginalLanguage(jezykOrg);
            String[] tlumacz = extractStringList(basicInfo.select("li:contains(umacz:)").text());
            bookItem.setTranslator(tlumacz);
            String kategoria = extractString(basicInfo.select("li:contains(ategoria:)").text());
            bookItem.setCategory(kategoria);
            String[] gatunek = extractStringList(basicInfo.select("li:contains(atunek:)").text());
            bookItem.setGenre(gatunek);
            String[] forma = extractStringList(basicInfo.select("li:contains(Forma:)").text());
            bookItem.setForm(forma);
            int rokWydPierwsze = extractInt(basicInfo.select("li:contains(ego wydania:)").text());
            bookItem.setFirstPubDate(rokWydPierwsze);
            int rokWydPl = extractInt(basicInfo.select("li:contains(ego wydania pol)").text());
            bookItem.setPlPubDate(rokWydPl);
            String alternVer = extractString(basicInfo.select("li:contains(Inne wersje ksi)").text());
            bookItem.setAltVersion(alternVer);

            Elements rating = document.getElementsByClass("average");
            Double ocena = Double.parseDouble(rating.text().replace(",", "."));
            bookItem.setRating(ocena);
            int glosy = Integer.decode(document.getElementsByClass("votes").text());
            bookItem.setVotes(glosy);

            Elements other = document.getElementsByClass("taksiazka").select("li:contains(agi i dodatkow)");

            String[] info = extractStringList(Jsoup.parse(other.html().replaceAll("<br>", "br2n")).text());
            bookItem.setAdditionalInfo(info);
            bookItem.setId(id);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return bookItem;
        }
    }

    private static String extractString(String el) {
        if (el.contains(":")) {
            int n = el.indexOf(":");
            return el.substring(n).replaceFirst(":", "").replaceFirst("\\s", "");
        } else return "";
    }

    private static String[] extractStringList(String el) {
        String[] out = new String[1];
        if (el.contains(":")) {
            int n = el.indexOf(":");
            if (el.contains(",")) {
                return el.substring(n).replaceFirst(":", "").replaceFirst("\\s", "").split(",");
            }
            if (el.contains("/")) {
                return el.substring(n).replaceFirst(":", "").replaceFirst("\\s", "").split("/");
            }
            if (el.contains("br2n")) {
                out = el.split("br2n");
                return out;
            } else {
                out[0] = el.substring(n).replaceFirst(":", "").replaceFirst("\\s", "");
                return out;
            }
        } else {
            out[0] = "";
            return out;
        }
    }

    private static int extractInt(String el) {
        if (el.contains(":")) {
            int n = el.indexOf(":");
            return Integer.decode(el.substring(n).replaceFirst(":", "").replaceFirst("\\s", ""));
        } else return 0;
    }
}

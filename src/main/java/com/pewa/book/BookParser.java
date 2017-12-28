package com.pewa.book;

import com.pewa.PewaType;
import com.pewa.common.Form;
import com.pewa.common.Genre;
import com.pewa.MediaParse;
import com.pewa.common.Person;
import com.pewa.common.Request;
import com.pewa.config.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;


/**
 * Created by phonik on 2017-01-02.
 * test book id: 14853
 */
@Component
public class BookParser implements MediaParse<Book, Integer> {

    private static final Logger log = LogManager.getLogger(BookParser.class);

    public Book getItem(Integer id) {
        Book bookItem = new Book();
        try {
            String url = ConfigFactory.get("item.bookItemUrl").replaceAll("query", String.valueOf(id));
            final Document document = Jsoup.connect(url)
                    .timeout(5 * 1000)
                    .get();
            String bookInfo = ConfigFactory.get("biblionetka.bookInfo");
            Elements basicInfo = document.getElementsByClass(bookInfo).get(0).children();
            String tytul = ConfigFactory.get("biblionetka.tytul");
            String itemTitle = document.getElementsByClass(tytul).text();
            String autor = ConfigFactory.get("biblionetka.autor");
            Set<String> authorSet = extractStringList(basicInfo.select(autor).text());
            authorSet.forEach(a -> bookItem.setPeople(new Person(a, "", "author")));

            String tutulOryginalny = ConfigFactory.get("biblionetka.tutulOryginalny");
            String tytulOrg = extractString(basicInfo.select(tutulOryginalny).text());
            if (tytulOrg.isEmpty()) {
                bookItem.setOriginalTitle(itemTitle);
            } else {
                bookItem.setOriginalTitle(tytulOrg);
            }
            bookItem.setPolishTitle(itemTitle);
            String jezykOryginalny = ConfigFactory.get("biblionetka.jezykOryginalny");
            String jezykOrg = extractString(basicInfo.select(jezykOryginalny).text());
            bookItem.setOriginalLanguage(jezykOrg);
            String tlumacz = ConfigFactory.get("biblionetka.tlumacz");
            Set<String> tlumaczSet = extractStringList(basicInfo.select(tlumacz).text());
            tlumaczSet.forEach(t -> bookItem.setPeople(new Person(t, "", "translator")));

            String kategoria = ConfigFactory.get("biblionetka.kategoria");
            String kat = extractString(basicInfo.select(kategoria).text());
            bookItem.setCategory(kat);
            String gatunek = ConfigFactory.get("biblionetka.gatunek");
            Set<String> gatunekSet = extractStringList(basicInfo.select(gatunek).text());
            gatunekSet.forEach(g -> bookItem.setGenre(new Genre(g)));

            String forma = ConfigFactory.get("biblionetka.forma");
            Set<String> formaSet = extractStringList(basicInfo.select(forma).text());
            formaSet.forEach(f -> bookItem.setForm(new Form(f)));

            String rokPierwszegoWydania = ConfigFactory.get("biblionetka.rokPierwszegoWydania");
            Integer rokWydPierwsze = extractInt(basicInfo.select(rokPierwszegoWydania).text());
            bookItem.setFirstPubDate(rokWydPierwsze);

            String rokPolskiegoWydania = ConfigFactory.get("biblionetka.rokPolskiegoWydania");
            Integer rokWydPl = extractInt(basicInfo.select(rokPolskiegoWydania).text());
                if (rokWydPl.equals(0)) bookItem.setPlPubDate(rokWydPierwsze);
                else bookItem.setPlPubDate(rokWydPl);
            //TODO odczyt kolejnego elementu po wskazanym (ul)
   /*         String alternatywne = ConfigFactory.get("biblionetka.alternatywne");
            String alternVer = extractString(basicInfo.select(alternatywne).text());
            System.out.println(alternVer);
            bookItem.setAltVersion(alternVer);*/

            String ratingAvg = ConfigFactory.get("biblionetka.ratingAverage");
            String ratingVot = ConfigFactory.get("biblionetka.ratingVotes");
            Elements rating = document.getElementsByClass(ratingAvg);
            if (!rating.isEmpty()) {
                Double ocena = Double.parseDouble(rating.text().replace(",", "."));
                bookItem.setRating(ocena);
                Integer glosy = Integer.decode(document.getElementsByClass(ratingVot).text());
                bookItem.setVotes(glosy);
            }

            String dodatkowe = ConfigFactory.get("biblionetka.dodatkowe");
            Elements other = document.getElementsByClass(bookInfo).select(dodatkowe);
            String info = Jsoup.parse(other.html().replaceAll("<br>", "br2n")).text().replaceAll("br2n", "<br>");
            bookItem.setAdditionalInfo(info);
            bookItem.setExternalBookId(id);
            bookItem.setType(PewaType.BOOK);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return bookItem;
    }

    private String extractString(String el) {
        if (el.contains(":")) {
            Integer n = el.indexOf(":");
            return el.substring(n).replaceFirst(":", "").replaceFirst("\\s", "");
        } else return "";
    }

    private Set<String> extractStringList(String el) {
        String[] out = new String[1];
        Integer n;
        Set<String> output = new TreeSet<>();
        if (el.contains("(pseud")) {
            n = el.indexOf("(");
            el = el.substring(0, n).trim();
        }
        if (el.contains(":")) {
            n = el.indexOf(":");
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
            } /*else if (el.contains("(pseud")) {
                n = el.indexOf("(")
                        out = el.substring(0,n).trim();
                el.chars().
                for (char c : el.toCharArray()) {
                    out.
                }
                
            }*/
            else {
                out[0] = el.substring(n)
                        .replaceFirst(":", "")
                        .replaceFirst("\\s", "")
                        .trim();
            }
            output = new TreeSet<>(Arrays.asList(out));
            output = output.stream()
                    .map(x -> x.trim())
                    .collect(Collectors.toSet());
        } else {
            output.add(el);
        }
        return output;
    }

    private Integer extractInt(String el) {
        String temp;
        if (el.contains(":")) {
            Integer n = el.indexOf(":");
            temp = el.substring(n)
                    .replaceFirst(":", "")
                    .replaceFirst("\\s", "");
            return Integer.decode(temp);
        } else return 0;
    }
}

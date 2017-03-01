package com.pewa.music;

import com.pewa.SingleSearchResult;
import com.pewa.config.ConfigReader;
import org.jdom2.JDOMException;
import org.xml.sax.InputSource;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.jsoup.Jsoup;


import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


public class AlbumSearch {
//    String url = "";


    public static Set<SingleSearchResult> searchMusicAlbum(String query) {
        StringBuilder url = new StringBuilder(ConfigReader.musicSearch).append(query.replaceAll(" ", "+"));
        Set<SingleSearchResult> searchResultSet = new TreeSet<>();

        SAXBuilder builder = new SAXBuilder();
        try {
            String musicBrainzDocument = Jsoup.connect(url.toString())
                    .userAgent(ConfigReader.userAgentMusicBrainz)
                    .timeout(5 * 1000)
                    .ignoreContentType(true)
                    .get()
                    .outputSettings(new org.jsoup.nodes.Document.OutputSettings().prettyPrint(false))
                    .toString();

            InputSource musicBrainzXml = new InputSource(new StringReader(musicBrainzDocument));
            Document musicBrainzJdom = builder.build(musicBrainzXml);
            Element rootNode = musicBrainzJdom.getRootElement();
            Namespace namespace = rootNode.getNamespace();
            List<Element> releases = rootNode.getChildren().get(0).getChildren();

            for (Element i : releases) {
                SingleSearchResult singleSearchResult = new SingleSearchResult();
                Element node = i;
                String albumId = node.getAttributeValue("id");
                String albumTitle = node.getChildText("title", namespace);
                String albumDate = node.getChildText("date", namespace);
                String albumArtist = node.getChild("artist-credit", namespace)
                        .getChild("name-credit", namespace)
                        .getChild("artist", namespace)
                        .getChildText("sort-name", namespace);
                String desc = new StringBuilder(albumArtist)
                        .append(" \"")
                        .append(albumTitle)
                        .append("\" (")
                        .append(albumDate)
                        .append(")")
                        .toString();
                singleSearchResult.setDesc(desc);
                singleSearchResult.setUrl(albumId);
                searchResultSet.add(singleSearchResult);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JDOMException e) {
            e.printStackTrace();
        }
        searchResultSet.forEach(System.out::println);
        return searchResultSet;
    }

}

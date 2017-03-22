package com.pewa.music;

import com.pewa.config.ConfigReader;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.jsoup.Jsoup;
import org.xml.sax.InputSource;

import java.io.IOException;
import java.io.StringReader;

public class AlbumParseToObject {
    private AlbumParseToObject() {

    }

    public static Album parseAlbum(String musicBrainzId) {
        Album album = new Album();
        String url = ConfigReader.musicBrainzItem.replaceAll("<query>", musicBrainzId);
        System.out.println(url);
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
            org.jdom2.Document musicBrainzJdom = builder.build(musicBrainzXml);
            System.out.println(musicBrainzJdom.getContent());
            Element rootNode = musicBrainzJdom.getRootElement();
            Namespace namespace = rootNode.getNamespace();
            String albumTitle = rootNode.getChild("release", namespace).getChildText("title", namespace);
            String albumDate = rootNode.getChild("release", namespace).getChildText("date", namespace);
            String albumArtist = rootNode.getChild("release", namespace)
                    .getChild("artist-credit", namespace)
                    .getChild("name-credit", namespace)
                    .getChild("artist", namespace)
                    .getChildText("sort-name", namespace);
            album.setAlbumArtist(albumArtist);
            album.setAlbumDate(albumDate);
            album.setAlbumTitle(albumTitle);
            return album;


        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return album;
    }
}


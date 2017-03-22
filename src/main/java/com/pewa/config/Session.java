package com.pewa.config;

import com.pewa.anime.AnimeAccessToken;
import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static com.pewa.anime.AnimeMangaSearch.aniListAuth;

public class Session {
    private static String accessToken;
    private static String tokenType;
    private static Long expireTime;

    public static AnimeAccessToken updateSession() {
        AnimeAccessToken at = new AnimeAccessToken();
        XStream xstream = new XStream();
        File sessionXml = new File("src/main/resources/pewa-session.tmp");
        try {
            if (sessionXml.exists()) {
                long localTimestamp = (System.currentTimeMillis() / 1000L);
                at = (AnimeAccessToken) xstream.fromXML(sessionXml);
                expireTime = at.getExpireTime();
                if ((expireTime - localTimestamp) <= 0) {
                    at = aniListAuth();
                    FileWriter xmlOut = new FileWriter(sessionXml);
                    xstream.toXML(at, xmlOut);
                    xmlOut.close();
                }
            } else {
                at = aniListAuth();
                FileWriter xmlOut = new FileWriter(sessionXml);
                xstream.toXML(at, xmlOut);
                xmlOut.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return at;
    }
/*    public static AnimeAccessToken updateSession() {
        AnimeAccessToken at = new AnimeAccessToken();
        File session = new File("src/main/resources/pewa-session.ini");
        File sessionXml = new File("src/main/resources/pewa-session.tmp");
        try {
            session.createNewFile();
            HierarchicalINIConfiguration readSession = new HierarchicalINIConfiguration(session);
            if (readSession.isEmpty()) {
                at = aniListAuth();
                readSession.setProperty("aniList.token", at.getAccessToken());
                readSession.setProperty("aniList.type", at.getTokenType());
                readSession.setProperty("aniList.expires", at.getExpireTime());
                readSession.save();
            } else {
                expireTime = readSession.getLong("aniList.expires");
                long localTimestamp = (System.currentTimeMillis() / 1000L);
                if ((expireTime - localTimestamp - 10) <= 0) {
                    at = aniListAuth();
                    readSession.setProperty("aniList.token", at.getAccessToken());
                    readSession.setProperty("aniList.type", at.getTokenType());
                    readSession.setProperty("aniList.expires", at.getExpireTime());
                    readSession.save();
                } else {
                    at = new AnimeAccessToken();
                    at.setAccessToken(readSession.getString("aniList.token"));
                    at.setTokenType(readSession.getString("aniList.type"));
                    at.setExpireTime(readSession.getLong("aniList.expires"));
                    XStream save = new XStream();
                    FileWriter xmlOut = new FileWriter(sessionXml);
                    save.toXML(at, xmlOut);

                    System.out.println(save.fromXML(sessionXml));


                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        return at;
    }*/


}

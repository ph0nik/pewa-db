package com.pewa.config;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.pewa.anime.AnimeAccessToken;
import com.thoughtworks.xstream.XStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


import static com.pewa.config.ConfigReader.*;
import static com.pewa.config.ConfigReader.userAgent;

public class Session {
    private static String accessToken;
    private static String tokenType;
    private static final String SESSION = "src/main/resources/pewa-session.tmp";
    private static final Logger log = LogManager.getLogger(Session.class);


    public static AnimeAccessToken updateSession() {
        AnimeAccessToken at = new AnimeAccessToken();
        StringBuilder logmess;
        XStream xstream = new XStream();
        File sessionXml = new File(SESSION);
        try {
            if (sessionXml.exists()) {
                logmess = new StringBuilder("Session file found: [").append(SESSION).append("]");
                log.info(logmess);
                long localTimestamp = (System.currentTimeMillis() / 1000L);
                at = (AnimeAccessToken) xstream.fromXML(sessionXml);
                Long expireTime = at.getExpireTime();
                if ((expireTime - localTimestamp) <= 0) {
                    at = aniListAuth();
                    FileWriter xmlOut = new FileWriter(sessionXml);
                    xstream.toXML(at, xmlOut);
                    xmlOut.close();
                    logmess = new StringBuilder("Session file updated.");
                    log.info(logmess);
                }
            } else {
                logmess = new StringBuilder("Session file not found: [").append(SESSION).append("]");
                log.info(logmess);
                at = aniListAuth();
                FileWriter xmlOut = new FileWriter(sessionXml);
                xstream.toXML(at, xmlOut);
                xmlOut.close();
                logmess = new StringBuilder("Session file created.");
                log.info(logmess);
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return at;
    }

    private static AnimeAccessToken aniListAuth() throws IOException {
        AnimeAccessToken animeAccessToken = new AnimeAccessToken();
        String clientId = aniListClientId;
        String clientSecret = aniListClientSecret;
        String url = aniListApiEndpoint + aniListPostTokenReq;
        final String getToken = Jsoup.connect(url)
                .data("grant_type", "client_credentials", "client_id", clientId, "client_secret", clientSecret)
                .userAgent(userAgent)
                .timeout(5 * 1000)
                .ignoreContentType(true)
                .post()
                .text();
        JsonObject auth = Json.parse(getToken).asObject();
        animeAccessToken.setAccessToken(auth.get("access_token").asString());
        animeAccessToken.setTokenType(auth.get("token_type").asString());
        animeAccessToken.setExpireTime(auth.get("expires").asLong());

        return animeAccessToken;
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

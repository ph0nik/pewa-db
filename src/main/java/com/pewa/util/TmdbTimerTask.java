package com.pewa.util;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.pewa.config.ConfigFactory;
import com.thoughtworks.xstream.XStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.TimerTask;

/**
 * Created by phonik on 2017-10-19.
 */
public class TmdbTimerTask extends TimerTask {

    private static final String SESSION = ConfigFactory.get("themoviedb.sessionConfig");
    private static final Logger log = LogManager.getLogger(TmdbTimerTask.class);

    @Override
    public void run() {
        try {
            String url = new StringBuilder(ConfigFactory.get("themoviedb.url"))
                    .append(ConfigFactory.get("themoviedb.config"))
                    .toString()
                    .replaceAll("<api-key>", ConfigFactory.get("themoviedb.apiKey"));

            String tmdbConfig = Jsoup.connect(url)
                    .userAgent(ConfigFactory.get("search.userAgentMB"))
                    .timeout(5 * 1000)
                    .ignoreContentType(true)
                    .get()
                    .text();
            TmdbApi tmdbConfigObject = parseJsonToObject(tmdbConfig);
            manageConfigStatusFile(tmdbConfigObject);
            System.out.println(tmdbConfigObject);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    public TmdbApi getConfiguration() {
        TmdbApi tmdbApi = new TmdbApi();
        XStream xstream = new XStream();
        File sessionXml = new File(SESSION);
        tmdbApi = (TmdbApi) xstream.fromXML(sessionXml);
        return tmdbApi;
    }

    private TmdbApi parseJsonToObject(String tmdbConfig) {
        JsonObject jsonConfig = Json.parse(tmdbConfig).asObject().get("images").asObject();
        TmdbApi tmdbConf = new TmdbApi();
        tmdbConf.setConfigDate(LocalDateTime.now());
        tmdbConf.setBaseUrl(jsonConfig.getString("base_url",""));
        tmdbConf.setSecureBaseUrl(jsonConfig.getString("secure_base_url",""));
        JsonArray sizesArray = jsonConfig.get("poster_sizes").asArray();
        sizesArray.values().forEach(x -> tmdbConf.setPosterSizes(x.asString()));
        return tmdbConf;
    }

    private void manageConfigStatusFile(TmdbApi tmdbConfig) {
        StringBuilder logmess;
        XStream xstream = new XStream();
        File sessionXml = new File(SESSION);
        try {
            FileWriter xmlOut = new FileWriter(sessionXml);
            xstream.toXML(tmdbConfig, xmlOut);
            xmlOut.close();
            logmess = new StringBuilder("Session file updated. [")
                    .append(SESSION)
                    .append("]");
            log.info(logmess);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}

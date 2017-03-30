package com.pewa.tv;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import com.pewa.Genre;
import com.pewa.MediaParse;
import com.pewa.config.ConfigReader;
import com.pewa.util.SaveImage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.pewa.config.ConfigReader.*;

public class TvShowParser implements MediaParse<TvShowSummary, String> {

    private static final Logger log = LogManager.getLogger(TvShowParser.class);

    public TvShowSummary getItem(String imdbUrl) {
        String urlSummary;
        StringBuilder urlEpisodeList = new StringBuilder();
        TvShowSummary tvShowSummaryItem = new TvShowSummary();

        try {
            urlSummary = tvMaze.concat(tvMazeByImdbId).concat(imdbUrl);
            String getSummaryByImdbId = Jsoup.connect(urlSummary)
                    .userAgent(userAgent)
                    .timeout(5 * 1000)
                    .ignoreContentType(true)
                    .get()
                    .text();
            JsonValue tvmazeSum = Json.parse(getSummaryByImdbId);
            tvShowSummaryItem.setTvMazeId(tvmazeSum.asObject().getInt("id", 0));
            tvShowSummaryItem.setTvMazeUrl(tvmazeSum.asObject().getString("url", ""));
            tvShowSummaryItem.setTitle(tvmazeSum.asObject().getString("name", ""));
            tvShowSummaryItem.setType(tvmazeSum.asObject().getString("type", ""));
            tvShowSummaryItem.setLanguage(tvmazeSum.asObject().getString("language", ""));
            JsonArray jsonGenresArray = tvmazeSum.asObject().get("genres").asArray();
            for (JsonValue item : jsonGenresArray) {
                tvShowSummaryItem.setGenres(new Genre(item.asString()));
            }
            tvShowSummaryItem.setStatus(tvmazeSum.asObject().getString("status", ""));
            tvShowSummaryItem.setRuntime(tvmazeSum.asObject().getInt("runtime", 0));

            String firstAiredTemp = tvmazeSum.asObject().getString("premiered", "0000-01-01");
            LocalDate firstAired = LocalDate.parse(firstAiredTemp, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            tvShowSummaryItem.setPremiered(firstAired);

            if (tvmazeSum.asObject().get("rating").asObject().get("average").isNull()) {
                tvShowSummaryItem.setRatingAvg(0.0);
            } else {
                tvShowSummaryItem.setRatingAvg(tvmazeSum.asObject().get("rating").asObject().getDouble("average", 0.0));
            }

            if (tvmazeSum.asObject().get("network").isNull()) {
                tvShowSummaryItem.setNetwork(tvmazeSum.asObject().get("webChannel").asObject().getString("name", ""));
                tvShowSummaryItem.setCountry(tvmazeSum.asObject().get("webChannel").asObject().get("country").asObject().getString("name", ""));
            } else {
                tvShowSummaryItem.setNetwork(tvmazeSum.asObject().get("network").asObject().getString("name", ""));
                tvShowSummaryItem.setCountry(tvmazeSum.asObject().get("network").asObject().get("country").asObject().getString("name", ""));
            }

            JsonObject tvmazeExternals = tvmazeSum.asObject().get("externals").asObject();
            tvShowSummaryItem.setImdbLink(tvmazeExternals.getString("imdb", ""));
            if (tvmazeExternals.get("tvrage").isNull()) {
                tvShowSummaryItem.setTvrageLink(0);
            } else {
                tvShowSummaryItem.setTvrageLink(tvmazeExternals.getInt("tvrage", 0));
            }
            if (tvmazeExternals.get("thetvdb").isNull()) {
                tvShowSummaryItem.setThetvdbLink(0);
            } else {
                tvShowSummaryItem.setThetvdbLink(tvmazeExternals.getInt("thetvdb", 0));
            }
            JsonObject tvmazePosters = tvmazeSum.asObject().get("image").asObject();
            tvShowSummaryItem.setPosterMed(tvmazePosters.getString("medium", ""));
            tvShowSummaryItem.setIntPosterMed(SaveImage.getImageMed(tvShowSummaryItem));
            tvShowSummaryItem.setPosterOrg(tvmazePosters.getString("original", ""));
            tvShowSummaryItem.setIntPosterOrg(SaveImage.getImageOrg(tvShowSummaryItem));
            tvShowSummaryItem.setSummary(tvmazeSum.asObject().getString("summary", ""));

            urlEpisodeList.append(tvMaze)
                    .append(tvMazeSummary)
                    .append(tvShowSummaryItem.getTvMazeId())
                    .append(tvMazeEpisodeList);
            String getEpisodes = Jsoup.connect(urlEpisodeList.toString())
                    .userAgent(userAgent)
                    .timeout(5 * 1000)
                    .ignoreContentType(true)
                    .get()
                    .text();
            JsonArray tvmazeEpisodes = Json.parse(getEpisodes).asArray();
            for (JsonValue ep : tvmazeEpisodes) {
                int tvMazeId = ep.asObject().getInt("id", 0);
                String tvMazeUrl = ep.asObject().getString("url", "");
                String epTitle = ep.asObject().getString("name", "");
                int season = ep.asObject().getInt("season", 0);
                int episode = ep.asObject().getInt("number", 0);
                firstAiredTemp = ep.asObject().getString("airdate", "0000-01-01");
                firstAired = LocalDate.parse(firstAiredTemp, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                String summary = ep.asObject().getString("summary", "");
                TvShowEpisode tvShowEpisode = new TvShowEpisode();
                tvShowEpisode.setEpTitle(epTitle);
                tvShowEpisode.setFirstAired(firstAired);
                tvShowEpisode.setSeason(season);
                tvShowEpisode.setEpisode(episode);
                tvShowEpisode.setSummary(summary);
                tvShowEpisode.setTvMazeId(tvMazeId);
                tvShowEpisode.setTvMazeUrl(tvMazeUrl);
                tvShowSummaryItem.setEpisodes(tvShowEpisode);
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return tvShowSummaryItem;
    }

}
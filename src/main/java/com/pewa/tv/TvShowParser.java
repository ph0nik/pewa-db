package com.pewa.tv;

import com.eclipsesource.json.*;
import com.pewa.common.Genre;
import com.pewa.MediaParse;
import com.pewa.common.Person;
import com.pewa.config.ConfigFactory;
import com.pewa.util.SaveImage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class TvShowParser implements MediaParse<TvShowSummary, Integer> {
    private String firstAiredTemp;
    private LocalDate firstAired;
    private TvShowSummary tvShowSummaryItem = new TvShowSummary();

    private static final Logger log = LogManager.getLogger(TvShowParser.class);

    public TvShowSummary getItem(Integer tvmazeId) {
        try {
            String urlSummary = ConfigFactory.get("item.tvMazeSummary").replaceAll("<tvmaze_id>", tvmazeId.toString());
            log.info(urlSummary);
            final Connection.Response getSummary = Jsoup.connect(urlSummary)
                    .userAgent(ConfigFactory.get("search.userAgent"))
                    .timeout(5 * 1000)
                    .ignoreContentType(true)
                    .execute();
            if (getSummary.statusCode() != 200) {
                StringBuilder errorMsg = new StringBuilder("Status code: ")
                        .append(getSummary.statusCode())
                        .append(", Status msg: ")
                        .append(getSummary.statusMessage());
                log.error(errorMsg);
            } else {
                parseSummary(getSummary);
                parseEpisodes(getSummary);
                parseStaff(getSummary);
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
        }
        return tvShowSummaryItem;
    }

    private void parseSummary(Connection.Response cr) throws IOException, ParseException {
        JsonValue tvmazeSum = Json.parse(cr.parse().text());
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

        firstAiredTemp = tvmazeSum.asObject().getString("premiered", "0000-01-01");
        firstAired = LocalDate.parse(firstAiredTemp, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
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
    }

    private void parseEpisodes(Connection.Response cr) throws IOException, ParseException {
        JsonArray tvmazeEpisodes = Json.parse(cr.parse().text())
                                                        .asObject()
                                                        .get("_embedded")
                                                        .asObject()
                                                        .get("episodes")
                                                        .asArray();
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
    }

    private void parseStaff(Connection.Response cr) throws IOException, ParseException {
        JsonArray tvmazeStaff = Json.parse(cr.parse().text())
                .asObject()
                .get("_embedded")
                .asObject()
                .get("cast")
                .asArray();
        for (JsonValue person : tvmazeStaff) {
            String name = person.asObject().get("person").asObject().getString("name","");
            String job = "actor";
            tvShowSummaryItem.setStaff(new Person(name,"",job));
        }
        tvmazeStaff = Json.parse(cr.parse().text())
                .asObject()
                .get("_embedded")
                .asObject()
                .get("crew")
                .asArray();
        for (JsonValue person : tvmazeStaff) {
            String job = person.asObject().getString("type","");
            String name = person.asObject().get("person").asObject().getString("name","");
            tvShowSummaryItem.setStaff(new Person(name,"",job));
        }
    }

}
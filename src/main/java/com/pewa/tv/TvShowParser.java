package com.pewa.tv;

import com.eclipsesource.json.*;
import com.google.gson.Gson;
import com.pewa.PewaType;
import com.pewa.common.Country;
import com.pewa.common.Genre;
import com.pewa.MediaParse;
import com.pewa.common.Language;
import com.pewa.common.Person;
import com.pewa.config.ConfigFactory;
import com.pewa.tv.tvmaze.TvMaze;
import com.pewa.util.SaveImage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Component
public class TvShowParser implements MediaParse<TvShowSummary, Integer> {
    private TvShowSummary tvShowSummaryItem;

    private static final Logger log = LogManager.getLogger(TvShowParser.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

    public TvShowSummary getItem(Integer tvmazeId) {
        tvShowSummaryItem = new TvShowSummary();
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
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
        }
        return tvShowSummaryItem;
    }
    private TvShowParser parseSummary(Connection.Response cr) throws IOException, ParseException {
        Gson gson = new Gson();
        String connectionResponseString = cr.parse().text();
        TvMaze tvMaze = gson.fromJson(connectionResponseString, TvMaze.class);
        tvShowSummaryItem.setTitle(tvMaze.getName());
        tvShowSummaryItem.setPremiered(LocalDate.parse(tvMaze.getPremiered(), formatter));
        tvShowSummaryItem.setLanguage(new Language(tvMaze.getLanguage()));
        String country = "none";
        String network = "none";
        if (tvMaze.getNetwork() != null) {
            if (tvMaze.getNetwork().getCountry() != null) country = tvMaze.getNetwork().getCountry().getName();
            if (tvMaze.getNetwork().getName() != null) network = tvMaze.getNetwork().getName();
        }
        else if (tvMaze.getWebChannel() != null) {
            if (tvMaze.getWebChannel().getCountry() != null) country = tvMaze.getWebChannel().getCountry().getName();
            if (tvMaze.getWebChannel().getName() != null) network = tvMaze.getWebChannel().getName();
        }
        tvShowSummaryItem.setCountry(new Country(country));
        tvShowSummaryItem.setNetwork(network);
        tvMaze.getEmbedded().getCrew().forEach(cm -> {
            String job = cm.getType().toLowerCase();
            if (job.equals("executive producer") || job.equals("creator")) {
                tvShowSummaryItem.setStaff(new Person(cm.getPerson().getName(), "", job));
            }
        });
        tvMaze.getEmbedded().getCast().forEach(ca -> {
            String actor = "actor";
            tvShowSummaryItem.setStaff(new Person(ca.getPerson().getName(), "", actor));
        });
        tvMaze.getGenres().forEach(g -> {
            tvShowSummaryItem.setGenres(new Genre(g));
        });
        tvShowSummaryItem.setTvMazeUrl(tvMaze.getUrl());
        tvShowSummaryItem.setTvMazeId(tvMaze.getId());
        tvShowSummaryItem.setImdbLink(tvMaze.getExternals().getImdb());
        tvShowSummaryItem.setThetvdbLink(tvMaze.getExternals().getThetvdb());
        tvShowSummaryItem.setTvrageLink(tvMaze.getExternals().getTvrage());

        tvShowSummaryItem.setPosterOrg(tvMaze.getImage().getOriginal());
        tvShowSummaryItem.setPosterMed(tvMaze.getImage().getMedium());
        tvShowSummaryItem.setIntPosterMed(SaveImage.getImageMed(tvShowSummaryItem));
        tvShowSummaryItem.setIntPosterOrg(SaveImage.getImageOrg(tvShowSummaryItem));

        tvShowSummaryItem.setRatingAvg(tvMaze.getRating().getAverage());
        tvShowSummaryItem.setRuntime(tvMaze.getRuntime());
        tvShowSummaryItem.setSummary(tvMaze.getSummary());
        tvShowSummaryItem.setStatus(tvMaze.getStatus());
        tvShowSummaryItem.setShowType(tvMaze.getType());
        tvShowSummaryItem.setType(PewaType.TVSERIES);

        tvMaze.getEmbedded().getEpisodes().forEach(e -> {
            TvShowEpisode episode = new TvShowEpisode();
            episode.setTvMazeId(e.getId());
            episode.setSeason(e.getSeason());
            episode.setEpisode(e.getNumber());
            episode.setSummary(e.getSummary());
            episode.setEpTitle(e.getName());
            episode.setTvMazeUrl(e.getUrl());
            episode.setFirstAired(LocalDate.parse(e.getAirdate(), formatter));
            tvShowSummaryItem.setEpisodes(episode);
        });

        return this;
    }

}
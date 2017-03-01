package com.pewa.tv;

import java.time.LocalDate;
import java.util.*;

/**
 * Created by phonik on 2017-01-12.
 */
public class TvShowSummary {
    private int tvMazeId;
    private String tvMazeUrl;
    private String title;
    private String type;
    private String language;
    private Set<String> genres;
    private String status;
    private int runtime;
    private LocalDate premiered;
    private Double ratingAvg;
    private String network;
    private String country;
    private String imdbLink;
    private int thetvdbLink;
    private int tvrageLink;
    private String posterMed;
    private String posterOrg;
    private String summary;
    private List<TvShowEpisode> episodes;


    TvShowSummary() {
        this.episodes = new ArrayList();
    }

    public List<TvShowEpisode> getEpisodes() {
        return episodes;
    }

    void setEpisodes(TvShowEpisode episodes) {
        this.episodes.add(episodes);
    }

    int getTvMazeId() {
        return tvMazeId;
    }

    void setTvMazeId(int tvMazeId) {
        this.tvMazeId = tvMazeId;
    }

    String getTvMazeUrl() {
        return tvMazeUrl;
    }

    void setTvMazeUrl(String tvMazeUrl) {
        this.tvMazeUrl = tvMazeUrl;
    }

    public String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    void setType(String type) {
        this.type = type;
    }

    public String getLanguage() {
        return language;
    }

    void setLanguage(String language) {
        this.language = language;
    }

    public Set getGenres() {
        return genres;
    }

    void setGenres(Set<String> genres) {
        this.genres = genres;
    }

    public String getStatus() {
        return status;
    }

    void setStatus(String status) {
        this.status = status;
    }

    public int getRuntime() {
        return runtime;
    }

    void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public LocalDate getPremiered() {
        return premiered;
    }

    void setPremiered(LocalDate premiered) {
        this.premiered = premiered;
    }

    public Double getRatingAvg() {
        return ratingAvg;
    }

    void setRatingAvg(Double ratingAvg) {
        this.ratingAvg = ratingAvg;
    }

    public String getNetwork() {
        return network;
    }

    void setNetwork(String network) {
        this.network = network;
    }

    public String getCountry() {
        return country;
    }

    void setCountry(String country) {
        this.country = country;
    }

    public String getImdbLink() {
        return imdbLink;
    }

    void setImdbLink(String imdbLink) {
        this.imdbLink = imdbLink;
    }

    public int getThetvdbLink() {
        return thetvdbLink;
    }

    void setThetvdbLink(int thetvdbLink) {
        this.thetvdbLink = thetvdbLink;
    }

    public int getTvrageLink() {
        return tvrageLink;
    }

    void setTvrageLink(int tvrageLink) {
        this.tvrageLink = tvrageLink;
    }

    public String getPosterMed() {
        return posterMed;
    }

    void setPosterMed(String posterMed) {
        this.posterMed = posterMed;
    }

    public String getPosterOrg() {
        return posterOrg;
    }

    void setPosterOrg(String posterOrg) {
        this.posterOrg = posterOrg;
    }

    public String getSummary() {
        return summary;
    }

    void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "com.pewa.tv.TvShowSummary{" +
                "tvMazeId=" + tvMazeId +
                ", tvMazeUrl='" + tvMazeUrl + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", language='" + language + '\'' +
                ", genres=" + genres.toString() +
                ", status='" + status + '\'' +
                ", runtime='" + runtime + '\'' +
                ", premiered='" + premiered + '\'' +
                ", ratingAvg=" + ratingAvg +
                ", network='" + network + '\'' +
                ", country=" + country + '\'' +
                ", imdbLink='" + imdbLink + '\'' +
                ", thetvdbLink='" + thetvdbLink + '\'' +
                ", tvrageLink='" + tvrageLink + '\'' +
                ", posterMed='" + posterMed + '\'' +
                ", posterOrg='" + posterOrg + '\'' +
                ", summary='" + summary + '\'' +
                "episodes=" + episodes +
                '}';
    }

}

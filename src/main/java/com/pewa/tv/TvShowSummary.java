package com.pewa.tv;

import com.pewa.common.Genre;
import com.pewa.common.Person;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class TvShowSummary implements Serializable {

    private String tvMazeUrl;
    private String title;
    private String type;
    private String language;
    private String status;
    private String network;
    private String country;
    private String imdbLink;
    private String posterMed;
    private String posterOrg;
    private String intPosterMed;
    private String intPosterOrg;
    private String summary;
    private Integer id;
    private Integer tvMazeId;
    private Integer runtime;
    private Integer thetvdbLink;
    private Integer tvrageLink;
    private Set<Genre> genres;
    private LocalDate premiered;
    private Double ratingAvg;
    private List<TvShowEpisode> episodes;
    private Set<Person> staff;
    private LocalDateTime dbDatetime;

    static final long serialVersionUID = 1L;

    TvShowSummary() {
        this.episodes = new ArrayList<>();
        this.genres = new TreeSet<>();
        this.staff = new TreeSet<>();
    }

    public LocalDateTime getDbDatetime() {
        return dbDatetime;
    }

    public void setDbDatetime(LocalDateTime dbDatetime) {
        this.dbDatetime = dbDatetime;
    }

    public Set<Person> getStaff() {
        return staff;
    }

    public void setStaff(Set<Person> staff) {
        this.staff = staff;
    }

    public void setStaff(Person person) {
        this.staff.add(person);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIntPosterMed() {
        return intPosterMed;
    }

    public void setIntPosterMed(String intPosterMed) {
        this.intPosterMed = intPosterMed;
    }

    public String getIntPosterOrg() {
        return intPosterOrg;
    }

    public void setIntPosterOrg(String intPosterOrg) {
        this.intPosterOrg = intPosterOrg;
    }

    public List<TvShowEpisode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(TvShowEpisode episodes) {
        this.episodes.add(episodes);
    }

    public void setEpisodes(List<TvShowEpisode> episodes) {
        this.episodes = episodes;
    }

    public Integer getTvMazeId() {
        return tvMazeId;
    }

    public void setTvMazeId(Integer tvMazeId) {
        this.tvMazeId = tvMazeId;
    }

    public String getTvMazeUrl() {
        return tvMazeUrl;
    }

    public void setTvMazeUrl(String tvMazeUrl) {
        this.tvMazeUrl = tvMazeUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public void setGenres(Genre genre) {
        this.genres.add(genre);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public LocalDate getPremiered() {
        return premiered;
    }

    public void setPremiered(LocalDate premiered) {
        this.premiered = premiered;
    }

    public Double getRatingAvg() {
        return ratingAvg;
    }

    public void setRatingAvg(Double ratingAvg) {
        this.ratingAvg = ratingAvg;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getImdbLink() {
        return imdbLink;
    }

    public void setImdbLink(String imdbLink) {
        this.imdbLink = imdbLink;
    }

    public Integer getThetvdbLink() {
        return thetvdbLink;
    }

    public void setThetvdbLink(Integer thetvdbLink) {
        this.thetvdbLink = thetvdbLink;
    }

    public Integer getTvrageLink() {
        return tvrageLink;
    }

    public void setTvrageLink(Integer tvrageLink) {
        this.tvrageLink = tvrageLink;
    }

    public String getPosterMed() {
        return posterMed;
    }

    public void setPosterMed(String posterMed) {
        this.posterMed = posterMed;
    }

    public String getPosterOrg() {
        return posterOrg;
    }

    public void setPosterOrg(String posterOrg) {
        this.posterOrg = posterOrg;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "TvShowSummary{" +
                "tvMazeUrl='" + tvMazeUrl + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", language='" + language + '\'' +
                ", status='" + status + '\'' +
                ", network='" + network + '\'' +
                ", country='" + country + '\'' +
                ", imdbLink='" + imdbLink + '\'' +
                ", posterMed='" + posterMed + '\'' +
                ", posterOrg='" + posterOrg + '\'' +
                ", intPosterMed='" + intPosterMed + '\'' +
                ", intPosterOrg='" + intPosterOrg + '\'' +
                ", summary='" + summary + '\'' +
                ", id=" + id +
                ", tvMazeId=" + tvMazeId +
                ", runtime=" + runtime +
                ", thetvdbLink=" + thetvdbLink +
                ", tvrageLink=" + tvrageLink +
                ", genres=" + genres +
                ", premiered=" + premiered +
                ", ratingAvg=" + ratingAvg +
                ", episodes=" + episodes +
                ", staff=" + staff +
                '}';
    }

}

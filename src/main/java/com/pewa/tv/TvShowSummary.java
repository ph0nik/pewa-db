package com.pewa.tv;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pewa.PewaType;
import com.pewa.common.*;
import com.pewa.status.Status;
import com.pewa.util.CustomLocalDateDeserializer;
import com.pewa.util.CustomLocalDateSerializer;
import com.pewa.util.CustomLocalDateTimeDeserializer;
import com.pewa.util.CustomLocalDateTimeSerializer;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class TvShowSummary implements Comparable<TvShowSummary>, Serializable, Encounter {

    private String tvMazeUrl, title, showType, status, network,
            imdbLink, posterMed, posterOrg, intPosterMed, intPosterOrg, summary;
    private PewaType type;
    private Integer id, tvMazeId, runtime, thetvdbLink, tvrageLink;
    private Country country;
    private Language language;
    private Set<Person> staff;
    private Set<Genre> genres;
    private List<TvShowEpisode> episodes;
    private Integer seasons;
    @JsonDeserialize(using = CustomLocalDateDeserializer.class)
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    private LocalDate premiered;
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    private LocalDateTime dbDatetime;
    private Double ratingAvg;
    private Set<Status> internalStatus;

    public String getShowType() {
        return showType;
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }

    static final long serialVersionUID = 1L;

    TvShowSummary() {
        this.episodes = new ArrayList<>();
        this.genres = new TreeSet<>();
        this.staff = new TreeSet<>();
        this.internalStatus = new TreeSet<>();
    }

    public Integer getSeasons() {
        return seasons;
    }

    private void setSeasons(){
        this.seasons = this.episodes.stream().map(TvShowEpisode::getSeason).max(Comparator.comparing(Integer::valueOf)).get();
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Set<Status> getInternalStatus() {
        return internalStatus;
    }

    public void setInternalStatus(Set<Status> internalStatus) {
        this.internalStatus = internalStatus;
    }

    public void setInternalStatus(Status internalStatus) {
        this.internalStatus.add(internalStatus);
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

    //TODO automatycznie ustawianie ilości sezonów
    public void setEpisodes(TvShowEpisode episodes) {
        this.episodes.add(episodes);
        setSeasons();
    }

    public void setEpisodes(List<TvShowEpisode> episodes) {
        this.episodes = episodes;
        setSeasons();
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

    public PewaType getType() {
        return type;
    }

    @Override
    public void setType(PewaType type) {
        this.type = type;
    }

//    public void setType(String type) {
//        this.type = type;
//    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TvShowSummary that = (TvShowSummary) o;

        if (tvMazeUrl != null ? !tvMazeUrl.equals(that.tvMazeUrl) : that.tvMazeUrl != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (showType != null ? !showType.equals(that.showType) : that.showType != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (network != null ? !network.equals(that.network) : that.network != null) return false;
        if (imdbLink != null ? !imdbLink.equals(that.imdbLink) : that.imdbLink != null) return false;
        if (posterMed != null ? !posterMed.equals(that.posterMed) : that.posterMed != null) return false;
        if (posterOrg != null ? !posterOrg.equals(that.posterOrg) : that.posterOrg != null) return false;
        if (intPosterMed != null ? !intPosterMed.equals(that.intPosterMed) : that.intPosterMed != null) return false;
        if (intPosterOrg != null ? !intPosterOrg.equals(that.intPosterOrg) : that.intPosterOrg != null) return false;
        if (summary != null ? !summary.equals(that.summary) : that.summary != null) return false;
        if (type != that.type) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (tvMazeId != null ? !tvMazeId.equals(that.tvMazeId) : that.tvMazeId != null) return false;
        if (runtime != null ? !runtime.equals(that.runtime) : that.runtime != null) return false;
        if (thetvdbLink != null ? !thetvdbLink.equals(that.thetvdbLink) : that.thetvdbLink != null) return false;
        if (tvrageLink != null ? !tvrageLink.equals(that.tvrageLink) : that.tvrageLink != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (language != null ? !language.equals(that.language) : that.language != null) return false;
        if (staff != null ? !staff.equals(that.staff) : that.staff != null) return false;
        if (genres != null ? !genres.equals(that.genres) : that.genres != null) return false;
        if (episodes != null ? !episodes.equals(that.episodes) : that.episodes != null) return false;
        if (seasons != null ? !seasons.equals(that.seasons) : that.seasons != null) return false;
        if (premiered != null ? !premiered.equals(that.premiered) : that.premiered != null) return false;
        if (dbDatetime != null ? !dbDatetime.equals(that.dbDatetime) : that.dbDatetime != null) return false;
        if (ratingAvg != null ? !ratingAvg.equals(that.ratingAvg) : that.ratingAvg != null) return false;
        return internalStatus != null ? internalStatus.equals(that.internalStatus) : that.internalStatus == null;
    }

    @Override
    public int hashCode() {
        int result = tvMazeUrl != null ? tvMazeUrl.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (showType != null ? showType.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (network != null ? network.hashCode() : 0);
        result = 31 * result + (imdbLink != null ? imdbLink.hashCode() : 0);
        result = 31 * result + (posterMed != null ? posterMed.hashCode() : 0);
        result = 31 * result + (posterOrg != null ? posterOrg.hashCode() : 0);
        result = 31 * result + (intPosterMed != null ? intPosterMed.hashCode() : 0);
        result = 31 * result + (intPosterOrg != null ? intPosterOrg.hashCode() : 0);
        result = 31 * result + (summary != null ? summary.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (tvMazeId != null ? tvMazeId.hashCode() : 0);
        result = 31 * result + (runtime != null ? runtime.hashCode() : 0);
        result = 31 * result + (thetvdbLink != null ? thetvdbLink.hashCode() : 0);
        result = 31 * result + (tvrageLink != null ? tvrageLink.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (language != null ? language.hashCode() : 0);
        result = 31 * result + (staff != null ? staff.hashCode() : 0);
        result = 31 * result + (genres != null ? genres.hashCode() : 0);
        result = 31 * result + (episodes != null ? episodes.hashCode() : 0);
        result = 31 * result + (seasons != null ? seasons.hashCode() : 0);
        result = 31 * result + (premiered != null ? premiered.hashCode() : 0);
        result = 31 * result + (dbDatetime != null ? dbDatetime.hashCode() : 0);
        result = 31 * result + (ratingAvg != null ? ratingAvg.hashCode() : 0);
        result = 31 * result + (internalStatus != null ? internalStatus.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TvShowSummary{" +
                "tvMazeUrl='" + tvMazeUrl + '\'' +
                ", title='" + title + '\'' +
                ", showType='" + showType + '\'' +
                ", status='" + status + '\'' +
                ", network='" + network + '\'' +
                ", imdbLink='" + imdbLink + '\'' +
                ", posterMed='" + posterMed + '\'' +
                ", posterOrg='" + posterOrg + '\'' +
                ", intPosterMed='" + intPosterMed + '\'' +
                ", intPosterOrg='" + intPosterOrg + '\'' +
                ", summary='" + summary + '\'' +
                ", type=" + type +
                ", id=" + id +
                ", tvMazeId=" + tvMazeId +
                ", runtime=" + runtime +
                ", thetvdbLink=" + thetvdbLink +
                ", tvrageLink=" + tvrageLink +
                ", country=" + country +
                ", language=" + language +
                ", staff=" + staff +
                ", genres=" + genres +
                ", episodes=" + episodes +
                ", seasons=" + seasons +
                ", premiered=" + premiered +
                ", dbDatetime=" + dbDatetime +
                ", ratingAvg=" + ratingAvg +
                ", internalStatus=" + internalStatus +
                '}';
    }

    @Override
    public int compareTo(TvShowSummary tvshow) {
        return this.title.compareTo(tvshow.title);
    }
}

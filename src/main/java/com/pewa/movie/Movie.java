package com.pewa.movie;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pewa.MediaModel;
import com.pewa.PewaType;
import com.pewa.common.*;
import com.pewa.config.ConfigFactory;
import com.pewa.status.Status;
import com.pewa.util.CustomLocalDateDeserializer;
import com.pewa.util.CustomLocalDateSerializer;
import com.pewa.util.CustomLocalDateTimeDeserializer;
import com.pewa.util.CustomLocalDateTimeSerializer;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Component
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Movie extends MediaModel implements Comparable<Movie>, Serializable, Encounter {

    private String title, titlePl, intPoster, plot, engTitle;
    @JsonIgnore
    private String poster;
    @JsonDeserialize(using = CustomLocalDateDeserializer.class)
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    private LocalDate relDate;
    @JsonDeserialize(using = CustomLocalDateDeserializer.class)
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    private LocalDate relDatePl;
    private Integer year, id, runtime, tmdbId;
    private Double tmdbRating;
//    @JsonProperty(value = "StandardImdbId")
    @JsonIgnore
    private Integer imdbID;
    private String imdbLink;
    private Set<Language> language;
    private Set<Country> country;
    private Set<Genre> genres;
    private Set<Person> staff;
    private PewaType type;
    private Set<Status> status;

    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dbDatetime;

    public Movie() {
        this.genres = new TreeSet<>();
        this.language = new TreeSet<>();
        this.country = new TreeSet<>();
        this.status = new TreeSet<>();
        this.staff = new TreeSet<>();
    }

    public String getEngTitle() {
        return engTitle;
    }

    public void setEngTitle(String engTitle) {
        this.engTitle = engTitle;
    }

    public Double getTmdbRating() {
        return tmdbRating;
    }

    public void setTmdbRating(Double tmdbRating) {
        this.tmdbRating = tmdbRating;
    }

    public Integer getImdbID() {
        return imdbID;
    }

    public void setImdbID(Integer imdbID) {
        this.imdbID = imdbID;
        String url = ConfigFactory.get("custom-getters.imdb");
        String imdbIdString = this.imdbID.toString().replaceFirst("11","tt");
        this.imdbLink = url.replaceAll("<id>", imdbIdString);
    }

    public String getImdbLink() {
        return imdbLink;
    }

    public Integer getTmdbId() {
        return tmdbId;
    }

    public void setTmdbId(Integer tmdbId) {
        this.tmdbId = tmdbId;
    }

    public Set<Status> getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status.add(status);
    }

    public void setStatus(Set<Status> status) {
        this.status = status;
    }

    public LocalDateTime getDbDatetime() {
        return dbDatetime;
    }

    public void setDbDatetime(LocalDateTime dbDatetime) {
        this.dbDatetime = dbDatetime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitlePl() {
        return titlePl;
    }

    public void setTitlePl(String titlePl) {
        this.titlePl = titlePl;
    }

    public PewaType getType() {
        return type;
    }

    public void setType(PewaType type) {
        this.type = type;
    }

    public LocalDate getRelDatePl() {
        return relDatePl;
    }

    public void setRelDatePl(LocalDate relDatePl) {
        this.relDatePl = relDatePl;
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

    public String getIntPoster() {
        return intPoster;
    }

    public void setIntPoster(String intPoster) {
        this.intPoster = intPoster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public LocalDate getRelDate() {
        return relDate;
    }

    public void setRelDate(LocalDate relDate) {
        this.relDate = relDate;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genre) {
        this.genres = genre;
    }

    public void setGenres(Genre genre) {
        this.genres.add(genre);
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public Set<Language> getLanguage() {
        return language;
    }

    public void setLanguage(Set<Language> language) {
        this.language = language;
    }

    public void setLanguage(Language language) {
        this.language.add(language);
    }

    public Set<Country> getCountry() {
        return country;
    }

    public void setCountry(Set<Country> country) {
        this.country = country;
    }

    public void setCountry(Country country) {
        this.country.add(country);
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }


    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", titlePl='" + titlePl + '\'' +
                ", intPoster='" + intPoster + '\'' +
                ", plot='" + plot + '\'' +
                ", engTitle='" + engTitle + '\'' +
                ", poster='" + poster + '\'' +
                ", relDate=" + relDate +
                ", relDatePl=" + relDatePl +
                ", year=" + year +
                ", id=" + id +
                ", runtime=" + runtime +
                ", tmdbId=" + tmdbId +
                ", tmdbRating=" + tmdbRating +
                ", imdbID=" + imdbID +
                ", imdbLink='" + imdbLink + '\'' +
                ", language=" + language +
                ", country=" + country +
                ", genres=" + genres +
                ", staff=" + staff +
                ", type=" + type +
                ", status=" + status +
                ", dbDatetime=" + dbDatetime +
                '}';
    }

    public boolean isEmpty() {
        return this.title == null || this.title.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (title != null ? !title.equals(movie.title) : movie.title != null) return false;
        if (titlePl != null ? !titlePl.equals(movie.titlePl) : movie.titlePl != null) return false;
        if (intPoster != null ? !intPoster.equals(movie.intPoster) : movie.intPoster != null) return false;
        if (plot != null ? !plot.equals(movie.plot) : movie.plot != null) return false;
        if (engTitle != null ? !engTitle.equals(movie.engTitle) : movie.engTitle != null) return false;
        if (poster != null ? !poster.equals(movie.poster) : movie.poster != null) return false;
        if (relDate != null ? !relDate.equals(movie.relDate) : movie.relDate != null) return false;
        if (relDatePl != null ? !relDatePl.equals(movie.relDatePl) : movie.relDatePl != null) return false;
        if (year != null ? !year.equals(movie.year) : movie.year != null) return false;
        if (id != null ? !id.equals(movie.id) : movie.id != null) return false;
        if (runtime != null ? !runtime.equals(movie.runtime) : movie.runtime != null) return false;
        if (tmdbId != null ? !tmdbId.equals(movie.tmdbId) : movie.tmdbId != null) return false;
        if (tmdbRating != null ? !tmdbRating.equals(movie.tmdbRating) : movie.tmdbRating != null) return false;
        if (imdbID != null ? !imdbID.equals(movie.imdbID) : movie.imdbID != null) return false;
        if (imdbLink != null ? !imdbLink.equals(movie.imdbLink) : movie.imdbLink != null) return false;
        if (language != null ? !language.equals(movie.language) : movie.language != null) return false;
        if (country != null ? !country.equals(movie.country) : movie.country != null) return false;
        if (genres != null ? !genres.equals(movie.genres) : movie.genres != null) return false;
        if (staff != null ? !staff.equals(movie.staff) : movie.staff != null) return false;
        if (type != movie.type) return false;
        if (status != null ? !status.equals(movie.status) : movie.status != null) return false;
        return dbDatetime != null ? dbDatetime.equals(movie.dbDatetime) : movie.dbDatetime == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (titlePl != null ? titlePl.hashCode() : 0);
        result = 31 * result + (intPoster != null ? intPoster.hashCode() : 0);
        result = 31 * result + (plot != null ? plot.hashCode() : 0);
        result = 31 * result + (engTitle != null ? engTitle.hashCode() : 0);
        result = 31 * result + (poster != null ? poster.hashCode() : 0);
        result = 31 * result + (relDate != null ? relDate.hashCode() : 0);
        result = 31 * result + (relDatePl != null ? relDatePl.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (runtime != null ? runtime.hashCode() : 0);
        result = 31 * result + (tmdbId != null ? tmdbId.hashCode() : 0);
        result = 31 * result + (tmdbRating != null ? tmdbRating.hashCode() : 0);
        result = 31 * result + (imdbID != null ? imdbID.hashCode() : 0);
        result = 31 * result + (imdbLink != null ? imdbLink.hashCode() : 0);
        result = 31 * result + (language != null ? language.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (genres != null ? genres.hashCode() : 0);
        result = 31 * result + (staff != null ? staff.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (dbDatetime != null ? dbDatetime.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Movie o) {
        return this.title.equals(o.title) ? this.tmdbId.compareTo(o.tmdbId) : this.getTitle().compareTo(o.getTitle());
    }

}

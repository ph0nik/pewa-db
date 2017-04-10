package com.pewa.movie;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pewa.MediaModel;
import com.pewa.PewaType;
import com.pewa.common.Country;
import com.pewa.common.Genre;
import com.pewa.common.Language;
import com.pewa.common.Person;
import com.pewa.util.CustomLocalDateTimeDeserializer;
import com.pewa.util.CustomLocalDateTimeSerializer;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Movie extends MediaModel implements Comparable<Movie>, Serializable {

    private String title, titlePl, awards, metascore, imdbRating, imdbVotes, imdbID, intPoster, runtime, plot, ageRating, relDate;
    @JsonIgnore
    private String poster;
    private LocalDate relDatePl;
    private Integer year, id;
    private Set<Language> language;
    private Set<Country> country;
    private Set<Genre> genres;
    private Set<Person> staff;
    private PewaType type;

    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dbDatetime;

    public Movie() {
        this.genres = new TreeSet<>();
        this.language = new TreeSet<>();
        this.country = new TreeSet<>();
        this.staff = new TreeSet<>();
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

    public String getAgeRating() {
        return ageRating;
    }

    public void setAgeRating(String ageRating) {
        this.ageRating = ageRating;
    }

    public String getRelDate() {
        return relDate;
    }

    public void setRelDate(String relDate) {
        this.relDate = relDate;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
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

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getMetascore() {
        return metascore;
    }

    public void setMetascore(String metascore) {
        this.metascore = metascore;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", titlePl='" + titlePl + '\'' +
                ", awards='" + awards + '\'' +
                ", poster='" + poster + '\'' +
                ", metascore='" + metascore + '\'' +
                ", imdbRating='" + imdbRating + '\'' +
                ", imdbVotes='" + imdbVotes + '\'' +
                ", imdbID='" + imdbID + '\'' +
                ", intPoster='" + intPoster + '\'' +
                ", runtime='" + runtime + '\'' +
                ", plot='" + plot + '\'' +
                ", ageRating='" + ageRating + '\'' +
                ", relDate='" + relDate + '\'' +
                ", relDatePl=" + relDatePl +
                ", year=" + year +
                ", id=" + id +
                ", language=" + language +
                ", country=" + country +
                ", genres=" + genres +
                ", staff=" + staff +
                ", type=" + type +
                ", dbDatetime=" + dbDatetime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (title != null ? !title.equals(movie.title) : movie.title != null) return false;
        if (titlePl != null ? !titlePl.equals(movie.titlePl) : movie.titlePl != null) return false;
        if (awards != null ? !awards.equals(movie.awards) : movie.awards != null) return false;
        if (poster != null ? !poster.equals(movie.poster) : movie.poster != null) return false;
        if (metascore != null ? !metascore.equals(movie.metascore) : movie.metascore != null) return false;
        if (imdbRating != null ? !imdbRating.equals(movie.imdbRating) : movie.imdbRating != null) return false;
        if (imdbVotes != null ? !imdbVotes.equals(movie.imdbVotes) : movie.imdbVotes != null) return false;
        if (imdbID != null ? !imdbID.equals(movie.imdbID) : movie.imdbID != null) return false;
        if (intPoster != null ? !intPoster.equals(movie.intPoster) : movie.intPoster != null) return false;
        if (runtime != null ? !runtime.equals(movie.runtime) : movie.runtime != null) return false;
        if (plot != null ? !plot.equals(movie.plot) : movie.plot != null) return false;
        if (ageRating != null ? !ageRating.equals(movie.ageRating) : movie.ageRating != null) return false;
        if (relDate != null ? !relDate.equals(movie.relDate) : movie.relDate != null) return false;
        if (relDatePl != null ? !relDatePl.equals(movie.relDatePl) : movie.relDatePl != null) return false;
        if (year != null ? !year.equals(movie.year) : movie.year != null) return false;
        if (language != null ? !language.equals(movie.language) : movie.language != null) return false;
        if (country != null ? !country.equals(movie.country) : movie.country != null) return false;
        if (genres != null ? !genres.equals(movie.genres) : movie.genres != null) return false;
        if (staff != null ? !staff.equals(movie.staff) : movie.staff != null) return false;
        return type == movie.type;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (titlePl != null ? titlePl.hashCode() : 0);
        result = 31 * result + (awards != null ? awards.hashCode() : 0);
        result = 31 * result + (poster != null ? poster.hashCode() : 0);
        result = 31 * result + (metascore != null ? metascore.hashCode() : 0);
        result = 31 * result + (imdbRating != null ? imdbRating.hashCode() : 0);
        result = 31 * result + (imdbVotes != null ? imdbVotes.hashCode() : 0);
        result = 31 * result + (imdbID != null ? imdbID.hashCode() : 0);
        result = 31 * result + (intPoster != null ? intPoster.hashCode() : 0);
        result = 31 * result + (runtime != null ? runtime.hashCode() : 0);
        result = 31 * result + (plot != null ? plot.hashCode() : 0);
        result = 31 * result + (ageRating != null ? ageRating.hashCode() : 0);
        result = 31 * result + (relDate != null ? relDate.hashCode() : 0);
        result = 31 * result + (relDatePl != null ? relDatePl.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (language != null ? language.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (genres != null ? genres.hashCode() : 0);
        result = 31 * result + (staff != null ? staff.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Movie o) {
        return this.title.equals(o.title) ? this.year.compareTo(o.year) : this.getTitle().compareTo(o.getTitle());
    }

}

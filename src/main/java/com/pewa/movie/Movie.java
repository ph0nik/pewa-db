package com.pewa.movie;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Movie implements Comparable<Movie> {
    private String title;
    private int year;
    private String ageRating;
    private String relDate;
    private String runtime;
    private Set<String> genre;
    private Set<String> director;
    private Set<String> writer;
    private Set<String> actors;
    private String plot;
    private Set<String> language;
    private Set<String> country;
    private String awards;
    private String poster;
    private String metascore;
    private String imdbRating;
    private String imdbVotes;
    private String imdbID;
    private String intPoster;

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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
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

    public Set<String> getGenre() {
        return genre;
    }

    public void setGenre(Set<String> genre) {
        this.genre = genre;
    }

    public Set<String> getDirector() {
        return director;
    }

    public void setDirector(Set<String> director) {
        this.director = director;
    }

    public Set<String> getWriter() {
        return writer;
    }

    public void setWriter(Set<String> writer) {
        this.writer = writer;
    }

    public Set<String> getActors() {
        return actors;
    }

    public void setActors(Set<String> actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public Set<String> getLanguage() {
        return language;
    }

    public void setLanguage(Set<String> language) {
        this.language = language;
    }

    public Set<String> getCountry() {
        return country;
    }

    public void setCountry(Set<String> country) {
        this.country = country;
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
                ", year=" + year +
                ", ageRating='" + ageRating + '\'' +
                ", relDate='" + relDate + '\'' +
                ", runtime='" + runtime + '\'' +
                ", genre=" + genre +
                ", director=" + director +
                ", writer=" + writer +
                ", actors=" + actors +
                ", plot='" + plot + '\'' +
                ", language=" + language +
                ", country=" + country +
                ", awards='" + awards + '\'' +
                ", poster='" + poster + '\'' +
                ", metascore='" + metascore + '\'' +
                ", imdbRating='" + imdbRating + '\'' +
                ", imdbVotes='" + imdbVotes + '\'' +
                ", imdbID='" + imdbID + '\'' +
                ", intPoster='" + intPoster + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (year != movie.year) return false;
        if (title != null ? !title.equals(movie.title) : movie.title != null) return false;
        if (ageRating != null ? !ageRating.equals(movie.ageRating) : movie.ageRating != null) return false;
        if (relDate != null ? !relDate.equals(movie.relDate) : movie.relDate != null) return false;
        if (runtime != null ? !runtime.equals(movie.runtime) : movie.runtime != null) return false;
        if (genre != null ? !genre.equals(movie.genre) : movie.genre != null) return false;
        if (director != null ? !director.equals(movie.director) : movie.director != null) return false;
        if (writer != null ? !writer.equals(movie.writer) : movie.writer != null) return false;
        if (actors != null ? !actors.equals(movie.actors) : movie.actors != null) return false;
        if (plot != null ? !plot.equals(movie.plot) : movie.plot != null) return false;
        if (language != null ? !language.equals(movie.language) : movie.language != null) return false;
        if (country != null ? !country.equals(movie.country) : movie.country != null) return false;
        if (awards != null ? !awards.equals(movie.awards) : movie.awards != null) return false;
        if (poster != null ? !poster.equals(movie.poster) : movie.poster != null) return false;
        if (metascore != null ? !metascore.equals(movie.metascore) : movie.metascore != null) return false;
        if (imdbRating != null ? !imdbRating.equals(movie.imdbRating) : movie.imdbRating != null) return false;
        if (imdbVotes != null ? !imdbVotes.equals(movie.imdbVotes) : movie.imdbVotes != null) return false;
        return imdbID != null ? imdbID.equals(movie.imdbID) : movie.imdbID == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + year;
        result = 31 * result + (ageRating != null ? ageRating.hashCode() : 0);
        result = 31 * result + (relDate != null ? relDate.hashCode() : 0);
        result = 31 * result + (runtime != null ? runtime.hashCode() : 0);
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        result = 31 * result + (director != null ? director.hashCode() : 0);
        result = 31 * result + (writer != null ? writer.hashCode() : 0);
        result = 31 * result + (actors != null ? actors.hashCode() : 0);
        result = 31 * result + (plot != null ? plot.hashCode() : 0);
        result = 31 * result + (language != null ? language.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (awards != null ? awards.hashCode() : 0);
        result = 31 * result + (poster != null ? poster.hashCode() : 0);
        result = 31 * result + (metascore != null ? metascore.hashCode() : 0);
        result = 31 * result + (imdbRating != null ? imdbRating.hashCode() : 0);
        result = 31 * result + (imdbVotes != null ? imdbVotes.hashCode() : 0);
        result = 31 * result + (imdbID != null ? imdbID.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Movie o) {
        return this.getTitle().compareTo(o.getTitle());
    }
}

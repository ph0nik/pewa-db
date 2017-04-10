package com.pewa.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pewa.anime.Anime;
import com.pewa.anime.Manga;
import com.pewa.book.Book;
import com.pewa.movie.Movie;
import com.pewa.tv.TvShowSummary;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by phonik on 2017-04-08.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Component
public class Results {

    private String message;
    private Integer resultsFound;
    private List<Movie> movies;
    private List<Book> books;
    private List<TvShowSummary> tvshows;
    private List<Anime> animes;
    private List<Manga> mangas;
    private Integer rowsAffected;


    public Results() {
        this.resultsFound = 0;
    }

    public Results setRowsAffected(Integer rows) {
        this.rowsAffected = rows;
        return this;
    }

    public Integer getRowsAffected() {
        return this.rowsAffected;
    }

    public Results setMessage() {
        this.message = (this.resultsFound != 0) ? "OK" : "No results found";
        return this;
    }

    public Results setMovies(List<Movie> movies) {
        this.movies = movies;
        this.resultsFound =+ movies.size();
        return this;
    }

    public Results setTvshows(List<TvShowSummary> tvshows) {
        this.tvshows = tvshows;
        this.resultsFound =+ tvshows.size();
        return this;
    }

    public Results setBooks(List<Book> books) {
        this.books = books;
        this.resultsFound =+ books.size();
        return this;
    }

    public Results setAnimes(List<Anime> animes) {
        this.animes = animes;
        this.resultsFound =+ animes.size();
        return this;
    }

    public Results setMangas(List<Manga> mangas) {
        this.mangas = mangas;
        this.resultsFound =+ mangas.size();
        return this;
    }

    public String getMessage() {
        return message;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<TvShowSummary> getTvshows() {
        return tvshows;
    }

    public List<Anime> getAnimes() {
        return animes;
    }

    public List<Manga> getMangas() {
        return mangas;
    }

    public Integer getResultsFound() {
        return resultsFound;
    }

    @Override
    public String toString() {
        return "Results{" +
                "message='" + message + '\'' +
                ", resultsFound=" + resultsFound +
                ", movies=" + movies +
                ", books=" + books +
                ", tvshows=" + tvshows +
                ", animes=" + animes +
                ", mangas=" + mangas +
                '}';
    }
}

package com.pewa.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Genre implements Comparable<Genre>, Serializable {
    private Integer id;
    private String genre;

    static final long serialVersionUID = 1L;

    public Genre() {}

    public Genre(Integer id) {
        this.id = id;
    }

    public Genre(String name) {
        this.genre = (name == null || "".equals(name)) ? Empty.NOT_AVAILABLE : name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", genre='" + genre + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Genre genre = (Genre) o;

        return this.genre != null ? this.genre.equals(genre.genre) : genre.genre == null;
    }

    @Override
    public int hashCode() {
        return genre != null ? genre.hashCode() : 0;
    }

    @Override
    public int compareTo(Genre o) {
        return this.getGenre().compareTo(o.getGenre());
    }
}

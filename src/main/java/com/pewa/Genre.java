package com.pewa;

public class Genre implements Comparable<Genre> {
    private int id;
    private String genre;

    public Genre() {}

    public Genre(String name) {
        this.genre = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

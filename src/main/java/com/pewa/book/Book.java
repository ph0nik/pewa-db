package com.pewa.book;

import com.pewa.Form;
import com.pewa.Genre;
import com.pewa.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Book {
    private int id;
    private Set<Person> people;
    private Set<Genre> genre;
    private Set<Form> form;
    private String additionalInfo;
    private String originalTitle;
    private String polishTitle;
    private String originalLanguage;
    private String category;
    private String altVersion;
    private String idBiblion;
    private Double rating;
    private int votes;
    private int firstPubDate;
    private int plPubDate;

    public Book() {
        this.people = new TreeSet<>();
        this.genre = new TreeSet<>();
        this.form = new TreeSet<>();
    }
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", people=" + people +
                ", genre=" + genre +
                ", form=" + form +
                ", additionalInfo='" + additionalInfo + '\'' +
                ", originalTitle='" + originalTitle + '\'' +
                ", polishTitle='" + polishTitle + '\'' +
                ", originalLanguage='" + originalLanguage + '\'' +
                ", category='" + category + '\'' +
                ", altVersion='" + altVersion + '\'' +
                ", idBiblion='" + idBiblion + '\'' +
                ", rating=" + rating +
                ", votes=" + votes +
                ", firstPubDate=" + firstPubDate +
                ", plPubDate=" + plPubDate +
                '}';
    }

    public Set<Person> getPeople() {
        return people;
    }

    public void setPeople(Person person) {
        this.people.add(person);
    }
    public void setPeople(Set<Person> people) {
        this.people = people;
    }

    public String getIdBiblion() {
        return idBiblion;
    }

    void setIdBiblion(String idBiblion) {
        this.idBiblion = idBiblion;
    }

    public String getPolishTitle() {
        return polishTitle;
    }

    void setPolishTitle(String polishTitle) {
        this.polishTitle = polishTitle;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getCategory() {
        return category;
    }

    void setCategory(String category) {
        this.category = category;
    }

    public Set<Genre> getGenre() {
        return genre;
    }

    void setGenre(Genre genre) {
        this.genre.add(genre);
    }

    public Set<Form> getForm() {
        return form;
    }

    void setForm(Form form) {
        this.form.add(form);
    }

    public int getFirstPubDate() {
        return firstPubDate;
    }

    void setFirstPubDate(int firstPubDate) {
        this.firstPubDate = firstPubDate;
    }

    public int getPlPubDate() {
        return plPubDate;
    }

    void setPlPubDate(int plPubDate) {
        this.plPubDate = plPubDate;
    }

    public String getAltVersion() {
        return altVersion;
    }

    void setAltVersion(String altVersion) {
        this.altVersion = altVersion;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public Double getRating() {
        return rating;
    }

    void setRating(Double rating) {
        this.rating = rating;
    }

    public int getVotes() {
        return votes;
    }

    void setVotes(int votes) {
        this.votes = votes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}

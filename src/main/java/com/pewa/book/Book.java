package com.pewa.book;

import com.pewa.PewaType;
import com.pewa.common.Form;
import com.pewa.common.Genre;
import com.pewa.common.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Book {
    private Integer id, votes, firstPubDate, plPubDate, idBiblion;
    private Double rating;
    private Set<Person> people;
    private Set<Genre> genre;
    private Set<Form> form;
    private PewaType type;
    private String additionalInfo, originalTitle, polishTitle, originalLanguage, category, altVersion;

 List a = new ArrayList();

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

    public void setGenre(Set<Genre> genre) {
        this.genre = genre;
    }

    public void setForm(Set<Form> form) {
        this.form = form;
    }

    public PewaType getType() {
        return type;
    }

    public void setType(PewaType type) {
        this.type = type;
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

    public Integer getIdBiblion() {
        return idBiblion;
    }

    void setIdBiblion(Integer idBiblion) {
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

    public Integer getFirstPubDate() {
        return firstPubDate;
    }

    void setFirstPubDate(Integer firstPubDate) {
        this.firstPubDate = firstPubDate;
    }

    public Integer getPlPubDate() {
        return plPubDate;
    }

    void setPlPubDate(Integer plPubDate) {
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

    public Integer getVotes() {
        return votes;
    }

    void setVotes(Integer votes) {
        this.votes = votes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}

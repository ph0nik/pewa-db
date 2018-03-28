package com.pewa.book;

import com.pewa.MediaModel;
import com.pewa.PewaType;
import com.pewa.common.*;
import com.pewa.status.Status;
import org.springframework.stereotype.Component;

import javax.persistence.metamodel.EmbeddableType;
import java.io.Serializable;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Set;
import java.util.TreeSet;

@Component
public class Book extends MediaModel implements Comparable<Book>, Serializable, Encounter {

    private Integer id, votes, firstPubDate, plPubDate, externalBookId;
    private Double rating;
    private Set<Person> people;
    private Set<Genre> genre;
    private Set<Form> form;
    private PewaType type;
    private String additionalInfo, originalTitle, polishTitle, originalLanguage, category;
    private Set<Status> status;

    public Book() {
        this.people = new TreeSet<>();
        this.genre = new TreeSet<>();
        this.form = new TreeSet<>();
        this.status = new TreeSet<>();
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", votes=" + votes +
                ", firstPubDate=" + firstPubDate +
                ", plPubDate=" + plPubDate +
                ", externalBookId=" + externalBookId +
                ", rating=" + rating +
                ", people=" + people +
                ", genre=" + genre +
                ", form=" + form +
                ", type=" + type +
                ", additionalInfo='" + additionalInfo + '\'' +
                ", originalTitle='" + originalTitle + '\'' +
                ", polishTitle='" + polishTitle + '\'' +
                ", originalLanguage='" + originalLanguage + '\'' +
                ", category='" + category + '\'' +
                ", status=" + status +
                '}';
    }

    public Set<Status> getStatus() {
        return status;
    }

    public void setStatus(Set<Status> status) {
        this.status = status;
    }

    public void setStatus(Status status) {
        this.status.add(status);
    }

    public void setGenre(Set<Genre> genre) {
        if (genre.isEmpty()) {
            this.genre.add(new Genre(Empty.NOT_AVAILABLE));
        } else {
            this.genre = genre;
        }
    }

    public void setForm(Set<Form> form) {
        if (form.isEmpty()) {
            this.form.add(new Form(Empty.NOT_AVAILABLE));
        }
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

    public Integer getExternalBookId() {
        return externalBookId;
    }

    void setExternalBookId(Integer externalBookId) {
        this.externalBookId = externalBookId;
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
        this.originalLanguage = (originalLanguage == null || "".equals(originalLanguage)) ? Empty.NOT_AVAILABLE : originalLanguage;
    }

    public String getCategory() {
        return category;
    }

    void setCategory(String category) {
        this.category = (category == null || "".equals(category)) ? Empty.NOT_AVAILABLE : category;
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
        this.firstPubDate = (firstPubDate == null) ? 0 : firstPubDate;
    }

    public Integer getPlPubDate() {
        return plPubDate;
    }

    void setPlPubDate(Integer plPubDate) {
        this.plPubDate = (plPubDate == null) ? 0 : plPubDate;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = (additionalInfo == null || "".equals(additionalInfo)) ? Empty.NOT_AVAILABLE : additionalInfo;
    }

    public Double getRating() {
        return rating;
    }

    void setRating(Double rating) {
        this.rating = (rating == null) ? 0 : rating;
    }

    public Integer getVotes() {
        return votes;
    }

    void setVotes(Integer votes) {
        this.votes = (votes == null) ? 0 : votes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int compareTo(Book book) {
        return this.originalTitle.compareTo(book.originalTitle);
    }

    // TODO
    @Override
    public boolean isEmpty() {
        return false;
    }
}

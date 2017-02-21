package com.pewa.book;

import java.util.Arrays;

public class Book {
    private String[] author;
    private String originalTitle;
    private String polishTitle;
    private String originalLanguage;
    private String[] translator;
    private String category;
    private String[] genre;
    private String[] form;
    private int firstPubDate;
    private int plPubDate;
    private String altVersion;
    private String[] additionalInfo;
    private Double rating;
    private int votes;
    private String id;

    @Override
    public String toString() {
        return "com.pewa.book.Book{" +
                "author=" + Arrays.toString(author) +
                ", originalTitle='" + originalTitle + '\'' +
                ", polishTitle='" + polishTitle + '\'' +
                ", originalLanguage='" + originalLanguage + '\'' +
                ", translator=" + Arrays.toString(translator) +
                ", category='" + category + '\'' +
                ", genre=" + Arrays.toString(genre) +
                ", form=" + Arrays.toString(form) +
                ", firstPubDate=" + firstPubDate +
                ", plPubDate=" + plPubDate +
                ", altVersion='" + altVersion + '\'' +
                ", additionalInfo=" + Arrays.toString(additionalInfo) +
                ", rating=" + rating +
                ", votes=" + votes +
                '}';
    }

    public String getId() {
        return id;
    }

    void setId(String id) {
        this.id = id;
    }

    public String getPolishTitle() {
        return polishTitle;
    }

    void setPolishTitle(String polishTitle) {
        this.polishTitle = polishTitle;
    }

    public String[] getAuthor() {
        return author;
    }

    void setAuthor(String[] author) {
        this.author = author;
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

    public String[] getTranslator() {
        return translator;
    }

    void setTranslator(String[] translator) {
        this.translator = translator;
    }

    public String getCategory() {
        return category;
    }

    void setCategory(String category) {
        this.category = category;
    }

    public String[] getGenre() {
        return genre;
    }

    void setGenre(String[] genre) {
        this.genre = genre;
    }

    public String[] getForm() {
        return form;
    }

    void setForm(String[] form) {
        this.form = form;
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

    public String[] getAdditionalInfo() {
        return additionalInfo;
    }

    void setAdditionalInfo(String[] additionalInfo) {
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
}

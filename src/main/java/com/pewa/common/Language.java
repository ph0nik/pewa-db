package com.pewa.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.codec.language.bm.Lang;

import java.io.Serializable;

/**
 * Created by phonik on 2017-04-04.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Language implements Comparable<Language>, Serializable {
    private Integer id;
    private String language;

    static final long serialVersionUID = 1L;

    public Language() {}

    public Language(Integer id) {
        this.id = id;
    }

    public Language(String lang) {
        this.language = lang;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "Language{" +
                "id=" + id +
                ", language='" + language + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Language language1 = (Language) o;

        if (id != language1.id) return false;
        return language != null ? language.equals(language1.language) : language1.language == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (language != null ? language.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Language o) {
        return o.language.compareTo(o.language);
    }
}

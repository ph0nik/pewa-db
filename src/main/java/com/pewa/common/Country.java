package com.pewa.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * Created by phonik on 2017-04-04.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Country implements Comparable<Country>, Serializable{
    private Integer id;
    private String country;

    static final long serialVersionUID = 1L;

    public Country() {}

    public Country(String country) {
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", country='" + country + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country country1 = (Country) o;

        if (id != null ? !id.equals(country1.id) : country1.id != null) return false;
        return country != null ? country.equals(country1.country) : country1.country == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Country o) {
        return this.country.compareTo(o.country);
    }
}

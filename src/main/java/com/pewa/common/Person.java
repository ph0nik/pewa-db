package com.pewa.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.stereotype.Component;

import java.io.Serializable;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Component
public class Person implements Comparable<Person>, Serializable {

    private int id;
    private String name;
    private String job;

    static final long serialVersionUID = 1L;

    public Person() {}

    public Person(Integer id) {
        this.id = id;
    }

    public Person(String firstName, String lastName, String job) {
        this.job = job;
        this.name = new StringBuilder(firstName)
                .append(" ")
                .append(lastName)
                .toString()
                .trim();
    }

    public Person(int id, String name, String job) {
        this.id = id;
        this.name = name;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (id != person.id) return false;
        if (name != null ? !name.equals(person.name) : person.name != null) return false;
        return job != null ? job.equals(person.job) : person.job == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (job != null ? job.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Person person) {
        if (this.name.equals(person.name)) {
            return this.job.compareTo(person.job);
        } else {
            return this.name.compareTo(person.name);
        }

    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", job='" + job + '\'' +
                '}';
    }
}

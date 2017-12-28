package com.pewa.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS , value = WebApplicationContext.SCOPE_REQUEST)
public class Results {

    private String message;
    private Integer resultsFound;
    private List<Encounter> encounters;
    private Integer rowsAffected;

    public List<Encounter> getEncounters() {
        return encounters;
    }

    //przeniesiony konstruktor listy z konstruktora obiektu do funkcji poniżęj
    public void setEncounters(Encounter encouter) {
        if (this.encounters == null) {
            this.encounters = new ArrayList<>();
        }
        this.encounters.add(encouter);
        this.resultsFound += encounters.size();
    }

    public void add(Results results) {
        if (results.getEncounters() != null) {
            results.getEncounters().forEach(this::setEncounters);
        }

    }

    public Results() {
        this.resultsFound = 0;
        this.rowsAffected = 0;
    }

    public Results setRowsAffected(Integer rows) {
        this.rowsAffected = rows;
        return this;
    }

    public Integer getRowsAffected() {
        return this.rowsAffected;
    }

    public Results setReturnMessage() {
        this.message = (this.resultsFound != 0) ? "OK" : "No results found";
        return this;
    }

    public Results setReturnMessage(String message) {
        this.message = message;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Integer getResultsFound() {
        return resultsFound;
    }

    @Override
    public String toString() {
        return "Results{" +
                "message='" + message + '\'' +
                ", resultsFound=" + resultsFound +
                ", encounters=" + encounters +
                ", rowsAffected=" + rowsAffected +
                '}';
    }
}

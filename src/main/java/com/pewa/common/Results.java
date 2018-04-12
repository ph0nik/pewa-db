package com.pewa.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pewa.MediaModel;
import com.pewa.anime.anilist.Media;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS , value = WebApplicationContext.SCOPE_REQUEST)
public class Results implements Iterable<MediaModel> {

    private String message;
    private Integer resultsFound;
    private List<MediaModel> encounters;
    private Integer rowsAffected;

    public Results() {
        this.encounters = new ArrayList<>();
        this.resultsFound = 0;
        this.rowsAffected = 0;
    }

    public List<MediaModel> getEncounters() {
        return encounters;
    }

    public void setEncounters(MediaModel encouter) {
        this.encounters.add(encouter);
        this.resultsFound = this.encounters.size();
    }

    public void add(Results results) {
        if (results.getEncounters() != null) {
            results.getEncounters().forEach(this::setEncounters);
        }
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

    @Override
    public Iterator<MediaModel> iterator() {
        return new MediaModelIterator();
    }

    private class MediaModelIterator implements Iterator<MediaModel> {
        private Iterator encountersIterator = encounters.iterator();

        @Override
        public boolean hasNext() {
            return encountersIterator.hasNext();
        }

        @Override
        public MediaModel next() {
            return (MediaModel) encountersIterator.next();
        }
    }
}

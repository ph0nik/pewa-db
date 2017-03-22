package com.pewa.controller;

import com.pewa.PewaType;
import com.pewa.movie.Movie;

import java.time.LocalDate;

/**
 * Created by phonik on 2017-03-02.
 */
public class Encounter {

    private LocalDate inputDate;
    private PewaType type;
    private String source; // cinema, computer, webstream, tv, video, dvd, bluray, other
    private int counter; // 1 dla oglądanego po raz pierwszy, 2... dla każdego kolejnego
    private int rating; // 1 ... 10
    private String comment;

    public LocalDate getInputDate() {
        return inputDate;
    }

    public void setInputDate(LocalDate inputDate) {
        this.inputDate = inputDate;
    }

    public PewaType getType() {
        return type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void setType(PewaType type) {
        this.type = type;
    }

}

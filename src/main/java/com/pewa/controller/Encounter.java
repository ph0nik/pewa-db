package com.pewa.controller;

import com.pewa.MediaSource;
import com.pewa.PewaType;
import com.pewa.movie.Movie;

import java.time.LocalDate;

public class Encounter {

    private LocalDate inputDate;
    private PewaType type;
    private MediaSource source; // cinema, computer, webstream, tv, video, dvd, bluray, other
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

    public MediaSource getSource() {
        return source;
    }

    public void setSource(MediaSource source) {
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

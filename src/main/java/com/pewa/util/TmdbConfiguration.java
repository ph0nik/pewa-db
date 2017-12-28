package com.pewa.util;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import com.pewa.anime.AnimeAccessToken;
import com.pewa.config.ConfigFactory;
import com.pewa.movie.MovieSearchTheMovieDatabase;
import com.thoughtworks.xstream.XStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by phonik on 2017-10-18.
 */
public class TmdbConfiguration {

    public void timerConfiguration(Integer numberOfDays) {
        long localDate = LocalDate.of(2000,1,1).toEpochDay();
        Date date = new Date();
        date.setTime(localDate);
        Timer timer = new Timer();
        TimerTask tmdbTimerTask = new TmdbTimerTask();
        Long timeInterval = Long.valueOf(1000 * 3600 * 24 * numberOfDays);
        timer.schedule(tmdbTimerTask, date, timeInterval);
    }



}


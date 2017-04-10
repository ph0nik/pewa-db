package com.pewa.util;

import com.pewa.PewaType;
import com.pewa.anime.Anime;
import com.pewa.anime.Manga;
import com.pewa.config.ConfigFactory;
import com.pewa.movie.Movie;
import com.pewa.music.Album;
import com.pewa.tv.TvShowSummary;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

public class SaveImage {
    /*
    * - wczytać obraz do pamięci
    * - zapisać go we wskazanym miejscu
    * - utworzyc link do pliku i go zwrocic
    * "ani", "man", "boo", "alb", "mov", "tv
    * */
    private static final String imgPath = ConfigFactory.get("dbCache.imgPath");
    private static final Logger log = LogManager.getLogger(SaveImage.class);
    private static PewaType type;
    private static String id;
    private static String posterLink;
    private static StringBuilder logMessage;

    public static String getImage(Anime anime) {
        type = PewaType.ANIME;
        id = String.valueOf(anime.getIdAnilist());
        posterLink = anime.getPoster();
        return getImage(type, id, posterLink);
    }

    public static String getImage(Manga manga) {
        type = PewaType.MANGA;
        id = String.valueOf(manga.getIdAnilist());
        posterLink = manga.getPoster();
        return getImage(type, id, posterLink);
    }

    public static String getImage(Movie movie) {
        type = PewaType.MOVIE;
        id = movie.getImdbID();
        posterLink = movie.getPoster();
        return getImage(type, id, posterLink);
    }

    public static String getImageMed(TvShowSummary tvshow) {
        type = PewaType.TVSERIES;
        id = tvshow.getImdbLink();
        posterLink = tvshow.getPosterMed();
        return getImage(type, id, posterLink);
    }

    public static String getImageOrg(TvShowSummary tvshow) {
        type = PewaType.TVSERIES;
        id = tvshow.getImdbLink();
        posterLink = tvshow.getPosterOrg();
        return getImage(type, id, posterLink);
    }

    public static String getImage(Album album) {
        type = PewaType.MUSIC;
        id = String.valueOf(album.getIdDiscogs());
        posterLink = album.getCover();
        return getImage(type, id, posterLink);
    }


    private static String getImage(PewaType type, String id, String imageUrl) {
        Boolean directoryConfirmation;
        String destinationFile;
        StringBuilder fileName = new StringBuilder(imgPath);
        File file = new File(imgPath);
        directoryConfirmation = file.mkdirs();
        if (directoryConfirmation) {
            logMessage = new StringBuilder("Folder created: [").append(fileName).append("]");
        } else {
            logMessage = new StringBuilder("Folder already exists: [").append(fileName).append("]");
        }
        log.info(logMessage);
        switch (type) {
            case ANIME:
                fileName.append("ani-");
                break;
            case MANGA:
                fileName.append("man-");
                break;
            case BOOK:
                fileName.append("boo-");
                break;
            case MOVIE:
                fileName.append("mov-");
                break;
            case TVSERIES:
                fileName.append("tv-");
                break;
            case MUSIC:
                fileName.append("alb-");
                break;
        }
        if (imageUrl.contains(".jpg")) {
            destinationFile = fileName.append(id).append(".jpg").toString();
        } else if (imageUrl.contains(".gif")) {
            destinationFile = fileName.append(id).append(".gif").toString();
        } else if (imageUrl.contains(".jpeg")) {
            destinationFile = fileName.append(id).append(".jpeg").toString();
        } else {
            destinationFile = fileName.append(id).append(".png").toString();
        }
        try {
            saveImage(imageUrl, destinationFile);
        } catch (IOException e) {
            log.error(e.getMessage(), e.fillInStackTrace());
        }
        return destinationFile;
    }

    private static void saveImage(String imgUrl, String destinationFile) throws IOException {
        File outputFile = new File(destinationFile);
        if (!outputFile.exists()) {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet(imgUrl);
            try (CloseableHttpResponse response = httpClient.execute(httpget)) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    try (FileOutputStream outStream = new FileOutputStream(outputFile)) {
                        entity.writeTo(outStream);
                        logMessage = new StringBuilder("File saved : [").append(destinationFile).append("]");
                        log.info(logMessage);
                    }
                }
            }
        } else {
            logMessage = new StringBuilder("File already exists: [").append(destinationFile).append("]");
            log.info(logMessage);
        }
    }
}

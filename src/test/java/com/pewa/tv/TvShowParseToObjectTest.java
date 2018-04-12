package com.pewa.tv;

import com.pewa.MediaParse;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TvShowParseToObjectTest {
    @Disabled
    @Test
    public void getListOfFoundTvShows() {
        MediaParse<TvShowSummary, Integer> tvShow = new TvShowParser();
        TvShowSummary buffy = tvShow.getItem(427);
        int dl = buffy.getEpisodes().size();
        assertEquals(143, dl);

        System.out.println(buffy.getEpisodes().get(12).getFirstAired());
        /*for(int i = 0; i < dl; i++) {
            System.out.println(i + 1 +" [" + buffy.getEpisodes().get(i).getEpisode() + "] " + buffy.getEpisodes().get(i).getEpTitle() + buffy.getPremiered().getYear());
        }*/

        System.out.println(buffy);
    }
    @Disabled
    @Test
    public void checkWrongImdbId() {
        MediaParse<TvShowSummary, Integer> tvShow = new TvShowParser();
        TvShowSummary wrongImdbId = tvShow.getItem(0);
        System.out.println(wrongImdbId.toString());
    }
}

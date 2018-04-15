package com.pewa.anime;

import com.pewa.MediaParse;
import com.pewa.PewaType;
import com.pewa.common.Results;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


/**
 * Created by phonik on 2017-02-27.
 */
public class AnimeMangaSearchTest {

    private Results results;
    @BeforeEach
    public void setup() {
        results = new Results();
    }

    @Disabled
    @Test
    public void showSearchResults() {
        String query = "akira";
        AnimeMangaSearch amSearch = new AnimeMangaSearch();
        Results wynik = amSearch.aniListSearch(query, PewaType.ANIME);
        wynik.getEncounters().stream().limit(5).forEach(System.out::println);

        query = "berserk";
        wynik = amSearch.aniListSearch(query, PewaType.MANGA);
        wynik.getEncounters().stream().limit(2).forEach(System.out::println);
    }

    @Disabled
    @Test
    public void graphQlTest() {
        AnimeMangaSearch amSearch = new AnimeMangaSearch();
        String query = "ghost in the shell";
        Results singleSearchResults = amSearch.aniListSearchV2(query, PewaType.ANIME);
        System.out.println(singleSearchResults);
    }


    @Disabled
    @Test
    public void getSingleItemAnime() {
        //ExternalMediaResult{url='null', desc='Koukaku Kidoutai (Ghost in the Shell) Movie(eps.1) - 1995', idInt=43, poster='https://cdn.anilist.co/img/dir/anime/reg/43.jpg'}
        MediaParse<Anime, Integer> animeParseToObject = new AnimeParser();
        Anime anime = animeParseToObject.getItem(95821);
        //Anime test = getAnimeItem(43);
        System.out.println(anime);
    }

    @Disabled
    @Test
    public void getSingleItemWithV2() {
        AnimeParser animeParseToObject = new AnimeParser();
        Anime itemV2 = animeParseToObject.getItem(47);
        System.out.println(itemV2);
    }

    @Disabled
    @Test
    public void getSingleItemManga() {
        MediaParse<Manga, Integer> mangaParser = new MangaParser();
        // id = 30664
        Manga manga = mangaParser.getItem(95821);
        System.out.println(manga);
    }
    @Disabled
    @Test
    public void getErrorItem() {
        MediaParse<Anime, Integer> animeParseToObject = new AnimeParser();
        Anime test = animeParseToObject.getItem(654534);
        System.out.println(test.isEmpty());
    }


}

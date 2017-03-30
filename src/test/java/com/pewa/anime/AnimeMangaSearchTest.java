package com.pewa.anime;

import com.pewa.MediaParse;
import com.pewa.PewaType;
import com.pewa.util.SaveImage;
import com.pewa.SingleSearchResult;
import com.pewa.config.ConfigReader;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.print.attribute.standard.Media;
import java.util.Set;


/**
 * Created by phonik on 2017-02-27.
 */
public class AnimeMangaSearchTest {

    @BeforeClass
    public static void initTest() {
        ConfigReader.load();
    }

    @Test
    public void showSearchResults() {

        String query = "ghost in the shell";
        AnimeMangaSearch amSearch = new AnimeMangaSearch();
        Set<SingleSearchResult> wynik = amSearch.aniListSearch(query, "anime");
        wynik.stream().limit(5).forEach(System.out::println);

        query = "berserk";
        wynik = amSearch.aniListSearch(query, "manga");
        wynik.stream().forEach(System.out::println);

    }

    @Test
    public void getSingleItemAnime() {
        //SingleSearchResult{url='null', desc='Koukaku Kidoutai (Ghost in the Shell) Movie(eps.1) - 1995', idInt=43, poster='https://cdn.anilist.co/img/dir/anime/reg/43.jpg'}
        MediaParse<Anime, Integer> animeParseToObject = new AnimeParser();
        Anime anime = animeParseToObject.getItem(43);
        //Anime test = getAnimeItem(43);
        System.out.println(anime);
    }

    @Test
    public void getSingleItemManga() {
        MediaParse<Manga, Integer> mangaParser = new MangaParser();
        // id = 30664
        Manga manga = mangaParser.getItem(30664);
        System.out.println(manga);
    }

    @Test
    public void getErrorItem() {
        MediaParse<Anime, Integer> animeParseToObject = new AnimeParser();
        Anime test = animeParseToObject.getItem(654534);
        System.out.println(test);
    }


}

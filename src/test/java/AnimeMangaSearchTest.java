import com.pewa.SingleSearchResult;
import com.pewa.anime.Anime;
import com.pewa.anime.Manga;
import com.pewa.config.ConfigReader;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.pewa.anime.AnimeMangaSearch.aniListSearchAnime;

import java.util.Set;

import static com.pewa.anime.AnimeParseToObject.getAnimeItem;
import static com.pewa.anime.MangaParseToObject.getMangaItem;
import static org.junit.Assert.assertEquals;

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
        Set<SingleSearchResult> wynik;
        wynik = aniListSearchAnime(query, "anime");
        wynik.stream().limit(5).forEach(System.out::println);

        query = "akira";
        wynik = aniListSearchAnime(query, "manga");
        wynik.stream().limit(5).forEach(System.out::println);

    }

    @Test
    public void getSingleItemAnime() {
        //SingleSearchResult{url='null', desc='Koukaku Kidoutai (Ghost in the Shell) Movie(eps.1) - 1995', idInt=43, poster='https://cdn.anilist.co/img/dir/anime/reg/43.jpg'}
        Anime test = getAnimeItem(43);
        System.out.println(test.getStartDate());

    }
    @Test
    public void getSingleItemManga() {
        // id = 30664
        Manga manga = getMangaItem(30664);
        System.out.println(manga);
    }

    @Test
    public void getErrorItem() {
        Anime test = getAnimeItem(654534);
        System.out.println(test);
    }


}

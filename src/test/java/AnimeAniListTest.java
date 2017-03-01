import com.pewa.SingleSearchResult;
import com.pewa.anime.Anime;
import com.pewa.config.ConfigReader;
import org.jsoup.HttpStatusException;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.pewa.anime.AnimeAniList.aniListSearchAnime;

import com.pewa.anime.AnimeAccessToken;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.util.Set;

import static com.pewa.anime.AnimeParseToObject.getAnimeItem;
import static com.pewa.config.Session.updateSession;
import static org.junit.Assert.assertEquals;

/**
 * Created by phonik on 2017-02-27.
 */
public class AnimeAniListTest {

    @BeforeClass
    public static void initTest() {
        ConfigReader.load();
    }

    @Test
    public void showSearchResults() {

        String query = "ghost in the shell";

        Set<SingleSearchResult> wynik = aniListSearchAnime(query);
        wynik.stream().limit(5).forEach(System.out::println);

    }

    @Test
    public void getSingleItem() {
        //SingleSearchResult{url='null', desc='Koukaku Kidoutai (Ghost in the Shell) Movie(eps.1) - 1995', idInt=43, poster='https://cdn.anilist.co/img/dir/anime/reg/43.jpg'}
        Anime test = getAnimeItem(43);
        System.out.println(test);

    }

    @Test
    public void getErrorItem() {
        Anime test = getAnimeItem(654534);
        System.out.println(test);
    }


}

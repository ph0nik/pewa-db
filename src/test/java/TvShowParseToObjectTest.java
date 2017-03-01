import com.pewa.config.ConfigReader;
import com.pewa.tv.TvShowParseToObject;
import com.pewa.tv.TvShowSummary;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TvShowParseToObjectTest {
    @BeforeClass
    public static void initTest() {
        ConfigReader.load();
    }

    @Test
    public void getListOfFoundTvShows() {
        TvShowSummary buffy = TvShowParseToObject.parseSelectedSummaryTv("tt0118276");
        int dl = buffy.getEpisodes().size();
        assertEquals(143, dl);
        System.out.println(buffy.getPremiered());

        System.out.println(buffy.getEpisodes().get(12).getFirstAired());
        /*for(int i = 0; i < dl; i++) {
            System.out.println(i + 1 +" [" + buffy.getEpisodes().get(i).getEpisode() + "] " + buffy.getEpisodes().get(i).getEpTitle() + buffy.getPremiered().getYear());
        }*/

    }

    @Test(expected = NullPointerException.class)
    public void checkWrongImdbId() {
        TvShowSummary wrongImdbId = TvShowParseToObject.parseSelectedSummaryTv("6");
        System.out.println(wrongImdbId.toString());
    }
}

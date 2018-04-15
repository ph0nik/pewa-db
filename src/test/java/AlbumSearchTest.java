
import com.pewa.common.ExternalMediaResult;
import com.pewa.music.Album;
import com.pewa.music.AlbumParseToObjectMB;
import com.pewa.music.AlbumSearch;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


import java.util.Set;

public class AlbumSearchTest {

    @Disabled
    @Test
    public void xmlParserTest() {
        Set<ExternalMediaResult> wynik = AlbumSearch.searchMusicAlbum("and justice metallica");
        wynik.forEach(System.out::println);
    }

    @Disabled
    @Test
    public void createAlbumObject() {
        // SingleSearchResult{url='ae3695b8-da89-3e22-9842-3c25decd701b', desc='Mr. Bungle "Mr. Bungle" (1991-10-10)'}
        String id = "ae3695b8-da89-3e22-9842-3c25decd701b";
        Album test = AlbumParseToObjectMB.parseAlbum(id);
        System.out.println(test.toString());

    }
}

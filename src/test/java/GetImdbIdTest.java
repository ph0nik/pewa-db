import com.pewa.SingleSearchResult;
import com.pewa.config.ConfigReader;
import com.pewa.imdb.GetImdbId;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

public class GetImdbIdTest {

    @BeforeClass
    public static void initTest() {
        ConfigReader.load();
    }

    @Test
    public void tryMapOfItemsMovie() {
        Set<SingleSearchResult> searchResultSet = new TreeSet<>();
        Set<SingleSearchResult> result;
        result = GetImdbId.mapOfItems("versus", "movie", searchResultSet);
        assertNotNull(result);
        result.forEach(System.out::println);
    }

    @Test
    public void tryMapOfItemsTv() {
        Set<SingleSearchResult> searchResultSet2 = new TreeSet<>();
        Set<SingleSearchResult> result;
        result = GetImdbId.mapOfItems("buffy", "tv", searchResultSet2);
        assertNotNull(result);
    }
}

import com.pewa.InitAllTables;
import com.pewa.config.ConfigReader;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by phonik on 2017-03-22.
 */
public class InitAllTablesTest {

    @BeforeClass
    public static void initTest() {

        ConfigReader.load();
    }

    @Test
    public void createTables() {
        InitAllTables tables = new InitAllTables();
        tables.initTables();

    }
}

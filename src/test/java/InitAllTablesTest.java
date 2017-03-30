import com.pewa.InitAllTables;
import com.pewa.config.ConfigReader;
import org.junit.BeforeClass;
import org.junit.Test;

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

    @Test
    public void dropAll() {
        InitAllTables tables = new InitAllTables();
        tables.dropTables();
    }
}

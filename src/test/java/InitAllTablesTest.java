import com.pewa.InitAllTables;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


public class InitAllTablesTest {

    @Disabled
    @Test
    public void dropAll() {
        InitAllTables tables = new InitAllTables();
        tables.dropTables();
    }

    @Disabled
    @Test
    public void createTables() {
        InitAllTables tables = new InitAllTables();
        tables.initTables();

    }


}

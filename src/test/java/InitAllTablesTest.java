import com.pewa.InitAllTables;
import com.pewa.PewaType;
import com.pewa.common.Results;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


public class InitAllTablesTest {

    private InitAllTables initAllTables;
    private Results results;

    @BeforeEach
    public void setup() {
        initAllTables = new InitAllTables(PewaType.STATUS);
        results = new Results();
    }

    @Disabled
    @Test
    public void dropAll() {
        InitAllTables tables = new InitAllTables(PewaType.STATUS);
        tables.dropTables();
    }

    @Disabled
    @Test
    public void createTables() {
        InitAllTables tables = new InitAllTables(PewaType.STATUS);
        tables.initTables();

    }

    @Disabled
    @Test
    public void deleteperson() {
        results = initAllTables.cleanAll(results);
        System.out.println(results);
    }

    @Disabled
    @Test
    public void images() {
        initAllTables.checkForUnconnectedFiles();
    }

}

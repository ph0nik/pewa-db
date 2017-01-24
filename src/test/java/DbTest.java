import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

/**
 * Created by phonik on 2017-01-20.
 */
public class DbTest {

    @BeforeClass
    public static void setUp() {
        assertTrue("ini file error", ConfigReader.load());
    }

    @Test
    public void creatingTableIfDoesntExist() throws Exception {
        DatabaseInit.createDb();
    }

    @Test
    public void loginWithWrongUserId() throws Exception {
        DatabaseInit.createTable();
    }


}

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import javax.xml.crypto.Data;

/**
 * Created by phonik on 2017-01-20.
 */
public class DbTest {

    @BeforeClass
    public static void setUp() {
        ConfigReader.load();
    }

    @Test
    public void creatingTableIfDoesntExist() throws Exception {
        DatabaseInit.createDb();
    }

//    @Test
    public void grantPrivileges() throws Exception {
        DatabaseInit.grantPrivs();
    }

    @Test
    public void createGenreTable() throws Exception {
        DatabaseInit.createGenreTable();
    }

    @Test
    public void createPersonTable() throws Exception {
        DatabaseInit.createPersonTable();
    }

    @Test
    public void createCountryTable() throws Exception {
        DatabaseInit.createCountryTable();
    }
}

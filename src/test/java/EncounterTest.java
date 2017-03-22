import com.pewa.PewaType;
import com.pewa.controller.Encounter;
import org.junit.Test;

/**
 * Created by phonik on 2017-03-15.
 */
public class EncounterTest {


    @Test
    public void sprawdzanieModelu() {
        Encounter test = new Encounter();
        test.setType(PewaType.MOVIE);

    }
}

import com.pewa.PewaType;
import com.pewa.controller.Encounter;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * Created by phonik on 2017-03-15.
 */
public class EncounterTest {

    @Disabled
    @Test
    public void sprawdzanieModelu() {
        Encounter test = new Encounter();
        test.setType(PewaType.MOVIE);

    }
}

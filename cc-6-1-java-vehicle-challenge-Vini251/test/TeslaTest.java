import model.Tesla;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TeslaTest {
    Tesla tesla;

    /**
     * Sets up a Tesla object for later use
     *
     * Result: Successfully creates a Tesla object
     */
    @Before
    public void setup() {
        tesla = new Tesla(4,"Model Y", "Blue");
    }

    /**
     * Checks that a successfully created Tesla object has the correct information
     *
     * Result: 4 successful asserts indicating that the autonomy level, make, model, and color
     */
    @Test
    public void constructTesla(){
        assertEquals(4, tesla.getAutonomyLevel());
        assertEquals("Tesla", tesla.getMake());
        assertEquals("Model Y", tesla.getModel());
        assertEquals("Blue", tesla.getColor());
    }

    /**
     * Checks that a successfully created Tesla object has the correct information
     *
     * Result: 1 successful assert indicating that a Tesla object converted to a String contains the correct information
     */
    @Test
    public void checkToString(){
        String expected = "\n=== Tesla Vehicle ===\n" +
                "Make: Tesla\n" +
                "Model: Model Y\n" +
                "Color: Blue\n" +
                "Autonomy Level: 4\n" +
                "Network: Autopilot\n" +
                "Final Direction: N\n";
        assertEquals(expected, tesla.toString());
    }
}

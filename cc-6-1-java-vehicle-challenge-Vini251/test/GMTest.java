import model.GM;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GMTest {
    GM gm;

    /**
     * Sets up a GM object for later use
     *
     * Result: Successfully creates a GM pod
     */
    @Before
    public void setup() {
        gm = new GM(1, "Buick LaCrosse", "Black", "pod");
    }

    /**
     * Checks that the vehicle is one of three types: pod, car, or robotaxi
     *
     * Result: 3 successful asserts indicating that "pod", "car", and "robotaxi" are acceptable inputs
     */
    @Test
    public void validVehicleTypeValidVehicle(){
        GM car = new GM(1, "Buick LaCrosse", "Black", "car");
        GM robotaxi = new GM(1, "Buick LaCrosse", "Black", "robotaxi");
        assertTrue(gm.isValidVehicleType());
        assertTrue(car.isValidVehicleType());
        assertTrue(robotaxi.isValidVehicleType());
    }

    /**
     * Checks that the vehicle is one of three types: pod, car, or robotaxi
     *
     * Result: 1 successful assert indicating that "Waymo" is not an acceptable input
     */
    @Test
    public void validVehicleTypeInvalidVehicle(){
        GM invalidCar = new GM(1, "Buick LaCrosse", "Black", "Waymo");
        assertFalse(invalidCar.isValidVehicleType());
    }

    /**
     * Checks that a successfully created GM object has the correct information
     *
     * Result: 1 successful assert indicating that a GM object converted to a String contains the correct information
     */
    @Test
    public void toStringCorrectOutput(){
        String expected = "\n=== GM Vehicle ===\n" +
                "Make: GM\n" +
                "Model: Buick LaCrosse\n" +
                "Color: Black\n" +
                "Autonomy Level: 1\n" +
                "Vehicle Type: pod\n" +
                "Is Valid Type: true\n" +
                "Final Direction: N\n";
        assertEquals(expected, gm.toString());
    }
}

import model.Toyota;
import org.junit.Before;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class ToyotaTest {
    Toyota toyota;

    /**
     * Sets up a Toyota object for later use
     *
     * Result: Successfully creates a Toyota object
     */
    @Before
    public void setup() {
        toyota = new Toyota(2, "Corolla", "White");
    }

    /**
     * Checks that a successfully created Toyota object has the correct information
     *
     * Result: 4 successful asserts indicating that the autonomy level, make, model, and color
     */
    @Test
    public void createToyotaCorrectValues(){
        assertEquals(2, toyota.getAutonomyLevel());
        assertEquals("Toyota", toyota.getMake());
        assertEquals("Corolla", toyota.getModel());
        assertEquals("White", toyota.getColor());
    }

    /**
     * Checks that the Lyft status is correctly set when a Toyota object is created
     *
     * Result: 1 assert indicating that Lyft is true during the weekends and false during the weekdays
     */
    @Test
    public void correctLyftSetting(){
        DayOfWeek today = LocalDate.now().getDayOfWeek();
        if(today == DayOfWeek.SATURDAY || today == DayOfWeek.SUNDAY){
            assertTrue(toyota.isLyftVehicle());
        } else{
            assertFalse(toyota.isLyftVehicle());
        }
    }

    /**
     * Checks that a successfully created Toyota object has the correct information
     *
     * Result: 1 successful assert indicating that a Toyota object converted to a String contains the correct information
     */
    @Test
    public void checkToString(){
        DayOfWeek today = LocalDate.now().getDayOfWeek();
        if(today == DayOfWeek.SATURDAY || today == DayOfWeek.SUNDAY){
        String expectedWeekend = "\n=== Toyota Vehicle ===\n" +
                "Make: Toyota\n" +
                "Model: Corolla\n" +
                "Color: White\n" +
                "Autonomy Level: 2\n" +
                "Is Lyft Vehicle: true\n" +
                "Final Direction: N\n";
        assertEquals(expectedWeekend, toyota.toString());
        } else{
            String expectedWeekday = "\n=== Toyota Vehicle ===\n" +
                    "Make: Toyota\n" +
                    "Model: Corolla\n" +
                    "Color: White\n" +
                    "Autonomy Level: 2\n" +
                    "Is Lyft Vehicle: false\n" +
                    "Final Direction: N\n";
            assertEquals(expectedWeekday,  toyota.toString());
        }
    }
}

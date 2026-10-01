import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayInputStream;


public class myMainTest {
    myMain myMain;

    /**
     * Sets up a myMain object for later use
     *
     * Result: Successfully creates a myMain object
     */
    @Before
    public void setup() {
        myMain = new myMain();
    }

    /**
     * Provides valid inputs to create a vehicle
     *
     * Result: Should print out the information for a Tesla vehicle with the correct
     */
    @Test
    public void myMainTestValidInputs() {
        String simulatedUserInput = "1" +
                "\n4" +
                "\nModel Y" +
                "\nBlue" +
                "\nR R R";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        myMain.main(null);
    }

    /**
     * Provides a vehicle type option that is below the acceptable values (1 - 3)
     *
     * Result: Should print out an Invalid vehicle type error
     */
    @Test
    public void myMainTestVehicleTypeNumberTooLow(){
        String simulatedUserInput = "0" +
                "\n1" +
                "\nModel Y" +
                "\nBlue" +
                "\nR R R";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        myMain.main(null);
    }

    /**
     * Provides a vehicle type option that is above the acceptable values (1 - 3)
     *
     * Result: Should print out an Invalid vehicle type error
     */
    @Test
    public void myMainTestVehicleTypeNumberTooHigh(){
        String simulatedUserInput = "4" +
                "\n1" +
                "\nModel Y" +
                "\nBlue" +
                "\nR R R";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        myMain.main(null);
    }

    /**
     * Provides a vehicle type option that is not a number
     *
     * Result: Should print out an Invalid input format error
     */
    @Test
    public void myMainTestVehicleTypeNonNumber(){
        String simulatedUserInput = "e" +
                "\n1" +
                "\nModel Y" +
                "\nBlue" +
                "\nR R R";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        myMain.main(null);
    }

    /**
     * Provides an autonomy level that is below the acceptable values (1 - 5)
     *
     * Result: Should print out an Invalid autonomy level error
     */
    @Test
    public void myMainTestWrongAutonomyLevelTooLow(){
        String simulatedUserInput = "1" +
                "\n0" +
                "\nModel Y" +
                "\nBlue" +
                "\nR R R";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        myMain.main(null);
    }

    /**
     * Provides an autonomy level that is above the acceptable values (1 - 5)
     *
     * Result: Should print out an Invalid autonomy level error
     */
    @Test
    public void myMainTestWrongAutonomyLevelTooHigh(){
        String simulatedUserInput = "1" +
                "\n6" +
                "\nModel Y" +
                "\nBlue" +
                "\nR R R";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        myMain.main(null);
    }

    /**
     * Provides an autonomy level that is not a number
     *
     * Result: Should print out an Invalid input format error
     */
    @Test
    public void myMainTestWrongAutonomyLevelNonNumber(){
        String simulatedUserInput = "1" +
                "\ne" +
                "\nModel Y" +
                "\nBlue" +
                "\nR R R";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        myMain.main(null);
    }

    /**
     * Includes an invalid turn in the turn list
     *
     * Result: Should print out an Invalid turn error
     */
    @Test
    public void myMainTestWrongTurnInput(){
        String simulatedUserInput = "1" +
                "\n2" +
                "\nModel Y" +
                "\nBlue" +
                "\nR R R G L R L L";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        myMain.main(null);
    }
}

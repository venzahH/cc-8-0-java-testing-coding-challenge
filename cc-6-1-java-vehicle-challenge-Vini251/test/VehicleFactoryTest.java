import enums.VehicleType;
import model.AutonomousVehicle;
import org.junit.Before;
import org.junit.Test;
import service.VehicleFactory;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Scanner;

public class VehicleFactoryTest {
    VehicleFactory factory;

    /**
     * Sets up a VehicleFactory before every test
     *
     * Result: Successfully creates a VehicleFactory object
     */
    @Before
    public void setup() {
        factory = new VehicleFactory();
    }

    /**
     * Tests the createVehicle to ensure it can successfully create a Tesla when provided valid inputs
     *
     * Result: 1 successful assert indicating that the information for the Tesla matches the information provided
     */
    @Test
    public void createProperTesla() {
        Scanner scanner = new Scanner(System.in);
        AutonomousVehicle vehicle = factory.createVehicle(VehicleType.TESLA, 4, "Model Y", "Blue", scanner);
        String expected = "\n=== Tesla Vehicle ===\n" +
                "Make: Tesla\n" +
                "Model: Model Y\n" +
                "Color: Blue\n" +
                "Autonomy Level: 4\n" +
                "Network: Autopilot\n" +
                "Final Direction: N\n";
        assertEquals(expected, vehicle.toString());
    }

    /**
     * Tests the createVehicle to ensure it can successfully create a Toyota when provided valid inputs
     *
     * Result: 1 successful assert indicating that the information for the Toyota matches the information provided
     */
    @Test
    public void createProperToyota() {
        Scanner scanner = new Scanner(System.in);
        AutonomousVehicle vehicle = factory.createVehicle(VehicleType.TOYOTA, 2, "Corolla", "White", scanner);
        DayOfWeek today = LocalDate.now().getDayOfWeek();
        if(today == DayOfWeek.SATURDAY || today == DayOfWeek.SUNDAY){
            String expectedWeekend = "\n=== Toyota Vehicle ===\n" +
                    "Make: Toyota\n" +
                    "Model: Corolla\n" +
                    "Color: White\n" +
                    "Autonomy Level: 2\n" +
                    "Is Lyft Vehicle: true\n" +
                    "Final Direction: N\n";
            assertEquals(expectedWeekend, vehicle.toString());
        } else{
            String expectedWeekday = "\n=== Toyota Vehicle ===\n" +
                    "Make: Toyota\n" +
                    "Model: Corolla\n" +
                    "Color: White\n" +
                    "Autonomy Level: 2\n" +
                    "Is Lyft Vehicle: false\n" +
                    "Final Direction: N\n";
            assertEquals(expectedWeekday,  vehicle.toString());
        }
    }

    /**
     * Tests the createVehicle to ensure it can successfully create a GM pod when provided valid inputs
     *
     * Result: 1 successful assert indicating that the information for the pod matches the information provided
     */
    @Test
    public void createProperGMPod() {
        String simulatedUserInput = "pod";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        Scanner scanner = new Scanner(System.in);
        AutonomousVehicle vehicle = factory.createVehicle(VehicleType.GM, 1, "Buick LaCrosse", "Black", scanner);
        String expected = "\n=== GM Vehicle ===\n" +
                "Make: GM\n" +
                "Model: Buick LaCrosse\n" +
                "Color: Black\n" +
                "Autonomy Level: 1\n" +
                "Vehicle Type: pod\n" +
                "Is Valid Type: true\n" +
                "Final Direction: N\n";
        assertEquals(expected, vehicle.toString());
    }

    /**
     * Tests the createVehicle to ensure it can successfully create a GM robotaxi when provided valid inputs
     *
     * Result: 1 successful assert indicating that the information for the robotaxi matches the information provided
     */
    @Test
    public void createProperGMRobotaxi() {
        String simulatedUserInput = "robotaxi";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        Scanner scanner = new Scanner(System.in);
        AutonomousVehicle vehicle = factory.createVehicle(VehicleType.GM, 1, "Buick LaCrosse", "Black", scanner);
        String expected = "\n=== GM Vehicle ===\n" +
                "Make: GM\n" +
                "Model: Buick LaCrosse\n" +
                "Color: Black\n" +
                "Autonomy Level: 1\n" +
                "Vehicle Type: robotaxi\n" +
                "Is Valid Type: true\n" +
                "Final Direction: N\n";
        assertEquals(expected, vehicle.toString());
    }

    /**
     * Tests the createVehicle to ensure it can successfully create a GM car when provided valid inputs
     *
     * Result: 1 successful assert indicating that the information for the car matches the information provided
     */
    @Test
    public void createProperGMCar() {
        String simulatedUserInput = "car";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        Scanner scanner = new Scanner(System.in);
        AutonomousVehicle vehicle = factory.createVehicle(VehicleType.GM, 1, "Buick LaCrosse", "Black", scanner);
        String expected = "\n=== GM Vehicle ===\n" +
                "Make: GM\n" +
                "Model: Buick LaCrosse\n" +
                "Color: Black\n" +
                "Autonomy Level: 1\n" +
                "Vehicle Type: car\n" +
                "Is Valid Type: true\n" +
                "Final Direction: N\n";
        assertEquals(expected, vehicle.toString());
    }

    /**
     * Tests the createVehicle to ensure it throws an IllegalArgumentException when provided an invalid GM vehicle type
     *
     * Result: 1 successful assert indicating IllegalArgumentException is thrown when provided an invalid vehicle type
     */
    @Test
    public void createProperVehicleInvalidGMTType(){
        String simulatedUserInput = "waymo";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        Scanner scanner = new Scanner(System.in);
        assertThrows(IllegalArgumentException.class, () -> {factory.createVehicle(VehicleType.GM, 1, "Buick LaCrosse", "Black", scanner);});
    }
}

package model;

import java.time.LocalDate;
import java.time.DayOfWeek;

/**
 * Toyota autonomous vehicle with Lyft service capability.
 */
public class Toyota extends AutonomousVehicle {
    private final boolean isLyft;
    
    /**
     * Constructs a Toyota vehicle.
     * @param autonomyLevel the autonomy level (1-5)
     * @param model the Toyota model
     * @param color the vehicle color
     */
    public Toyota(int autonomyLevel, String model, String color) {
        super(autonomyLevel, "Toyota", model, color);
        this.isLyft = isLyftVehicle();
    }
    
    /**
     * Determines if this vehicle is a Lyft vehicle based on current day.
     * @return true if today is Saturday or Sunday, false otherwise
     */
    public boolean isLyftVehicle() {
        DayOfWeek today = LocalDate.now().getDayOfWeek();
        return today == DayOfWeek.SATURDAY || today == DayOfWeek.SUNDAY;
    }
    
    /**
     * Returns a string representation of the Toyota vehicle.
     * @return formatted string with vehicle details
     */
    @Override
    public String toString() {
        return "\n=== Toyota Vehicle ===\n" +
               "Make: " + make + "\n" +
               "Model: " + model + "\n" +
               "Color: " + color + "\n" +
               "Autonomy Level: " + autonomyLevel + "\n" +
               "Is Lyft Vehicle: " + isLyft + "\n" +
               "Final Direction: " + direction.getSymbol() + "\n";
    }
}
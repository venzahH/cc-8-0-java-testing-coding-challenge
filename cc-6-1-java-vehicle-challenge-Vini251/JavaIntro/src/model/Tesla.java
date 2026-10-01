package model;

/**
 * Tesla autonomous vehicle with Autopilot network.
 */
public class Tesla extends AutonomousVehicle {
    private final String network;
    
    /**
     * Constructs a Tesla vehicle.
     * @param autonomyLevel the autonomy level (1-5)
     * @param model the Tesla model
     * @param color the vehicle color
     */
    public Tesla(int autonomyLevel, String model, String color) {
        super(autonomyLevel, "Tesla", model, color);
        this.network = "Autopilot";
    }
    
    /**
     * Returns a string representation of the Tesla vehicle.
     * @return formatted string with vehicle details
     */
    @Override
    public String toString() {
        return "\n=== Tesla Vehicle ===\n" +
               "Make: " + make + "\n" +
               "Model: " + model + "\n" +
               "Color: " + color + "\n" +
               "Autonomy Level: " + autonomyLevel + "\n" +
               "Network: " + network + "\n" +
               "Final Direction: " + direction.getSymbol() + "\n";
    }
}
package model;

/**
 * GM autonomous vehicle with configurable vehicle type.
 */
public class GM extends AutonomousVehicle {
    private final String vehicleType;
    
    /**
     * Constructs a GM vehicle.
     * @param autonomyLevel the autonomy level (1-5)
     * @param model the GM model
     * @param color the vehicle color
     * @param vehicleType the type of GM vehicle (pod, robotaxi, or car)
     */
    public GM(int autonomyLevel, String model, String color, String vehicleType) {
        super(autonomyLevel, "GM", model, color);
        this.vehicleType = vehicleType;
    }
    
    /**
     * Validates if the vehicle type is supported.
     * @return true if vehicle type is pod, robotaxi, or car
     */
    public boolean isValidVehicleType() {
        return vehicleType.equals("pod") || vehicleType.equals("robotaxi") || vehicleType.equals("car");
    }
    
    /**
     * Returns a string representation of the GM vehicle.
     * @return formatted string with vehicle details
     */
    @Override
    public String toString() {
        return "\n=== GM Vehicle ===\n" +
               "Make: " + make + "\n" +
               "Model: " + model + "\n" +
               "Color: " + color + "\n" +
               "Autonomy Level: " + autonomyLevel + "\n" +
               "Vehicle Type: " + vehicleType + "\n" +
               "Is Valid Type: " + isValidVehicleType() + "\n" +
               "Final Direction: " + direction.getSymbol() + "\n";
    }
}
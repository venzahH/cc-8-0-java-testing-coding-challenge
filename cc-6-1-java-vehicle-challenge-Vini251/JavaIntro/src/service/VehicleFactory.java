package service;

import java.util.Scanner;
import model.*;
import enums.VehicleType;

/**
 * Factory class for creating autonomous vehicles.
 */
public class VehicleFactory {
    
    /**
     * Creates an autonomous vehicle based on the specified type.
     * @param type the type of vehicle to create
     * @param autonomyLevel the autonomy level (1-5)
     * @param model the vehicle model
     * @param color the vehicle color
     * @param scanner scanner for additional input if needed
     * @return the created autonomous vehicle
     * @throws IllegalArgumentException if vehicle type is unsupported or GM vehicle type is invalid
     */
    public static AutonomousVehicle createVehicle(VehicleType type, int autonomyLevel, String model, String color, Scanner scanner) {
        switch (type) {
            case TESLA:
                return new Tesla(autonomyLevel, model, color);
            case TOYOTA:
                return new Toyota(autonomyLevel, model, color);
            case GM:
                System.out.print("Enter vehicle type (pod/robotaxi/car): ");
                String vehicleType = scanner.nextLine();
                GM gmVehicle = new GM(autonomyLevel, model, color, vehicleType);
                if (!gmVehicle.isValidVehicleType()) {
                    throw new IllegalArgumentException("Invalid GM vehicle type. Must be pod, robotaxi, or car.");
                }
                return gmVehicle;
            default:
                throw new IllegalArgumentException("Unsupported vehicle type: " + type);
        }
    }
}
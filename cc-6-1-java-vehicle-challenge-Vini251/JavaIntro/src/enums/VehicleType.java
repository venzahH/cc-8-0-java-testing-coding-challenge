package enums;

/**
 * Represents different vehicle manufacturer types.
 */
public enum VehicleType {
    TESLA(1), TOYOTA(2), GM(3);
    
    private final int value;
    
    /**
     * Constructs a VehicleType with its numeric value.
     * @param value the numeric identifier for the vehicle type
     */
    VehicleType(int value) {
        this.value = value;
    }
    
    /**
     * Creates a VehicleType from an integer value.
     * @param value the numeric value representing the vehicle type
     * @return the corresponding VehicleType
     * @throws IllegalArgumentException if the value is invalid
     */
    public static VehicleType fromInt(int value) {
        for (VehicleType type : values()) {
            if (type.value == value) return type;
        }
        throw new IllegalArgumentException("Invalid vehicle type: " + value);
    }
}
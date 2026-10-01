package model;

import enums.Direction;
import enums.TurnDirection;

/**
 * Base class for autonomous vehicles with navigation capabilities.
 */
public class AutonomousVehicle {
    protected int autonomyLevel;
    protected String make;
    protected String model;
    protected String color;
    protected Direction direction;
    
    /**
     * Constructs an AutonomousVehicle with specified properties.
     * @param autonomyLevel the autonomy level (1-5)
     * @param make the vehicle manufacturer
     * @param model the vehicle model
     * @param color the vehicle color
     */
    public AutonomousVehicle(int autonomyLevel, String make, String model, String color) {
        this.autonomyLevel = autonomyLevel;
        this.make = make;
        this.model = model;
        this.color = color;
        this.direction = Direction.NORTH;
    }
    
    /**
     * Calculates the final direction after a series of turns.
     * @param turns array of turn directions to execute
     * @return the final direction after all turns
     */
    public Direction finalDirection(TurnDirection[] turns) {
        Direction current = Direction.NORTH;
        
        for (TurnDirection turn : turns) {
            current = (turn == TurnDirection.RIGHT) ? current.turnRight() : current.turnLeft();
        }
        
        this.direction = current;
        return this.direction;
    }
    
    /**
     * Gets the current direction.
     * @return the current direction
     */
    public Direction getDirection() { return direction; }
    
    /**
     * Gets the autonomy level.
     * @return the autonomy level
     */
    public int getAutonomyLevel() { return autonomyLevel; }
    
    /**
     * Gets the vehicle make.
     * @return the vehicle make
     */
    public String getMake() { return make; }
    
    /**
     * Gets the vehicle model.
     * @return the vehicle model
     */
    public String getModel() { return model; }
    
    /**
     * Gets the vehicle color.
     * @return the vehicle color
     */
    public String getColor() { return color; }
}
package enums;

/**
 * Represents the four cardinal directions with turn functionality.
 */
public enum Direction {
    NORTH("N"), EAST("E"), SOUTH("S"), WEST("W");
    
    private final String symbol;
    
    /**
     * Constructs a Direction with its symbol.
     * @param symbol the single character symbol for the direction
     */
    Direction(String symbol) {
        this.symbol = symbol;
    }
    
    /**
     * Gets the symbol representation of the direction.
     * @return the direction symbol
     */
    public String getSymbol() { return symbol; }
    
    /**
     * Returns the direction after turning right.
     * @return the new direction after a right turn
     */
    public Direction turnRight() {
        return values()[(ordinal() + 1) % 4];
    }
    
    /**
     * Returns the direction after turning left.
     * @return the new direction after a left turn
     */
    public Direction turnLeft() {
        return values()[(ordinal() + 3) % 4];
    }
}
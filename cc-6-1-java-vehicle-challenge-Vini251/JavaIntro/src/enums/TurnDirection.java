package enums;

/**
 * Represents turn directions for vehicle navigation.
 */
public enum TurnDirection {
    LEFT("L"), RIGHT("R");
    
    private final String symbol;
    
    /**
     * Constructs a TurnDirection with its symbol.
     * @param symbol the single character symbol for the turn direction
     */
    TurnDirection(String symbol) {
        this.symbol = symbol;
    }
    
    /**
     * Creates a TurnDirection from a string symbol.
     * @param symbol the string representation of the turn direction
     * @return the corresponding TurnDirection
     * @throws IllegalArgumentException if the symbol is invalid
     */
    public static TurnDirection fromString(String symbol) {
        for (TurnDirection turn : values()) {
            if (turn.symbol.equals(symbol)) return turn;
        }
        throw new IllegalArgumentException("Invalid turn: " + symbol);
    }
}
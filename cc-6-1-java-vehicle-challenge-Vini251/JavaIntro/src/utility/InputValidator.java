package utility;

import enums.TurnDirection;

/**
 * Utility class for validating user input.
 */
public class InputValidator {
    
    /**
     * Validates that the autonomy level is within valid range.
     * @param level the autonomy level to validate
     * @throws IllegalArgumentException if level is not between 1 and 5
     */
    public static void validateAutonomyLevel(int level) {
        if (level < 1 || level > 5) {
            throw new IllegalArgumentException("Invalid autonomy level. Must be between 1 and 5.");
        }
    }
    
    /**
     * Validates that a string input is not empty.
     * @param input the string to validate
     * @param fieldName the name of the field for error messages
     * @throws IllegalArgumentException if input is empty or whitespace only
     */
    public static void validateNonEmpty(String input, String fieldName) {
        if (input.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty.");
        }
    }
    
    /**
     * Validates and converts turn strings to TurnDirection array.
     * @param turnStrings array of turn direction strings
     * @return array of validated TurnDirection objects
     * @throws IllegalArgumentException if any turn string is invalid
     */
    public static TurnDirection[] validateTurns(String[] turnStrings) {
        if (turnStrings.length == 1 && turnStrings[0].trim().isEmpty()) {
            return new TurnDirection[0];
        }
        TurnDirection[] turns = new TurnDirection[turnStrings.length];
        for (int i = 0; i < turnStrings.length; i++) {
            turns[i] = TurnDirection.fromString(turnStrings[i]);
        }
        return turns;
    }
}
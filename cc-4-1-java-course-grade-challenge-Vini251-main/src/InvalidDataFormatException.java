/**
 * Custom exception for invalid data format in TSV file.
 */
public class InvalidDataFormatException extends Exception {
    public InvalidDataFormatException(String message) {
        super(message);
    }
}
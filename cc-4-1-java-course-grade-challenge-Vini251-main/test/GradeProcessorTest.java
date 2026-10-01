import static org.junit.jupiter.api.Assertions.*;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;

import java.util.ArrayList;

public class GradeProcessorTest {
    GradeProcessor processor;

    /**
     * Creates a new GradeProcessor object before every test
     *
     * Results: Creates a new GradeProcessor object
     */
    @Before
    public void setup() {
        processor = new GradeProcessor();
    }

    /**
     * Checks that main runs successfully when provided valid inputs
     *
     * Results: report.txt with properly updated values
     */
    @Test
    public void testMain(){
        String simulatedUserInput = "src/TestCases.tsv";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        processor.main(null);
    }

    /**
     * Checks a InvalidDataFormatException error is thrown when provided an empty file name (full of whitespace)
     *
     * Results: 1 successful assert indicating that InvalidDataFormatException was thrown as expected
     */
    @Test
    public void getFileFromUserValidFileFileNameWithOnlySpace(){
        String simulatedUserInput = "    ";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        assertThrows(InvalidDataFormatException.class, () -> {processor.getFilenameFromUser();});
    }

    /**
     * Checks a InvalidDataFormatException error is thrown when provided an empty file name (new line)
     *
     * Results: 1 successful assert indicating that InvalidDataFormatException was thrown as expected
     */
    @Test
    public void getFileFromUserEmptyFileName(){
        String simulatedUserInput = "\n";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        assertThrows(InvalidDataFormatException.class, () -> {processor.getFilenameFromUser();});
    }

    /**
     * Checks a FileNotFoundException error is thrown when a file name that does not exist is provided
     *
     * Results: 1 successful assert indicating that FileNotFoundException was thrown as expected
     */
    @Test
    public void readStudentDataFileDoesNotExist(){
        assertThrows(FileNotFoundException.class, () -> processor.readStudentData("test/thisFileDoesNotExist.tsv"));
    }

    /**
     * Checks an InvalidDataFormatException error is thrown when a existing file is provided but the file is empty
     *
     * Results: 1 successful assert indicating that InvalidDataFormatException was thrown as expected
     */
    @Test
    public void readStudentDataEmptyFile(){
        assertThrows(InvalidDataFormatException.class, () -> processor.readStudentData("test/EmptyFile.tsv"));
    }

    /**
     * Checks an InvalidDataFormatException error is thrown when a existing file is provided but the file no valid records (All the records are missing something)
     *
     * Results: 1 successful assert indicating that InvalidDataFormatException was thrown as expected
     */
    @Test
    public void readStudentDataAllWrongFile(){
        assertThrows(InvalidDataFormatException.class, () -> processor.readStudentData("test/AllWrong.tsv"));
    }

    /**
     * Checks that blank lines are ignored when reading the file
     *
     * Results: 4 successful assert indicating that 4 Student objects exist as expected
     */
    @Test
    public void readStudentDataBlankLines(){
        ArrayList<String> expectedNames = new ArrayList<>();
        expectedNames.add("Perfect");
        expectedNames.add("Failing");
        expectedNames.add("Low");
        expectedNames.add("Inconsistent");
        try{
            ArrayList<Student>  actualNames = processor.readStudentData("test/EmptyLines.tsv");
            for(int i = 0; i < expectedNames.size(); i++){
                assertEquals(expectedNames.get(i), actualNames.get(i).lastName);
            }
        } catch(FileNotFoundException | InvalidDataFormatException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Checks that lines (to represent records) of student data are not parsed if information is missing
     *
     * Results: 1 successful assert indicating that InvalidDataFormatException is thrown when information is missing
     */
    @Test
    public void parseStudentRecordInvalidData(){
        assertThrows(InvalidDataFormatException.class, () -> processor.parseStudentRecord("Venzah   Hamilton"));
    }

    /**
     * Checks that lines (to represent records) of student data are not parsed if the scores are not valid
     *
     * Results: 1 successful assert indicating that InvalidScoreException is thrown when a negative number is added as a score
     * Note: Method comments state @throws InvalidScoreException if scores are invalid but InvalidDataFormatException is thrown
     */
    @Test
    public void parseStudentRecordInvalidScoreNegative(){
        assertThrows(InvalidScoreException.class, () -> processor.parseStudentRecord("Venzah    Hamilton    100 -20 70"));
    }

    /**
     * Checks that lines (to represent records) of student data are not parsed if the scores are not valid
     *
     * Results: 1 successful assert indicating that InvalidScoreException is thrown when the score exceeds 100
     * Note: Method comments state @throws InvalidScoreException if scores are invalid but InvalidDataFormatException is thrown
     */
    @Test
    public void parseStudentRecordInvalidScoreTooHigh(){
        assertThrows(InvalidScoreException.class, () -> processor.parseStudentRecord("Venzah    Hamilton    80  190 70"));
    }

    /**
     * Checks that an empty string is not accepted as a name
     *
     * Result: 1 successful assert indicating that InvalidDataFormatException is thrown when an empty String is provided
     */
    @Test
    public void validateNameEmptyName(){
        assertThrows(InvalidDataFormatException.class, () -> processor.validateName("", "Last Name"));
    }

    /**
     * Checks that an empty string is not accepted as a name
     *
     * Result: 1 successful assert indicating that InvalidDataFormatException is thrown when a string of just whitespace is provided
     */
    @Test
    public void validateNameEmptyNameWhitespace(){
        assertThrows(InvalidDataFormatException.class, () -> processor.validateName("      ", "Last Name"));
    }

    /**
     * Checks that an empty string is not accepted as a name
     *
     * Result: 1 successful assert indicating that InvalidDataFormatException is thrown when a null string is provided
     */
    @Test
    public void validateNameNullName(){
        assertThrows(InvalidDataFormatException.class, () -> processor.validateName(null, "Last Name"));
    }

    /**
     * Checks that only numbers are accepted as scores
     *
     * Result: 1 successful assert indicating that InvalidScoreException when a non-number is provided
     */
    @Test
    public void validateScoreNotNumber(){
        assertThrows(InvalidScoreException.class, () -> processor.validateScore("twenty", "Final"));
    }

    /**
     * Checks that only numbers are accepted as scores
     *
     * Result: 1 successful assert indicating that InvalidScoreException when a negative number is provided
     */
    @Test
    public void validateScoreNegative(){
        assertThrows(InvalidScoreException.class, () -> processor.validateScore("-1", "Final"));
    }

    /**
     * Checks that only numbers are accepted as scores
     *
     * Result: 1 successful assert indicating that InvalidScoreException when a number greater than 100 is provided
     */
    @Test
    public void validateScoreOverHundred(){
        assertThrows(InvalidScoreException.class, () -> processor.validateScore("101", "Final"));
    }

    /**
     * Checks that only numbers are accepted as scores
     *
     * Result: 1 successful assert indicating that 0 (an edge case) is an accepted score
     */
    @Test
    public void validateScoreOverZeroEdgeCase(){
        try{
            assertEquals(0, processor.validateScore("0", "Final"));
        } catch(InvalidScoreException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Checks that only numbers are accepted as scores
     *
     * Result: 1 successful assert indicating that 100 (an edge case) is an accepted score
     */
    @Test
    public void validateScoreHundredEdgeCase(){
        try{
            assertEquals(100, processor.validateScore("100", "Final"));
        } catch(InvalidScoreException e){
            System.out.println(e.getMessage());
        }
    }
}

import static org.junit.jupiter.api.Assertions.*;
import org.junit.Before;
import org.junit.Test;

public class StudentTest {
    Student student;

    /**
     * Creates a new student before every test
     *
     * Results: Creates a new student object
     */
    @Before
    public void setup() {
        student = new Student("Stone", "Melissa", 40, 80, 100);
    }

    /**
     * Checks that a student object is created when the constructor is called
     *
     * Results: 1 successful assert indicating a not null Student object is created
     */
    @Test
    public void testStudentConstructorCreatesStudentObject() {
        assertNotNull(student);
    }

    /**
     * Checks that upon creating a Student object, it contains all the correct information
     *
     * Results: 5 successful asserts indicating the Student contains the expected last name, first name, midterm 1, midterm 2, and final exam scores
     */
    @Test
    public void testStudentConstructorSetsCorrectProperties() {
        Student student = new Student("Stone", "Melissa", 40, 80, 100);
        assertEquals("Stone", student.lastName);
        assertEquals("Melissa", student.firstName);
        assertEquals(40, student.midterm1);
        assertEquals(80, student.midterm2);
        assertEquals(100, student.finalExam);
    }

    /**
     * Checks that scores of A is assigned to averages of 90+ (including edge case)
     *
     * Result: 3 successful asserts indicating that the A was assigned as expected
     */
    @Test
    public void testCalculateGradeCorrectCalculationA(){
        Student student100 = new Student("Stone", "Melissa", 100, 100, 100);
        Student student91 = new Student("Stone", "Melissa", 91, 91, 91);
        Student student90 = new Student("Stone", "Melissa", 90, 90, 90);

        assertEquals('A', student100.letterGrade);
        assertEquals('A', student91.letterGrade);
        assertEquals('A', student90.letterGrade);
    }

    /**
     * Checks that scores of B is assigned to averages between 80 (inclusive) and 90 (non-inclusive) (including edge case)
     *
     * Result: 2 successful asserts indicating that the B was assigned as expected
     */
    @Test
    public void testCalculateGradeCorrectCalculationB(){
        Student student89 = new Student("Stone", "Melissa", 89, 89, 89);
        Student student80 = new Student("Stone", "Melissa", 80, 80, 80);

        assertEquals('B', student89.letterGrade);
        assertEquals('B', student80.letterGrade);
    }

    /**
     * Checks that scores of C is assigned to averages between 70 (inclusive) and 80 (non-inclusive) (including edge case)
     *
     * Result: 2 successful asserts indicating that the C was assigned as expected
     */
    @Test
    public void testCalculateGradeCorrectCalculationC(){
        Student student79 = new Student("Stone", "Melissa", 79, 79, 79);
        Student student70 = new Student("Stone", "Melissa", 70, 70, 70);

        assertEquals('C', student79.letterGrade);
        assertEquals('C', student70.letterGrade);
    }

    /**
     * Checks that scores of D is assigned to averages between 60 (inclusive) and 70 (non-inclusive) (including edge case)
     *
     * Result: 2 successful asserts indicating that the D was assigned as expected
     */
    @Test
    public void testCalculateGradeCorrectCalculationD(){
        Student student69 = new Student("Stone", "Melissa", 69, 69, 69);
        Student student60 = new Student("Stone", "Melissa", 60, 60, 60);

        assertEquals('D', student69.letterGrade);
        assertEquals('D', student60.letterGrade);
    }

    /**
     * Checks that scores of F is assigned to averages below 60 (non-inclusive)
     *
     * Result: 2 successful asserts indicating that the F was assigned as expected
     */
    @Test
    public void testCalculateGradeCorrectCalculationF(){
        Student student59 = new Student("Stone", "Melissa", 59, 59, 59);
        Student student0 = new Student("Stone", "Melissa", 0, 0, 0);

        assertEquals('F', student59.letterGrade);
        assertEquals('F', student0.letterGrade);
    }

}

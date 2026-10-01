/**
 * Represents a student with exam scores and calculated grade.
 */
public class Student {
	String lastName, firstName;
	int midterm1, midterm2, finalExam;
	double average;
	char letterGrade;

	/**
	 * Creates a new Student with exam scores and calculates average and letter grade.
	 * @param lastName the student's last name
	 * @param firstName the student's first name
	 * @param midterm1 the first midterm exam score
	 * @param midterm2 the second midterm exam score
	 * @param finalExam the final exam score
	 */
	public Student(String lastName, String firstName, int midterm1, int midterm2, int finalExam){
		this.lastName = lastName;
		this.firstName = firstName;
		this.midterm1 = midterm1;
		this.midterm2 = midterm2;
		this.finalExam = finalExam;
		this.average = (double) (midterm1 + midterm2 + finalExam) / 3;
		this.letterGrade = calculateGrade(this.average);
	}

	/**
	 * Calculates the letter grade based on the exam average.
	 * @param avg the average exam score
	 * @return the letter grade (A, B, C, D, or F)
	 */
	private char calculateGrade(double avg){
		if (avg >= 90) {
			return 'A';
		} else if (avg >= 80) {
			return 'B';
		}else if (avg >= 70){
			return 'C';
		} else if (avg >= 60) {
			return 'D';
		}else{
			return 'F';
		}
	}
}

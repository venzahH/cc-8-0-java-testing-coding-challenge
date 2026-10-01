import java.io.PrintWriter;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Processes student grade information from a TSV file and generates a grade report.
 * Reads student exam scores, calculates averages and letter grades, and outputs
 * a formatted report with individual grades and exam averages.
 */
public class GradeProcessor {
	/**
	 * Main method that processes student grades from TSV input and generates report.txt.
	 * @param args command line arguments (not used)
	 */
	public static void main(String[] args) {
		try {
			String filename = getFilenameFromUser();
			ArrayList<Student> students = readStudentData(filename);
			generateReport(students);
			System.out.println("Report generated successfully!");
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	/**
	 * Prompts user for filename and validates input.
	 * @return the filename entered by user
	 * @throws InvalidDataFormatException if filename is empty
	 */
	public static String getFilenameFromUser() throws InvalidDataFormatException {
		Scanner scanner = new Scanner(System.in);
		//1. Prompt user for tsv filename
		System.out.println("Enter the TSV filename: ");
		//2. Read the filename
		String filename = scanner.nextLine().trim();

		if (filename.isEmpty()) {
			throw new InvalidDataFormatException("Filename cannot be empty");
		}
		return filename;
	}

	/**
	 * Reads student data from TSV file and validates each record.
	 * @param filename the TSV file to read
	 * @return list of valid Student objects
	 * @throws FileNotFoundException if file doesn't exist
	 * @throws InvalidDataFormatException if no valid records found
	 */
	public static ArrayList<Student> readStudentData(String filename)
			throws FileNotFoundException, InvalidDataFormatException {
		//3. Open tsv file for reading
		Scanner fileScanner = new Scanner(new File(filename));
		//4. Create empty list to store student record
		ArrayList<Student> students = new ArrayList<>();
		
		try {
			//6. Loop until there is no next line
			while (fileScanner.hasNextLine()) {
				//7. Read the file line by line
				String line = fileScanner.nextLine().trim();
				if (line.isEmpty()) continue;
				
				try {
					//8-11. Parse and add student record
					Student student = parseStudentRecord(line);
					students.add(student);
				} catch (InvalidDataFormatException | InvalidScoreException e) {
					System.err.println("Skipping invalid record: " + e.getMessage());
				}
			}
		} finally {
			//12. Close the file
			fileScanner.close();
		}
		
		if (students.isEmpty()) {
			throw new InvalidDataFormatException("No valid student records found in file");
		}
		
		return students;
	}

	/**
	 * Parses a single student record from TSV line with validation.
	 * @param line the TSV line to parse
	 * @return Student object
	 * @throws InvalidDataFormatException if line format is invalid
	 * @throws InvalidScoreException if scores are invalid
	 */
	public static Student parseStudentRecord(String line)
			throws InvalidDataFormatException, InvalidScoreException {
		//8. Split the line by tab character into lastname, firstname, mid1, mid2, final
		String[] parts = line.split("\t");
		if (parts.length != 5) {
            throw new InvalidDataFormatException("Line must have exactly 5 tab-separated fields: " + line);
		}
		
		String lastName = validateName(parts[0], "Last name");
		String firstName = validateName(parts[1], "First name");
		int midterm1 = validateScore(parts[2], "Midterm1");
		int midterm2 = validateScore(parts[3], "Midterm2");
		int finalExam = validateScore(parts[4], "Final");
		
		//9. Create new Student object to store the data. This will also calculate the letter grade for each student.
		return new Student(lastName, firstName, midterm1, midterm2, finalExam);
	}

	/**
	 * Validates student name fields.
	 * @param name the name to validate
	 * @param fieldName the field name for error messages
	 * @return validated name
	 * @throws InvalidDataFormatException if name is invalid
	 */
	public static String validateName(String name, String fieldName)
			throws InvalidDataFormatException {
		if (name == null || name.trim().isEmpty()) {
			throw new InvalidDataFormatException(fieldName + " cannot be empty");
		}
		return name.trim();
	}

	/**
	 * Validates exam scores.
	 * @param scoreStr the score string to validate
	 * @param examName the exam name for error messages
	 * @return validated score
	 * @throws InvalidScoreException if score is invalid
	 */
	public static int validateScore(String scoreStr, String examName)
			throws InvalidScoreException {
		try {
			int score = Integer.parseInt(scoreStr.trim());
			if (score < 0 || score > 100) {
				throw new InvalidScoreException(examName + " score must be between 0-100: " + score);
			}
			return score;
		} catch (NumberFormatException e) {
			throw new InvalidScoreException(examName + " must be a valid integer: " + scoreStr);
		}
	}

	/**
	 * Generates the grade report file.
	 * @param students list of students to include in report
	 * @throws FileNotFoundException if report file cannot be created
	 */
	private static void generateReport(ArrayList<Student> students) 
			throws FileNotFoundException {
		//14. Open the report.txt file for writing
		PrintWriter writer = new PrintWriter("src/report.txt");
		
		try {
			//15. For each student print the records along with letter grade
			for (Student student : students) {
				writer.printf("%s\t%s\t%d\t%d\t%d\t%c\n", 
					student.lastName, student.firstName, 
					student.midterm1, student.midterm2, student.finalExam, 
					student.letterGrade);
			}
			
			//13. Calculate the exam averages
			//16. Also write the exam averages at bottom of the file
			double[] averages = calculateExamAverages(students);
			writer.printf("\nAverages: Midterm1 %.2f, Midterm2 %.2f, Final %.2f\n", 
				averages[0], averages[1], averages[2]);
		} finally {
			//17. Close the file
			writer.close();
		}
	}

	/**
	 * Calculates exam averages for all students.
	 * @param students list of students
	 * @return array of averages [midterm1, midterm2, final]
	 */
	private static double[] calculateExamAverages(ArrayList<Student> students) {
		//5. Create variables to track exam totals and student count
		int totalMidterm1 = 0, totalMidterm2 = 0, totalFinal = 0;
		
		//11. Update the exam totals and student count
		for (Student student : students) {
			totalMidterm1 += student.midterm1;
			totalMidterm2 += student.midterm2;
			totalFinal += student.finalExam;
		}
		
		int count = students.size();
		return new double[] {
			(double) totalMidterm1 / count,
			(double) totalMidterm2 / count,
			(double) totalFinal / count
		};
	}
}
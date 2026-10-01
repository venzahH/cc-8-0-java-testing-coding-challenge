import java.util.Scanner;
import java.util.InputMismatchException;
import model.AutonomousVehicle;
import enums.*;
import service.VehicleFactory;
import utility.InputValidator;

/**
 * Main application class for the Autonomous Vehicle Creator.
 * @author Vini Patel
 */
public class myMain {

	/**
	 * Main method that runs the autonomous vehicle creation application.
	 * @param args command line arguments (not used)
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		try {
			System.out.println("*** AUTONOMOUS VEHICLE CREATOR ***\n");
			
			VehicleType vehicleType = getVehicleType(scanner);
			int autonomyLevel = getAutonomyLevel(scanner);
			String model = getModel(scanner);
			String color = getColor(scanner);
			
			AutonomousVehicle vehicle = VehicleFactory.createVehicle(vehicleType, autonomyLevel, model, color, scanner);
			
			TurnDirection[] turns = getTurns(scanner);
			vehicle.finalDirection(turns);
			
			System.out.println("\n*** VEHICLE DETAILS ***");
			System.out.println(vehicle);
			
		} catch (InputMismatchException e) {
			System.err.println("Error: Invalid input format. Please enter numbers where required.");
		} catch (IllegalArgumentException e) {
			System.err.println("Error: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Unexpected error: " + e.getMessage());
		} finally {
			scanner.close();
		}
	}
	
	/**
	 * Gets vehicle type from user input.
	 * @param scanner the scanner for user input
	 * @return the selected VehicleType
	 */
	private static VehicleType getVehicleType(Scanner scanner) {
		System.out.print("Enter vehicle type (1-Tesla, 2-Toyota, 3-GM): ");
		return VehicleType.fromInt(scanner.nextInt());
	}
	
	/**
	 * Gets autonomy level from user input.
	 * @param scanner the scanner for user input
	 * @return the validated autonomy level
	 */
	private static int getAutonomyLevel(Scanner scanner) {
		scanner.nextLine();
		System.out.print("Enter autonomy level (1-5): ");
		int level = scanner.nextInt();
		InputValidator.validateAutonomyLevel(level);
		return level;
	}
	
	/**
	 * Gets vehicle model from user input.
	 * @param scanner the scanner for user input
	 * @return the validated vehicle model
	 */
	private static String getModel(Scanner scanner) {
		scanner.nextLine();
		System.out.print("Enter model: ");
		String model = scanner.nextLine();
		InputValidator.validateNonEmpty(model, "Model");
		return model;
	}
	
	/**
	 * Gets vehicle color from user input.
	 * @param scanner the scanner for user input
	 * @return the validated vehicle color
	 */
	private static String getColor(Scanner scanner) {
		System.out.print("Enter color: ");
		String color = scanner.nextLine();
		InputValidator.validateNonEmpty(color, "Color");
		return color;
	}
	
	/**
	 * Gets turn directions from user input.
	 * @param scanner the scanner for user input
	 * @return array of validated turn directions
	 */
	private static TurnDirection[] getTurns(Scanner scanner) {
		System.out.print("Enter turns (L/R separated by spaces): ");
		String[] turnStrings = scanner.nextLine().split(" ");
		return InputValidator.validateTurns(turnStrings);
	}

}

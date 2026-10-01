# Autonomous Vehicle Creator - Design Document

## Overview
The Autonomous Vehicle Creator is a Java console application that allows users to create and configure autonomous vehicles from different manufacturers (Tesla, Toyota, GM) and simulate their navigation through a series of turns.

## Architecture

### Design Patterns Used
- **Factory Pattern**: `VehicleFactory` creates vehicle instances based on type
- **Inheritance**: Vehicle-specific classes extend `AutonomousVehicle` base class
- **Enum Pattern**: Type-safe constants for directions, turns, and vehicle types
- **Utility Pattern**: Static validation methods in `InputValidator`

### Package Structure
```
src/
├── myMain.java                 # Main application entry point
├── enums/                      # Type-safe constants
│   ├── Direction.java          # Cardinal directions (N, E, S, W)
│   ├── TurnDirection.java      # Turn directions (L, R)
│   └── VehicleType.java        # Vehicle manufacturers (Tesla, Toyota, GM)
├── model/                      # Vehicle domain objects
│   ├── AutonomousVehicle.java  # Base vehicle class
│   ├── Tesla.java              # Tesla-specific implementation
│   ├── Toyota.java             # Toyota-specific implementation
│   └── GM.java                 # GM-specific implementation
├── service/                    # Business logic
│   └── VehicleFactory.java     # Vehicle creation factory
└── utility/                    # Helper utilities
    └── InputValidator.java     # Input validation methods
```

## File Structure Analysis

### Enums Package (`enums/`)

#### `Direction.java`
- **Purpose**: Represents cardinal directions with navigation logic
- **Key Features**: 
  - Circular turn logic (N→E→S→W→N)
  - Symbol representation for display
- **Design Rationale**: Encapsulates direction logic and prevents invalid states

#### `TurnDirection.java`
- **Purpose**: Type-safe turn commands
- **Key Features**: String-to-enum conversion with validation
- **Design Rationale**: Prevents invalid turn inputs and provides clear API

#### `VehicleType.java`
- **Purpose**: Manufacturer enumeration with numeric mapping
- **Key Features**: Integer-to-enum conversion for user input
- **Design Rationale**: Maps user-friendly numbers to type-safe enums

### Model Package (`model/`)

#### `AutonomousVehicle.java`
- **Purpose**: Base class defining common vehicle behavior
- **Key Features**:
  - Common properties (autonomy level, make, model, color)
  - Navigation logic (`finalDirection` method)
  - Protected fields for inheritance
- **Design Rationale**: Follows DRY principle and provides consistent interface

#### `Tesla.java`
- **Purpose**: Tesla-specific vehicle implementation
- **Key Features**: 
  - Fixed "Autopilot" network
  - Tesla-branded toString output
- **Design Rationale**: Demonstrates inheritance and manufacturer-specific features

#### `Toyota.java`
- **Purpose**: Toyota-specific vehicle implementation
- **Key Features**: 
  - Dynamic Lyft service determination (weekends only)
  - Date-based business logic
- **Design Rationale**: Shows time-dependent behavior and external service integration

#### `GM.java`
- **Purpose**: GM-specific vehicle implementation
- **Key Features**: 
  - Configurable vehicle types (pod, robotaxi, car)
  - Type validation logic
- **Design Rationale**: Demonstrates additional configuration parameters and validation

### Service Package (`service/`)

#### `VehicleFactory.java`
- **Purpose**: Centralized vehicle creation logic
- **Key Features**: 
  - Type-based vehicle instantiation
  - GM-specific additional input handling
  - Validation integration
- **Design Rationale**: Separates object creation from business logic, follows Factory pattern

### Utility Package (`utility/`)

#### `InputValidator.java`
- **Purpose**: Centralized input validation
- **Key Features**: 
  - Autonomy level range validation (1-5)
  - Non-empty string validation
  - Turn sequence parsing and validation
- **Design Rationale**: Single responsibility for validation, reusable across application

### Main Class (`myMain.java`)

#### `myMain.java`
- **Purpose**: Application entry point and user interaction
- **Key Features**: 
  - Step-by-step user input collection
  - Comprehensive error handling
  - Clean resource management
- **Design Rationale**: Separates UI concerns from business logic, robust error handling

## Design Decisions Rationale

### 1. Package Organization
- **Separation of Concerns**: Each package has a specific responsibility
- **Maintainability**: Related classes are grouped together
- **Scalability**: Easy to add new vehicle types or validation rules

### 2. Inheritance Hierarchy
- **Code Reuse**: Common vehicle behavior in base class
- **Polymorphism**: Factory can return different vehicle types through common interface
- **Extensibility**: New vehicle types can be added easily

### 3. Enum Usage
- **Type Safety**: Prevents invalid values at compile time
- **Readability**: Clear, self-documenting code
- **Validation**: Built-in validation through enum constraints

### 4. Factory Pattern
- **Flexibility**: Easy to modify vehicle creation logic
- **Encapsulation**: Creation complexity hidden from client code
- **Testability**: Factory can be easily mocked for testing

### 5. Static Utility Methods
- **Stateless**: No object state needed for validation
- **Performance**: No object instantiation overhead
- **Reusability**: Can be used across different classes

## Test Cases

### Edge Cases Covered

#### Input Validation Tests
1. **Invalid Vehicle Type**: Tests enum boundary validation
2. **Invalid Autonomy Level**: Tests range validation (1-5)
3. **Empty Strings**: Tests required field validation
4. **Invalid Turn Directions**: Tests enum conversion validation
5. **Invalid GM Vehicle Type**: Tests business rule validation
6. **Non-numeric Input**: Tests input format validation

#### Functional Tests
7. **Complex Turn Sequences**: Tests navigation logic accuracy
8. **Different Vehicle Types**: Tests polymorphic behavior
9. **Empty Turn Sequence**: Tests edge case handling
10. **Weekend Lyft Logic**: Tests time-dependent behavior (Toyota)

### Test Structure
```
tests/
├── test_cases.txt      # Documented test scenarios
└── run_tests.sh        # Automated test execution script
```

## Running the Program

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Unix-like environment (for Makefile and test scripts)

### Build and Run Commands

#### Using Makefile
```bash
# Compile the program
make compile

# Run the program interactively
make run

# Run all test cases
make test

# Clean compiled files
make clean

# Show help
make help
```

#### Manual Commands
```bash
# Compile
mkdir -p out
javac -d out src/**/*.java src/*.java

# Run
java -cp out myMain

# Run specific test
printf "1\n3\nModel S\nRed\nL R\n" | java -cp out myMain
```

### Sample Program Flow
```
*** AUTONOMOUS VEHICLE CREATOR ***

Enter vehicle type (1-Tesla, 2-Toyota, 3-GM): 1
Enter autonomy level (1-5): 4
Enter model: Model S
Enter color: Red
Enter turns (L/R separated by spaces): L R L

*** VEHICLE DETAILS ***

=== Tesla Vehicle ===
Make: Tesla
Model: Model S
Color: Red
Autonomy Level: 4
Network: Autopilot
Final Direction: W
```

## Error Handling Strategy

### Exception Types
- **InputMismatchException**: Invalid input format
- **IllegalArgumentException**: Business rule violations
- **Generic Exception**: Unexpected errors

### Error Messages
- Clear, user-friendly descriptions
- Specific validation failure reasons
- Graceful program termination

## Future Enhancements

### Potential Improvements
1. **Configuration File**: External configuration for vehicle types and validation rules
2. **Logging**: Structured logging for debugging and monitoring
3. **GUI Interface**: Graphical user interface for better user experience
4. **Database Integration**: Persistent storage for vehicle configurations
5. **Unit Tests**: Comprehensive JUnit test suite
6. **API Interface**: REST API for programmatic access
7. **Additional Manufacturers**: Support for more vehicle brands
8. **Advanced Navigation**: GPS coordinates and real-world mapping

### Scalability Considerations
- **Plugin Architecture**: Dynamic loading of new vehicle types
- **Microservices**: Separate services for different concerns
- **Cloud Deployment**: Containerized deployment options
- **Performance Optimization**: Caching and optimization strategies

## Conclusion

The Autonomous Vehicle Creator demonstrates solid object-oriented design principles with clear separation of concerns, robust error handling, and comprehensive testing. The modular architecture makes it easy to maintain and extend while providing a reliable foundation for future enhancements.
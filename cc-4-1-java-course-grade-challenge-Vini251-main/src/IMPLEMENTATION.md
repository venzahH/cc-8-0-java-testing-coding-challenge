# Student Grade Processor - Implementation Documentation

## Overview
This project processes student grade information from a TSV file and generates a formatted grade report with individual student grades and exam averages.

## Code Structure

### Core Classes
- **GradeProcessor.java** - Main processing logic with helper methods
- **Student.java** - Student data model with grade calculation
- **InvalidDataFormatException.java** - Custom exception for format errors
- **InvalidScoreException.java** - Custom exception for invalid scores

## Key Improvements Made

### 1. Modular Design
Refactored monolithic main method into focused helper methods:
- `getFilenameFromUser()` - Input validation
- `readStudentData()` - File reading with error handling
- `parseStudentRecord()` - Individual record parsing
- `validateName()` - Name field validation
- `validateScore()` - Score validation with range checking
- `generateReport()` - Report file generation
- `calculateExamAverages()` - Average calculations

### 2. Exception Handling Strategy

#### Custom Exceptions
```java
InvalidDataFormatException - For TSV format and data structure errors
InvalidScoreException - For invalid exam scores (range/format)
```

#### Exception Handling Approach
- **Graceful Degradation**: Invalid records are skipped with error messages
- **Resource Management**: Proper cleanup with try-finally blocks
- **User-Friendly Messages**: Clear error descriptions for debugging

#### Specific Validations
- **Filename**: Cannot be empty
- **TSV Format**: Must have exactly 5 tab-separated fields
- **Names**: Cannot be null or empty
- **Scores**: Must be integers between 0-100
- **File Existence**: Handles FileNotFoundException

### 3. Input Validation
- Empty line handling in TSV files
- Score range validation (0-100)
- Name field validation (non-empty)
- TSV structure validation (5 fields required)

### 4. Error Recovery
- Continues processing when individual records are invalid
- Provides detailed error messages for debugging
- Ensures at least one valid record exists before generating report

## Exception Flow

```
Main Method
├── getFilenameFromUser()
│   └── InvalidDataFormatException (empty filename)
├── readStudentData()
│   ├── FileNotFoundException (file not found)
│   ├── InvalidDataFormatException (no valid records)
│   └── parseStudentRecord()
│       ├── InvalidDataFormatException (wrong field count)
│       ├── validateName() → InvalidDataFormatException
│       └── validateScore() → InvalidScoreException
└── generateReport()
    └── FileNotFoundException (cannot create output)
```

## Usage
```bash
javac *.java
java GradeProcessor
```

Input: TSV filename (e.g., `StudentInfo.tsv`)
Output: `report.txt` with student grades and exam averages

## Sample Input/Output
**Input (StudentInfo.tsv):**
```
Barrett	Edan	70	45	59
Bradshaw	Reagan	96	97	88
```

**Output (report.txt):**
```
Barrett	Edan	70	45	59	F
Bradshaw	Reagan	96	97	88	A

Averages: Midterm1 83.00, Midterm2 71.00, Final 73.50
```

## Error Handling Examples
- Invalid score: "Midterm1 score must be between 0-100: 150"
- Wrong format: "Line must have exactly 5 tab-separated fields"
- Empty file: "No valid student records found in file"
- Missing file: "Error: File not found - StudentInfo.tsv"
# Test Cases for Student Grade Processor

## Test Files Overview

### 1. TestCases.tsv - Standard Test Data
**Purpose**: Test normal operation with various grade ranges
**Contents**: 10 students with scores covering all letter grades (A-F)

| Student | Midterm1 | Midterm2 | Final | Expected Grade |
|---------|----------|----------|-------|----------------|
| Smith John | 85 | 90 | 88 | B |
| Johnson Mary | 92 | 87 | 95 | A |
| Williams David | 78 | 82 | 75 | C |
| Brown Sarah | 100 | 98 | 99 | A |
| Davis Michael | 60 | 65 | 70 | D |
| Miller Lisa | 45 | 50 | 55 | F |
| Wilson Robert | 0 | 10 | 5 | F |
| Moore Jennifer | 73 | 77 | 80 | C |
| Taylor Christopher | 88 | 85 | 90 | B |
| Anderson Jessica | 95 | 92 | 97 | A |

**Expected Averages**: Midterm1: 71.60, Midterm2: 73.60, Final: 75.40

### 2. EdgeCases.tsv - Boundary Conditions
**Purpose**: Test grade boundaries and edge cases
**Contents**: Students with scores at grade boundaries

| Student | Midterm1 | Midterm2 | Final | Average | Expected Grade |
|---------|----------|----------|-------|---------|----------------|
| Perfect Student | 100 | 100 | 100 | 100.0 | A |
| Failing Student | 0 | 0 | 0 | 0.0 | F |
| Boundary High | 90 | 80 | 70 | 80.0 | B |
| Boundary Low | 89 | 79 | 69 | 79.0 | C |
| Average Student | 75 | 75 | 75 | 75.0 | C |
| Inconsistent Grades | 95 | 50 | 85 | 76.67 | C |

### 3. InvalidData.tsv - Error Handling Tests
**Purpose**: Test exception handling and data validation
**Contents**: Mix of valid and invalid records

**Invalid Records**:
- Empty first name: `	EmptyFirst	75	80	85`
- Empty last name: `LastEmpty		70	75	80`
- Non-numeric score: `Invalid	Score	abc	90	88`
- Score > 100: `OutOfRange	High	85	150	88`
- Score < 0: `OutOfRange	Low	85	-10	88`
- Missing fields: `Missing	Fields	85	90`
- Extra fields: `Extra	Fields	Too	Many	85	90	88	95`

**Expected Behavior**: Skip invalid records, process valid ones

### 4. EmptyLines.tsv - Whitespace Handling
**Purpose**: Test handling of empty lines and whitespace
**Contents**: Valid records separated by empty lines

## Test Execution

### Running Tests
```bash
# Compile all files
javac *.java

# Test with different files
java GradeProcessor
# Enter: TestCases.tsv
# Enter: EdgeCases.tsv  
# Enter: InvalidData.tsv
# Enter: EmptyLines.tsv
```

### Expected Outputs

#### TestCases.tsv Output
```
Smith	John	85	90	88	B
Johnson	Mary	92	87	95	A
Williams	David	78	82	75	C
Brown	Sarah	100	98	99	A
Davis	Michael	60	65	70	D
Miller	Lisa	45	50	55	F
Wilson	Robert	0	10	5	F
Moore	Jennifer	73	77	80	C
Taylor	Christopher	88	85	90	B
Anderson	Jessica	95	92	97	A

Averages: Midterm1 71.60, Midterm2 73.60, Final 75.40
```

#### InvalidData.tsv Expected Behavior
- Console shows error messages for invalid records
- Only valid records appear in report.txt
- Program continues execution despite errors

## Validation Checklist

- [ ] All letter grades (A, B, C, D, F) are correctly assigned
- [ ] Boundary scores (90, 80, 70, 60) produce correct grades
- [ ] Invalid records are skipped with error messages
- [ ] Empty lines are ignored
- [ ] Averages are calculated with 2 decimal places
- [ ] Program handles missing files gracefully
- [ ] Output file is properly formatted with tabs
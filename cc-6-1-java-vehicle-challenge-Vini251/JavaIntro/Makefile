# Java Vehicle App Makefile

# Directories
SRC_DIR = src
OUT_DIR = out
TEST_DIR = tests

# Java compiler and runtime
JAVAC = javac
JAVA = java

# Main class
MAIN_CLASS = myMain

# Default target
all: compile

# Compile all Java files
compile:
	@mkdir -p $(OUT_DIR)
	$(JAVAC) -d $(OUT_DIR) $(SRC_DIR)/**/*.java $(SRC_DIR)/*.java

# Run the program
run: compile
	$(JAVA) -cp $(OUT_DIR) $(MAIN_CLASS)

# Run test cases
test:
	@./$(TEST_DIR)/run_tests.sh

# Clean compiled files
clean:
	rm -rf $(OUT_DIR)

# Help
help:
	@echo "Available targets:"
	@echo "  compile - Compile all Java files"
	@echo "  run     - Compile and run the program"
	@echo "  test    - Run edge test cases"
	@echo "  clean   - Remove compiled files"
	@echo "  help    - Show this help message"

.PHONY: all compile run test clean help
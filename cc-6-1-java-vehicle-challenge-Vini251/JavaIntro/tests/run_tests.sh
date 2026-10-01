#!/bin/bash

# Test script for Vehicle App
echo "Running Vehicle App Test Suite"
echo "=============================="

# Change to parent directory where Makefile is located
#cd ..

# Compile first
make compile

echo -e "\nTest Case 1: Invalid Vehicle Type"
echo "Expected: Error - Invalid vehicle type: 4"
printf "4\n3\nModel S\nRed\nL R\n" | java -cp out myMain || true

echo -e "\nTest Case 2: Invalid Autonomy Level (Too High)"
echo "Expected: Error - Invalid autonomy level. Must be between 1 and 5."
printf "1\n6\nModel S\nRed\nL R\n" | java -cp out myMain || true

echo -e "\nTest Case 3: Invalid Autonomy Level (Too Low)"
echo "Expected: Error - Invalid autonomy level. Must be between 1 and 5."
printf "1\n0\nModel S\nRed\nL R\n" | java -cp out myMain || true

echo -e "\nTest Case 4: Empty Model Name"
echo "Expected: Error - Model cannot be empty."
printf "1\n3\n\nRed\nL R\n" | java -cp out myMain || true

echo -e "\nTest Case 5: Empty Color"
echo "Expected: Error - Color cannot be empty."
printf "1\n3\nModel S\n\nL R\n" | java -cp out myMain || true

echo -e "\nTest Case 6: Invalid Turn Direction"
echo "Expected: Error - Invalid turn: X. Only L and R are allowed."
printf "1\n3\nModel S\nRed\nL R X\n" | java -cp out myMain || true

echo -e "\nTest Case 7: Invalid GM Vehicle Type"
echo "Expected: Error - Invalid GM vehicle type. Must be pod, robotaxi, or car."
printf "3\n3\nCruise\nWhite\ntruck\nL R\n" | java -cp out myMain || true

echo -e "\nTest Case 8: Non-numeric Vehicle Type"
echo "Expected: Error - Invalid input format. Please enter numbers where required."
printf "abc\n3\nModel S\nRed\nL R\n" | java -cp out myMain || true

echo -e "\nTest Case 9: Valid Tesla (L L L -> E)"
echo "Expected: Tesla vehicle facing East (E)"
printf "1\n4\nModel S\nRed\nL L L\n" | java -cp out myMain

echo -e "\nTest Case 10: Valid Toyota (R R R L -> S)"
echo "Expected: Toyota vehicle facing South (S)"
printf "2\n3\nPrius\nBlue\nR R R L\n" | java -cp out myMain

echo -e "\nTest Case 11: Valid GM with Robotaxi (R R R R -> N)"
echo "Expected: GM vehicle facing North (N)"
printf "3\n5\nCruise\nWhite\nrobotaxi\nR R R R\n" | java -cp out myMain

echo -e "\nTest Case 12: Empty Turn Sequence ( " " -> N)"
echo "Expected: Vehicle facing North (N) - no turns applied"
printf "1\n3\nModel S\nRed\n \n" | java -cp out myMain

echo -e "\n=============================="
echo "Test Suite Complete!"
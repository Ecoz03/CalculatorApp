# Command Line Calculator App

A Java-based command line calculator designed for desktop use through the Windows console. 
This project demonstrates object-oriented design, exception handling, and memory management 
in a user-friendly console application. 

Developed for Week 5 of SDC230L: Object Oriented Programming Using Java LAB.

---

## Features

- Basic arithmetic operations: Add, Subtract, Multiply, Divide
- Division by zero error handling
- Output precision formatted to two decimal places
- Single-value memory: stores last result
- Results memory: stores up to 10 previous results
- View and clear calculation history
- Batch operations: apply a single operator to multiple inputs
- Console interface only (keyboard input)
- Persistent loop until user chooses to quit
- Modular object-oriented design

---

## Project Structure

CalculatorApp.java     # Main application logic and user interface 
Calculator.java        # Core arithmetic logic and batch operations 
SingleMemory.java      # Stores the most recent result 
MemoryList.java        # Manages up to 10 stored values

---

## How to Compile and Run

1. Save all `.java` files in the same directory.
2. Open a command prompt and navigate to the directory.
3. Compile the project: 
    javac | CalculatorApp.java | Calculator.java | SingleMemory.java | MemoryList.java
4. Run the application: 
    java CalculatorApp

---

## Example Batch Operation

User input:

    Enter numbers separated by spaces: 5 6 12 
    Select operation (+, -, *, /): +

Output:

    Batch + → Result: 23.00

---

## Author

Created by Jessie Sosniak  
Week 5 Project – SDC230L Object Oriented Programming Using Java LAB

---

## Limitations

- Console-based only; no graphical interface
- Memory is not persisted after application exit

---

## License

This project was developed for educational purposes as a technical assignment. 
All code was written independently without external calculator code reuse.



/*
* Name: Jessie Sosniak
* Date: 24 July 2025
* Assignment: 5.2 Project SDC230L
*/

//Try catch method

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class CalculatorApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static final SingleMemory singleMemory = new SingleMemory();
    private static final MemoryList resultsMemory = new MemoryList();
    private static final LinkedList<String> calculationHistory = new LinkedList<>();

    public static void main(String[] args) {
        String choice;

        System.out.println("Week 5 Project");
        System.out.println("Created by: Jessie Sosniak");
        System.out.println("Basic Operations | Memory | History");
        System.out.println("______________________________________________________");

        do {
            displayMenu();
            choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "1": case "2": case "3": case "4":
                    handleTwoNumberOperation(choice);
                    break;
                case "5":
                    handleBatchOperation();
                    break;
                case "6":
                    showPreviousCalculations();
                    break;
                case "7":
                    handleResultsMemoryMenu();
                    break;
                case "8":
                    clearMemoryConfirmation();
                    break;
                case "X":
                    System.out.println("Thanks for using the Calculator App. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid selection. Try again.");
            }
            System.out.println("______________________________________________________");
        } while (!choice.equals("X"));

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n=== MAIN MENU ===");
        System.out.println("1. Add Two Numbers");
        System.out.println("2. Subtract Two Numbers");
        System.out.println("3. Multiply Two Numbers");
        System.out.println("4. Divide Two Numbers");
        System.out.println("5. Batch Operation (Multiple Numbers)");
        System.out.println("6. Previous Calculations");
        System.out.println("7. Results Memory");
        System.out.println("8. Clear All Memory");
        System.out.println("X. Exit");
        System.out.print("Select: ");
    }

    private static void handleTwoNumberOperation(String type) {
        try {
            double num1 = getDouble("Enter first number: ");
            double num2 = getDouble("Enter second number: ");
            double result;
            String equation;

            switch (type) {
                case "1": result = Calculator.add(num1, num2); equation = format(num1, "+", num2, result); break;
                case "2": result = Calculator.subtract(num1, num2); equation = format(num1, "-", num2, result); break;
                case "3": result = Calculator.multiply(num1, num2); equation = format(num1, "*", num2, result); break;
                case "4":
                    result = Calculator.divide(num1, num2);
                    equation = format(num1, "/", num2, result);
                    break;
                default: return;
            }

            System.out.println(equation);
            singleMemory.store(result);
            resultsMemory.append(result);
            updateHistory(equation);

        } catch (InputMismatchException ime) {
            System.out.println("Invalid number input.");
            scanner.nextLine();
        } catch (ArithmeticException ae) {
            System.out.println("Math error: " + ae.getMessage());
        }
    }

    private static void handleBatchOperation() {
        try {
            System.out.print("Enter numbers separated by spaces: ");
            String[] tokens = scanner.nextLine().trim().split("\\s+");
            double[] nums = new double[tokens.length];
            for (int i = 0; i < tokens.length; i++) {
                nums[i] = Double.parseDouble(tokens[i]);
            }

            System.out.print("Select operation (+, -, *, /): ");
            String op = scanner.nextLine().trim();

            double result = Calculator.batchCalculate(nums, op);
            String equation = String.format("Batch %s → Result: %.2f", op, result);
            System.out.println(equation);

            singleMemory.store(result);
            resultsMemory.append(result);
            updateHistory(equation);

        } catch (NumberFormatException nfe) {
            System.out.println("Invalid input. Please enter only numbers.");
        } catch (ArithmeticException ae) {
            System.out.println("Math error: " + ae.getMessage());
        }
    }

    private static void showPreviousCalculations() {
        if (calculationHistory.isEmpty()) {
            System.out.println("No previous calculations available.");
            return;
        }

        System.out.println("Previous Calculations:");
        int count = 1;
        for (int i = calculationHistory.size() - 1; i >= 0 && count <= 10; i--, count++) {
            System.out.printf("%d. %s%n", count, calculationHistory.get(i));
        }
    }

    private static void handleResultsMemoryMenu() {
        System.out.println("\n-- Results Memory --");
        System.out.println("1. Display All");
        System.out.println("2. Count");
        System.out.println("3. Remove Specific Value");
        System.out.println("4. Clear All");
        System.out.println("5. Append Manual Value");
        System.out.print("Select: ");
        String subChoice = scanner.nextLine().trim();

        switch (subChoice) {
            case "1":
                var values = resultsMemory.getValues();
                if (values.isEmpty()) System.out.println("No stored values.");
                else for (int i = 0; i < values.size(); i++)
                    System.out.printf("%d. %.2f%n", i + 1, values.get(i));
                break;
            case "2":
                System.out.println("Stored result count: " + resultsMemory.count());
                break;
            case "3":
                double target = getDouble("Enter value to remove: ");
                System.out.print("Confirm delete (Y/N): ");
                if (scanner.nextLine().trim().equalsIgnoreCase("Y")) {
                    boolean removed = resultsMemory.remove(target);
                    System.out.println(removed ? "Value removed." : "Value not found.");
                }
                break;
            case "4":
                resultsMemory.clearAll();
                System.out.println("Memory cleared.");
                break;
            case "5":
                double manualVal = getDouble("Enter value to store: ");
                resultsMemory.append(manualVal);
                System.out.println("Manual value added.");
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    private static void clearMemoryConfirmation() {
        System.out.print("Are you sure? This will clear all stored memory. (Y/N): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("Y")) {
            singleMemory.clear();
            calculationHistory.clear();
            resultsMemory.clearAll();
            System.out.println("Memory cleared.");
        } else {
            System.out.println("No changes made.");
        }
    }

    private static double getDouble(String prompt) {
        System.out.print(prompt);
        double value = scanner.nextDouble();
        scanner.nextLine(); // clear newline
        return value;
    }

    private static String format(double a, String op, double b, double result) {
        return String.format("%.2f %s %.2f = %.2f", a, op, b, result);
    }

    private static void updateHistory(String entry) {
        if (calculationHistory.size() == 10) calculationHistory.removeFirst();
        calculationHistory.addLast(entry);
    }
}
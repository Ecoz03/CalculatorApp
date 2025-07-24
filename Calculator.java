public class Calculator {

    // Basic operations
    public static double add(double a, double b) {
        return a + b;
    }

    public static double subtract(double a, double b) {
        return a - b;
    }

    public static double multiply(double a, double b) {
        return a * b;
    }

    public static double divide(double a, double b) {
        if (b == 0) throw new ArithmeticException("Cannot divide by zero.");
        return a / b;
    }

    // Batch operation: add, subtract, multiply, divide on array
    public static double batchCalculate(double[] values, String operator) {
        if (values.length == 0) throw new IllegalArgumentException("No numbers provided.");
        double result = values[0];

        for (int i = 1; i < values.length; i++) {
            switch (operator) {
                case "+": result += values[i]; break;
                case "-": result -= values[i]; break;
                case "*": result *= values[i]; break;
                case "/":
                    if (values[i] == 0) throw new ArithmeticException("Cannot divide by zero in batch operation.");
                    result /= values[i];
                    break;
                default: throw new IllegalArgumentException("Invalid operator: " + operator);
            }
        }
        return result;
    }
}
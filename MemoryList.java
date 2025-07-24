import java.util.LinkedList;

public class MemoryList {
    private LinkedList<Double> values = new LinkedList<>();
    private final int MAX_SIZE = 10;

    // Store new value, remove oldest if full
    public void append(double value) {
        if (values.size() == MAX_SIZE) {
            values.removeFirst(); // Remove oldest to make space
        }
        values.addLast(value); // Add newest value
    }

    // Remove a specific value (first occurrence)
    public boolean remove(double value) {
        return values.removeFirstOccurrence(value);
    }

    // Clear all stored values
    public void clearAll() {
        values.clear();
    }

    // Return current count
    public int count() {
        return values.size();
    }

    // Return safe copy for display or processing
    public LinkedList<Double> getValues() {
        return new LinkedList<>(values);
    }
}
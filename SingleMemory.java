public class SingleMemory {
    private Double value;

    // Store a value in memory
    public void store(double value) {
        this.value = value;
    }

    // Retrieve the stored value
    public Double retrieve() {
        return value;
    }

    // Clear the stored value
    public void clear() {
        value = null;
    }

    // Check if memory is empty
    public boolean isEmpty() {
        return value == null;
    }

    // Replace the stored value with a new one
    public void replace(double newValue) {
        this.value = newValue;
    }

    // Optional: Display memory contents as a string
    @Override
    public String toString() {
        return isEmpty() ? "Memory is empty." : String.format("Stored value: %.2f", value);
    }
}
import java.util.ArrayList;
import java.util.List;

public class User {
    private String userId; // Unique identifier
    private String name;
    private int age;
    private List<Consumption> consumptionRecords; // List to store carbon records

    // Constructor to initialize User object with a generated userId
    public User(String name, int age) {
        this.userId = generateUniqueId(); // Automatically generate unique userId
        this.name = name;
        this.age = age;
        this.consumptionRecords = new ArrayList<>();
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Consumption> getConsumptionRecords() {
        return consumptionRecords;
    }

    public void addConsumptionRecord(Consumption consumption) {
        this.consumptionRecords.add(consumption);
    }

    // Method to generate a unique 4-digit user ID
    private String generateUniqueId() {
        return String.format("%04d", (int)(Math.random() * 10000));
    }
}

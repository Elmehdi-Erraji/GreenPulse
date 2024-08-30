
import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private int age;
    private String userId; // Unique identifier
    private List<Carbon> carbonRecords; // List to store carbon records

    public User(String name, int age, String userId) {
        this.name = name;
        this.age = age;
        this.userId = userId;
        this.carbonRecords = new ArrayList<>();
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

    public List<Carbon> getCarbonRecords() {
        return carbonRecords;
    }

    public void addCarbonRecord(Carbon carbon) {
        this.carbonRecords.add(carbon);
    }

    public void removeCarbonRecord(Carbon carbon) {
        this.carbonRecords.remove(carbon);
    }
}

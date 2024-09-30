package entities;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String userId;
    private String name;
    private int age;
    private List<CarbonRecord> carbonRecords;

    public User(String userId, String name, int age) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.carbonRecords = new ArrayList<>(); // Initialize the list of CarbonRecords
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

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

    public List<CarbonRecord> getCarbonRecords() {
        return carbonRecords;
    }

    public void addCarbonRecord(CarbonRecord carbonRecord) {
        this.carbonRecords.add(carbonRecord);
    }

    public double getTotalCarbonConsumption() {
        double total = 0.0;
        for (CarbonRecord record : carbonRecords) {
            total += record.getAmount();
        }
        return total;
    }

    @Override
    public String toString() {
        return "User ID: " + userId + ", Name: " + name + ", Age: " + age + ", Carbon Records: " + carbonRecords.size();
    }
}

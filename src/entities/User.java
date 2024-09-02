package entities;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String userId;
    private String name;
    private int age;
    private List<CarbonRecord> carbonRecords = new ArrayList<>();

    public User(String userId, String name, int age) {
        this.userId = userId;
        this.name = name;
        this.age = age;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<CarbonRecord> getCarbonRecords() {
        return carbonRecords;
    }

    public void addCarbonRecord(CarbonRecord record) {
        this.carbonRecords.add(record);
    }
}
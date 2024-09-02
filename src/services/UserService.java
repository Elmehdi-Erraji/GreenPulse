package services;

import entities.CarbonRecord;
import entities.User;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class UserService {
    private Map<String, User> users = new HashMap<>();
    private int userIdCounter = 1000;

    public User createUser(String name, int age) {
        String userId = String.valueOf(userIdCounter++);
        User user = new User(userId, name, age);
        users.put(userId, user);
        return user;
    }

    public User getUserById(String userId) {
        return users.get(userId);
    }

    public boolean updateUser(String userId, String newName, int newAge) {
        User user = users.get(userId);
        if (user != null) {
            user = new User(userId, newName, newAge);
            users.put(userId, user);
            return true;
        }
        return false;
    }

    public boolean deleteUser(String userId) {
        return users.remove(userId) != null;
    }

    public void addCarbonRecord(String userId, LocalDate startDate, LocalDate endDate, double amount) {
        User user = getUserById(userId);
        if (user != null) {
            user.addCarbonRecord(new CarbonRecord(startDate, endDate, amount));
        }
    }

    public void displayUserCarbonRecords(String userId) {
        User user = getUserById(userId);
        if (user != null) {
            for (CarbonRecord record : user.getCarbonRecords()) {
                System.out.println("From: " + record.getStartDate() + " To: " + record.getEndDate() + " Amount: " + record.getAmount());
            }
        }
    }

    public void generateConsumptionReport(User user, String reportType) {
        // Implement the logic for generating reports based on reportType (daily, weekly, monthly)
        System.out.println("Generating " + reportType + " report for user " + user.getName());
    }
}

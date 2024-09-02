package services;

import entities.User;
import entities.CarbonRecord;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {

    private Map<String, User> users = new HashMap<>();
    private Map<String, List<CarbonRecord>> userCarbonRecords = new HashMap<>();
    private static int userIdCounter = 1000;

    public User createUser(String name, int age) {
        String userId = String.valueOf(userIdCounter++);
        User user = new User(userId, name, age);
        users.put(userId, user);
        userCarbonRecords.put(userId, new ArrayList<>());
        return user;
    }

    public User getUserById(String userId) {
        return users.get(userId);
    }

    public boolean updateUser(String userId, String newName, int newAge) {
        User user = users.get(userId);
        if (user != null) {
            user.setName(newName);
            user.setAge(newAge);
            return true;
        }
        return false;
    }

    public boolean deleteUser(String userId) {
        if (users.containsKey(userId)) {
            users.remove(userId);
            userCarbonRecords.remove(userId);
            return true;
        }
        return false;
    }

    public void addCarbonRecord(String userId, LocalDate startDate, LocalDate endDate, double amount) {
        List<CarbonRecord> carbonRecords = userCarbonRecords.get(userId);
        if (carbonRecords != null) {
            CarbonRecord newRecord = new CarbonRecord(startDate, endDate, amount);
            carbonRecords.add(newRecord);
        } else {
            System.out.println("User not found.");
        }
    }

    public void displayUserCarbonRecords(String userId) {
        List<CarbonRecord> records = userCarbonRecords.get(userId);
        if (records != null && !records.isEmpty()) {
            for (CarbonRecord record : records) {
                System.out.println(record);
            }
        } else {
            System.out.println("No carbon records found for user with ID " + userId);
        }
    }

    public void generateConsumptionReport(User user, String reportType) {
        List<CarbonRecord> records = userCarbonRecords.get(user.getUserId());
        if (records != null && !records.isEmpty()) {
            switch (reportType.toLowerCase()) {
                case "daily":
                    generateReport(records, "Daily");
                    break;
                case "weekly":
                    generateReport(records, "Weekly");
                    break;
                case "monthly":
                    generateReport(records, "Monthly");
                    break;
                default:
                    System.out.println("Invalid report type.");
            }
        } else {
            System.out.println("No carbon consumption records available for user " + user.getName());
        }
    }

    private void generateReport(List<CarbonRecord> records, String reportType) {
        System.out.println(reportType + " Consumption Report:");
        for (CarbonRecord record : records) {
            long daysBetween = ChronoUnit.DAYS.between(record.getStartDate(), record.getEndDate()) + 1;
            double dailyConsumption = record.getAmount() / daysBetween;

            LocalDate currentDate = record.getStartDate();
            double periodConsumption = 0;
            int dayCounter = 0;

            while (!currentDate.isAfter(record.getEndDate())) {
                periodConsumption += dailyConsumption;
                dayCounter++;

                boolean isReportBoundary = false;
                switch (reportType) {
                    case "Daily":
                        isReportBoundary = true;
                        break;
                    case "Weekly":
                        isReportBoundary = dayCounter % 7 == 0 || currentDate.equals(record.getEndDate());
                        break;
                    case "Monthly":
                        isReportBoundary = currentDate.getDayOfMonth() == currentDate.lengthOfMonth() || currentDate.equals(record.getEndDate());
                        break;
                }

                if (isReportBoundary) {
                    System.out.printf("%s ending %s, Consumption: %.2f units%n", reportType, currentDate, periodConsumption);
                    periodConsumption = 0;
                }

                currentDate = currentDate.plusDays(1);
            }
        }
    }
}

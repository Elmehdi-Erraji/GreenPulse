import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;

public class UserService {
    private HashMap<String, User> users = new HashMap<>();

    public User createUser(String name, int age) {
        User user = new User(name, age);
        users.put(user.getUserId(), user);
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
        return users.remove(userId) != null;
    }

    public void addCarbonRecord(String userId, LocalDate startDate, LocalDate endDate, double amount) {
        User user = getUserById(userId);
        if (user != null) {
            user.addConsumptionRecord(new Consumption(startDate, endDate, amount));
        }
    }

    public void displayUserCarbonRecords(String userId) {
        User user = getUserById(userId);
        if (user != null) {
            List<Consumption> records = user.getConsumptionRecords();
            if (records.isEmpty()) {
                System.out.println("No carbon records found.");
            } else {
                for (Consumption record : records) {
                    System.out.println(record);
                }
            }
        }
    }

    public void generateConsumptionReport(User user, String reportType) {
        List<Consumption> records = user.getConsumptionRecords();
        if (records.isEmpty()) {
            System.out.println("No carbon consumption records available.");
            return;
        }

        double totalConsumption = 0;
        long totalDays = 0;

        // Calculate total consumption and total days
        for (Consumption record : records) {
            long daysBetween = ChronoUnit.DAYS.between(record.getStartDate(), record.getEndDate()) + 1;
            totalConsumption += record.getAmount();
            totalDays += daysBetween;
        }

        switch (reportType.toLowerCase()) {
            case "daily":
                double dailyConsumption = totalConsumption / totalDays;
                System.out.printf("Average daily consumption over %d days: %.2f units%n", totalDays, dailyConsumption);
                break;
            case "weekly":
                long totalWeeks = totalDays / 7;
                double weeklyConsumption = totalConsumption / totalWeeks;
                System.out.printf("Average weekly consumption over %d weeks: %.2f units%n", totalWeeks, weeklyConsumption);
                break;
            case "monthly":
                long totalMonths = totalDays / 30;
                double monthlyConsumption = totalConsumption / totalMonths;
                System.out.printf("Average monthly consumption over %d months: %.2f units%n", totalMonths, monthlyConsumption);
                break;
            default:
                System.out.println("Invalid report type.");
        }
    }
}

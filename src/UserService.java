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

        switch (reportType.toLowerCase()) {
            case "daily":
                generateDailyReport(records);
                break;
            case "weekly":
                generateWeeklyReport(records);
                break;
            case "monthly":
                generateMonthlyReport(records);
                break;
            default:
                System.out.println("Invalid report type.");
        }
    }

    private void generateDailyReport(List<Consumption> records) {
        System.out.println("Daily Consumption Report:");
        for (Consumption record : records) {
            long daysBetween = ChronoUnit.DAYS.between(record.getStartDate(), record.getEndDate()) + 1;
            double dailyConsumption = record.getAmount() / daysBetween;

            LocalDate currentDate = record.getStartDate();
            while (!currentDate.isAfter(record.getEndDate())) {
                System.out.printf("Date: %s, Consumption: %.2f units%n", currentDate, dailyConsumption);
                currentDate = currentDate.plusDays(1);
            }
        }
    }

    private void generateWeeklyReport(List<Consumption> records) {
        System.out.println("Weekly Consumption Report:");
        for (Consumption record : records) {
            long daysBetween = ChronoUnit.DAYS.between(record.getStartDate(), record.getEndDate()) + 1;
            double dailyConsumption = record.getAmount() / daysBetween;

            LocalDate currentDate = record.getStartDate();
            double weeklyConsumption = 0;
            int dayCounter = 0;

            while (!currentDate.isAfter(record.getEndDate())) {
                weeklyConsumption += dailyConsumption;
                dayCounter++;

                // Print consumption for each week
                if (dayCounter % 7 == 0 || currentDate.equals(record.getEndDate())) {
                    System.out.printf("Week ending %s, Consumption: %.2f units%n", currentDate, weeklyConsumption);
                    weeklyConsumption = 0;
                }

                currentDate = currentDate.plusDays(1);
            }
        }
    }

    private void generateMonthlyReport(List<Consumption> records) {
        System.out.println("Monthly Consumption Report:");
        for (Consumption record : records) {
            long daysBetween = ChronoUnit.DAYS.between(record.getStartDate(), record.getEndDate()) + 1;
            double dailyConsumption = record.getAmount() / daysBetween;

            LocalDate currentDate = record.getStartDate();
            double monthlyConsumption = 0;

            while (!currentDate.isAfter(record.getEndDate())) {
                monthlyConsumption += dailyConsumption;

                // Print consumption for each month
                if (currentDate.getDayOfMonth() == currentDate.lengthOfMonth() || currentDate.equals(record.getEndDate())) {
                    System.out.printf("Month ending %s, Consumption: %.2f units%n", currentDate, monthlyConsumption);
                    monthlyConsumption = 0;
                }

                currentDate = currentDate.plusDays(1);
            }
        }
    }
}

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class CarbonConsumption {
    private Map<LocalDate, Double> dailyConsumptions;

    // Constructor
    public CarbonConsumption() {
        this.dailyConsumptions = new HashMap<>();
    }

    // Add consumption data for a given period
    public double addConsumption(double amount, LocalDate startDate, LocalDate endDate) {
        double totalAddedConsumption = 0.0;
        LocalDate currentDate = startDate;

        while (!currentDate.isAfter(endDate)) {
            addDailyConsumption(amount, currentDate);
            totalAddedConsumption += amount;
            currentDate = currentDate.plusDays(1);
        }

        return totalAddedConsumption;
    }

    // Add daily consumption
    private void addDailyConsumption(double amount, LocalDate date) {
        dailyConsumptions.put(date, dailyConsumptions.getOrDefault(date, 0.0) + amount);
    }

    // Get total consumption for a given date range
    public double getTotalDailyConsumption(LocalDate startDate, LocalDate endDate) {
        return dailyConsumptions.entrySet().stream()
                .filter(entry -> !entry.getKey().isBefore(startDate) && !entry.getKey().isAfter(endDate))
                .mapToDouble(Map.Entry::getValue)
                .sum();
    }

    // Get a summary of consumption between two dates
    public String getConsumptionSummary(LocalDate startDate, LocalDate endDate) {
        double totalDaily = getTotalDailyConsumption(startDate, endDate);

        return String.format("Total Daily Consumption: %.2f units", totalDaily);
    }
}

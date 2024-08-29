public class CarbonConsumption {
    private double dailyConsumption;
    private double weeklyConsumption;
    private double monthlyConsumption;

    // Constructor
    public CarbonConsumption() {
        this.dailyConsumption = 0.0;
        this.weeklyConsumption = 0.0;
        this.monthlyConsumption = 0.0;
    }

    // Getters and Setters
    public double getDailyConsumption() { return dailyConsumption; }
    public void setDailyConsumption(double dailyConsumption) { this.dailyConsumption = dailyConsumption; }

    public double getWeeklyConsumption() { return weeklyConsumption; }
    public void setWeeklyConsumption(double weeklyConsumption) { this.weeklyConsumption = weeklyConsumption; }

    public double getMonthlyConsumption() { return monthlyConsumption; }
    public void setMonthlyConsumption(double monthlyConsumption) { this.monthlyConsumption = monthlyConsumption; }

    // Method to add consumption
    public void addConsumption(double amount, String period) {
        switch (period.toLowerCase()) {
            case "daily":
                dailyConsumption += amount;
                break;
            case "weekly":
                weeklyConsumption += amount;
                break;
            case "monthly":
                monthlyConsumption += amount;
                break;
            default:
                System.out.println("Invalid period specified. Use 'daily', 'weekly', or 'monthly'.");
        }
    }

    // Method to get a summary of consumption
    public String getConsumptionSummary() {
        return String.format("Daily Consumption: %.2f units\nWeekly Consumption: %.2f units\nMonthly Consumption: %.2f units",
                dailyConsumption, weeklyConsumption, monthlyConsumption);
    }
}

package services;

import entities.CarbonRecord;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class CarbonRecordService {

    public void addCarbonRecord(List<CarbonRecord> carbonRecords, LocalDate startDate, LocalDate endDate, double amount) {
        CarbonRecord newRecord = new CarbonRecord(startDate, endDate, amount);
        carbonRecords.add(newRecord);
    }

    public void displayCarbonRecords(List<CarbonRecord> records) {
        if (records.isEmpty()) {
            System.out.println("No carbon records found.");
        } else {
            for (CarbonRecord record : records) {
                System.out.println(record);
            }
        }
    }

    public void generateConsumptionReport(List<CarbonRecord> records, String reportType) {
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

    private void generateDailyReport(List<CarbonRecord> records) {
        System.out.println("Daily Consumption Report:");
        for (CarbonRecord record : records) {
            long daysBetween = ChronoUnit.DAYS.between(record.getStartDate(), record.getEndDate()) + 1;
            double dailyConsumption = record.getAmount() / daysBetween;

            LocalDate currentDate = record.getStartDate();
            while (!currentDate.isAfter(record.getEndDate())) {
                System.out.printf("Date: %s, Consumption: %.2f units%n", currentDate, dailyConsumption);
                currentDate = currentDate.plusDays(1);
            }
        }
    }

    private void generateWeeklyReport(List<CarbonRecord> records) {
        System.out.println("Weekly Consumption Report:");
        for (CarbonRecord record : records) {
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

    private void generateMonthlyReport(List<CarbonRecord> records) {
        System.out.println("Monthly Consumption Report:");
        for (CarbonRecord record : records) {
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

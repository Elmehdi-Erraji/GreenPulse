package entities;

import java.time.LocalDate;

public class CarbonRecord {
    private LocalDate startDate;
    private LocalDate endDate;
    private double amount;

    public CarbonRecord(LocalDate startDate, LocalDate endDate, double amount) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Start Date: " + startDate + ", End Date: " + endDate + ", Amount: " + amount + " units";
    }
}

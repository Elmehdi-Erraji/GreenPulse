import java.time.LocalDate;

public class Carbon {
    private LocalDate startDate;
    private LocalDate endDate;
    private double amount; // Amount of carbon consumed

    public Carbon(LocalDate startDate, LocalDate endDate, double amount) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
    }

    // Getters and Setters
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}

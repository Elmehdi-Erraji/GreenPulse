
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Carbon {
    private LocalDate date;
    private double amount; // Amount of carbon consumed

    public Carbon(LocalDate date, double amount) {
        this.date = date;
        this.amount = amount;
    }

    // Getters and Setters
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}

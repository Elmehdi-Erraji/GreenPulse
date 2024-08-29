import java.util.Date;

public class Consommation {
    private Date date;
    private double amount;

    // Constructor
    public Consommation(Date date, double amount) {
        this.date = date;
        this.amount = amount;
    }

    // Getters
    public Date getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    // Display the consumption details
    public String getConsumptionDetails() {
        return "Date: " + date + ", Amount: " + amount + " kg of CO2";
    }
}

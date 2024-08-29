import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String name;
    private int age;
    private List<Consommation> consommations;  // List of Consommation objects

    // Constructor
    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.consommations = new ArrayList<>();  // Initialize the list
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    // Modify account details
    public void modifyAccount(String newName, int newAge) {
        this.name = newName;
        this.age = newAge;
        System.out.println("Account modified successfully.");
    }

    // Add a new consommation entry
    public void addConsommation(String date, Consommation consommation) {
        this.consommations.add(consommation);
        System.out.println("Consommation added for " + date + ".");
    }

    // View consumption details
    public void viewConsumption() {
        System.out.println("Consumption details for user " + name + ":");
        for (Consommation consommation : consommations) {
            System.out.println("Date: " + consommation.getDate());
            System.out.println("Amount (kg of CO2): " + consommation.getAmount());
            System.out.println("------");
        }
        if (consommations.isEmpty()) {
            System.out.println("No consommation records found.");
        }
    }

    // Delete the account
    public void deleteAccount() {
        System.out.println("Account deleted for user: " + name);
    }
}

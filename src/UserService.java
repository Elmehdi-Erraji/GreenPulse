import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class UserService {
    private ArrayList<User> users;  // Using ArrayList to store users
    private HashSet<Integer> userIds;  // HashSet to ensure unique user IDs
    private Scanner scanner;
    private Random random;

    // Constructor
    public UserService() {
        this.users = new ArrayList<>();
        this.userIds = new HashSet<>();
        this.scanner = new Scanner(System.in);
        this.random = new Random();
    }

    // Generate a unique 4-digit user ID
    private int generateUniqueUserId() {
        int id;
        do {
            id = 1000 + random.nextInt(9000);  // Generate a 4-digit number
        } while (userIds.contains(id));  // Ensure ID is unique
        userIds.add(id);  // Add to HashSet to track unique IDs
        return id;
    }

    // Create a new user account
    public void createAccount() {
        int id = generateUniqueUserId();  // Generate random 4-digit ID
        System.out.print("Enter name: ");
        String name = scanner.next();
        System.out.print("Enter age: ");
        int age = scanner.nextInt();

        User user = new User(id, name, age);
        users.add(user);
        System.out.println("Account created successfully with User ID: " + id);
    }

    // Modify an existing user account
    public void modifyAccount() {
        System.out.print("Enter user ID to modify: ");
        int id = scanner.nextInt();

        User user = findUserById(id);
        if (user != null) {
            System.out.print("Enter new name: ");
            String newName = scanner.next();
            System.out.print("Enter new age: ");
            int newAge = scanner.nextInt();

            user.modifyAccount(newName, newAge);
        } else {
            System.out.println("User not found.");
        }
    }

    // Delete an existing user account
    public void deleteAccount() {
        System.out.print("Enter user ID to delete: ");
        int id = scanner.nextInt();

        User user = findUserById(id);
        if (user != null) {
            user.deleteAccount();
            users.remove(user);
            userIds.remove(id);  // Remove the ID from the HashSet
            System.out.println("Account deleted successfully.");
        } else {
            System.out.println("User not found.");
        }
    }

    // Add a consommation entry for a user
    public void addConsommation() {
        System.out.print("Enter user ID to add consumption: ");
        int id = scanner.nextInt();

        User user = findUserById(id);
        if (user != null) {
            System.out.print("Enter consumption date (e.g., 2024-08-29): ");
            String date = scanner.next();
            System.out.print("Enter consumption amount (kg of CO2): ");
            double amount = scanner.nextDouble();

            Consommation consommation = new Consommation(new java.util.Date(), amount);
            user.addConsommation(date, consommation);
            System.out.println("Consumption added successfully.");
        } else {
            System.out.println("User not found.");
        }
    }

    // View consumption details for a user
    public void viewConsumption() {
        System.out.print("Enter user ID to view consumption: ");
        int id = scanner.nextInt();

        User user = findUserById(id);
        if (user != null) {
            user.viewConsumption();
        } else {
            System.out.println("User not found.");
        }
    }

    // View user details (New method)
    public void viewUserDetails() {
        System.out.print("Enter user ID to view details: ");
        int id = scanner.nextInt();

        User user = findUserById(id);
        if (user != null) {
            System.out.println("User Details:");
            System.out.println("ID: " + user.getId());
            System.out.println("Name: " + user.getName());
            System.out.println("Age: " + user.getAge());
        } else {
            System.out.println("User not found.");
        }
    }

    // Find a user by their ID
    private User findUserById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    // Main menu
    public void menu() {
        int choice;
        do {
            System.out.println("\n--- User Management System ---");
            System.out.println("1. Create Account");
            System.out.println("2. Modify Account");
            System.out.println("3. Delete Account");
            System.out.println("4. Add Consommation");
            System.out.println("5. View Consumption");
            System.out.println("6. View User Details");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    modifyAccount();
                    break;
                case 3:
                    deleteAccount();
                    break;
                case 4:
                    addConsommation();
                    break;
                case 5:
                    viewConsumption();
                    break;
                case 6:
                    viewUserDetails();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 7);
    }
}

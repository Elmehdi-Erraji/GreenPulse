
import entities.User;
import services.UserService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static UserService userService = new UserService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int mainChoice;
        do {
            System.out.println("\n Main Menu:");
            System.out.println("1. User Management");
            System.out.println("2. Carbon Consumption");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            mainChoice = getValidIntInput();

            switch (mainChoice) {
                case 1:
                    userManagementMenu();
                    break;
                case 2:
                    carbonConsumptionMenu();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (mainChoice != 3);
    }

    private static void userManagementMenu() {
        int userChoice;
        do {
            System.out.println("\nUser Management Menu:");
            System.out.println("1. Create User");
            System.out.println("2. View User");
            System.out.println("3. Update User");
            System.out.println("4. Delete User");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");

            userChoice = getValidIntInput();  // Input validation for user management choices

            switch (userChoice) {
                case 1:
                    createUser();
                    break;
                case 2:
                    viewUser();
                    break;
                case 3:
                    updateUser();
                    break;
                case 4:
                    deleteUser();
                    break;
                case 5:
                    return; // Back to Main Menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (userChoice != 5);
    }

    private static void carbonConsumptionMenu() {
        int carbonChoice;
        do {
            System.out.println("\nCarbon Consumption Menu:");
            System.out.println("1. Add Carbon Record");
            System.out.println("2. View Carbon Consumption Report");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter your choice: ");

            carbonChoice = getValidIntInput();  // Input validation for carbon consumption choices

            switch (carbonChoice) {
                case 1:
                    addCarbonRecord();
                    break;
                case 2:
                    viewConsumptionReport();
                    break;
                case 3:
                    return; // Back to Main Menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (carbonChoice != 3);
    }

    private static void createUser() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = getValidIntInput();  // Input validation for age

        User user = userService.createUser(name, age);
        System.out.println("User created with ID: " + user.getUserId());
    }

    private static void viewUser() {
        System.out.print("Enter user ID: ");
        String userId = scanner.nextLine();
        User user = userService.getUserById(userId);

        if (user != null) {
            System.out.println("User ID: " + user.getUserId());
            System.out.println("Name: " + user.getName());
            System.out.println("Age: " + user.getAge());
            System.out.println("Carbon Records:");
            userService.displayUserCarbonRecords(userId);  // Display individual carbon records

            // Display total carbon consumption
            double totalCarbonConsumption = user.getTotalCarbonConsumption();
            System.out.println("Total Carbon Consumption: " + totalCarbonConsumption + " units");
        } else {
            System.out.println("User not found.");
        }
    }

    private static void updateUser() {
        System.out.print("Enter user ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter new name: ");
        String newName = scanner.nextLine();
        System.out.print("Enter new age: ");
        int newAge = getValidIntInput();  // Input validation for age

        boolean updated = userService.updateUser(userId, newName, newAge);
        if (updated) {
            System.out.println("User updated successfully.");
        } else {
            System.out.println("User not found.");
        }
    }

    private static void deleteUser() {
        System.out.print("Enter user ID: ");
        String userId = scanner.nextLine();
        boolean deleted = userService.deleteUser(userId);
        if (deleted) {
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("User not found.");
        }
    }

    private static void addCarbonRecord() {
        System.out.print("Enter user ID: ");
        String userId = scanner.nextLine();
        User user = userService.getUserById(userId);

        if (user != null) {
            LocalDate startDate = getValidDateInput("Enter start date (yyyy-mm-dd): ");
            LocalDate endDate = getValidDateInput("Enter end date (yyyy-mm-dd): ");

            if (endDate.isBefore(startDate)) {  // Date comparison to ensure valid range
                System.out.println("End date cannot be before start date. Please try again.");
                return;
            }

            System.out.print("Enter amount of carbon consumed: ");
            double amount = getValidDoubleInput();  // Input validation for carbon consumption amount
            userService.addCarbonRecord(userId, startDate, endDate, amount);
            System.out.println("Carbon record added.");
        } else {
            System.out.println("User not found.");
        }
    }

    private static void viewConsumptionReport() {
        System.out.print("Enter user ID: ");
        String userId = scanner.nextLine();
        User user = userService.getUserById(userId);
        if (user != null) {
            int reportChoice;
            do {
                System.out.println("\nChoose Report Type:");
                System.out.println("1. Daily");
                System.out.println("2. Weekly");
                System.out.println("3. Monthly");
                System.out.println("4. Back to Carbon Menu");
                System.out.print("Enter your choice: ");

                reportChoice = getValidIntInput();  // Input validation for report choice

                switch (reportChoice) {
                    case 1:
                        userService.generateConsumptionReport(user, "daily");
                        break;
                    case 2:
                        userService.generateConsumptionReport(user, "weekly");
                        break;
                    case 3:
                        userService.generateConsumptionReport(user, "monthly");
                        break;
                    case 4:
                        return;  // Back to Carbon Menu
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (reportChoice != 4);
        } else {
            System.out.println("User not found.");
        }
    }

    // Utility functions for input validation
    private static int getValidIntInput() {
        int number;
        while (true) {
            try {
                number = scanner.nextInt();
                scanner.nextLine();  // Consume newline
                return number;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();  // Consume invalid input
            }
        }
    }

    private static double getValidDoubleInput() {
        double number;
        while (true) {
            try {
                number = scanner.nextDouble();
                scanner.nextLine();  // Consume newline
                return number;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid decimal number.");
                scanner.nextLine();  // Consume invalid input
            }
        }
    }

    private static LocalDate getValidDateInput(String message) {
        LocalDate date;
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();
            try {
                date = LocalDate.parse(input);
                return date;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter a date in yyyy-mm-dd format.");
            }
        }
    }
}

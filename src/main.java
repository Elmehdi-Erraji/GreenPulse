import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    private static UserService userService = new UserService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int mainChoice;
        do {
            System.out.println("\nMain Menu:");
            System.out.println("1. User Management");
            System.out.println("2. Carbon Consumption");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            mainChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

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

            userChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

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
        } while (userChoice != 6);
    }

    private static void carbonConsumptionMenu() {
        int carbonChoice;
        do {
            System.out.println("\nCarbon Consumption Menu:");
            System.out.println("1. Add Carbon Record");
            System.out.println("2. View Carbon Consumption Report");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter your choice: ");

            carbonChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

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
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline
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
            userService.displayUserCarbonRecords(userId);
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
        int newAge = scanner.nextInt();
        scanner.nextLine(); // Consume newline
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
            System.out.print("Enter start date (yyyy-mm-dd): ");
            LocalDate startDate = LocalDate.parse(scanner.nextLine());
            System.out.print("Enter end date (yyyy-mm-dd): ");
            LocalDate endDate = LocalDate.parse(scanner.nextLine());
            System.out.print("Enter amount of carbon consumed: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
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

                reportChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

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
                        return; // Back to Carbon Menu
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (reportChoice != 4);
        } else {
            System.out.println("User not found.");
        }
    }
}


import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    private static UserService userService = new UserService();
    private static CarbonService carbonService = new CarbonService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("Menu:");
            System.out.println("1. Create User");
            System.out.println("2. View User");
            System.out.println("3. Update User");
            System.out.println("4. Delete User");
            System.out.println("5. Add Carbon Record");
            System.out.println("6. View All Users");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
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
                    addCarbonRecord();
                    break;
                case 6:
                    viewAllUsers();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 7);
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
            for (Carbon carbon : user.getCarbonRecords()) {
                System.out.println("Date: " + carbon.getDate() + ", Amount: " + carbon.getAmount());
            }
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
            System.out.print("Enter carbon record date (yyyy-mm-dd): ");
            LocalDate date = LocalDate.parse(scanner.nextLine());
            System.out.print("Enter amount of carbon consumed: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            Carbon carbon = new Carbon(date, amount);
            carbonService.addCarbonRecord(user, carbon);
            System.out.println("Carbon record added.");
        } else {
            System.out.println("User not found.");
        }
    }

    private static void viewAllUsers() {
        System.out.println("All Users:");
        userService.displayAllUsers();
    }
}

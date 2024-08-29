import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Carbon Footprint Monitoring System");
            System.out.println("1. Create User");
            System.out.println("2. Read User");
            System.out.println("3. Update User");
            System.out.println("4. Delete User");
            System.out.println("5. Add Consumption");
            System.out.println("6. Show User Consumption Summary");
            System.out.println("7. Exit");

            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Create User
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter age: ");
                    int age = scanner.nextInt();
                    userService.createUser(name, age);
                    break;

                case 2:
                    // Read User
                    System.out.print("Enter user ID: ");
                    int readId = scanner.nextInt();
                    User user = userService.readUser(readId);
                    if (user != null) {
                        System.out.println("User found: ID: " + user.getUserId() + ", Name: " + user.getName() + ", Age: " + user.getAge());
                    } else {
                        System.out.println("User not found.");
                    }
                    break;

                case 3:
                    // Update User
                    System.out.print("Enter user ID: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new age: ");
                    int newAge = scanner.nextInt();
                    userService.updateUser(updateId, newName, newAge);
                    break;

                case 4:
                    // Delete User
                    System.out.print("Enter user ID: ");
                    int deleteId = scanner.nextInt();
                    userService.deleteUser(deleteId);
                    break;

                case 5:
                    // Add Consumption
                    System.out.print("Enter user ID: ");
                    int userId = scanner.nextInt();
                    System.out.print("Enter amount of consumption: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter period (daily, weekly, monthly): ");
                    String period = scanner.nextLine();
                    User userToUpdate = userService.readUser(userId);
                    if (userToUpdate != null) {
                        userToUpdate.getConsumption().addConsumption(amount, period);
                        System.out.println("Consumption added.");
                    } else {
                        System.out.println("User not found.");
                    }
                    break;

                case 6:
                    System.out.print("Enter user ID: ");
                    int summaryId = scanner.nextInt();
                    User userForSummary = userService.readUser(summaryId);
                    if (userForSummary != null) {
                        System.out.println(userForSummary.getConsumption().getConsumptionSummary());
                    } else {
                        System.out.println("User not found.");
                    }
                    break;

                case 7:
                    // Exit
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}

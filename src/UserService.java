import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class UserService {
    private Map<String, User> users; // Using HashMap to store users with their ID as the key

    public UserService() {
        this.users = new HashMap<>();
    }

    // Create a new user
    public User createUser(String name, int age) {
        User user = new User(name, age);
        users.put(user.getUserId(), user); // Add the user to the HashMap with userId as the key
        return user;
    }

    // Get user by ID
    public User getUserById(String userId) {
        return users.get(userId); // Fetch the user from the HashMap by userId
    }

    // Update user details
    public boolean updateUser(String userId, String newName, int newAge) {
        User user = getUserById(userId);
        if (user != null) {
            user.setName(newName);
            user.setAge(newAge);
            return true;
        }
        return false;
    }

    // Delete a user
    public boolean deleteUser(String userId) {
        if (users.containsKey(userId)) {
            users.remove(userId); // Remove the user from the HashMap by userId
            return true;
        }
        return false;
    }

    // Add a carbon consumption record to a user
    public void addCarbonRecord(String userId, Carbon carbon) {
        User user = getUserById(userId);
        if (user != null) {
            user.addCarbonRecord(carbon);
        }
    }

    // Display all users
    public void displayAllUsers() {
        if (users.isEmpty()) {
            System.out.println("No users available.");
        } else {
            for (User user : users.values()) { // Iterate over the values (User objects) in the HashMap
                System.out.println("User ID: " + user.getUserId() + ", Name: " + user.getName() + ", Age: " + user.getAge());
            }
        }
    }

    // Generate a consumption report for a user
    public void generateConsumptionReport(User user, LocalDate startDate, LocalDate endDate, String reportType) {
        System.out.println("Generating " + reportType + " report for user ID: " + user.getUserId());
        // Implement report generation logic here
    }
}

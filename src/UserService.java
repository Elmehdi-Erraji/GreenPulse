
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserService {
    private Map<String, User> users; // Storage for users

    public UserService() {
        this.users = new HashMap<>();
    }

    // Generate a unique user ID
    private String generateUserId() {
        return UUID.randomUUID().toString().substring(0, 8); // Example: 8-character unique ID
    }

    // Create a new user
    public User createUser(String name, int age) {
        String userId = generateUserId();
        User newUser = new User(name, age, userId);
        users.put(userId, newUser);
        return newUser;
    }

    // Read a user by ID
    public User getUserById(String userId) {
        return users.get(userId);
    }

    // Update user details
    public boolean updateUser(String userId, String newName, int newAge) {
        User user = users.get(userId);
        if (user != null) {
            user.setName(newName);
            user.setAge(newAge);
            return true;
        }
        return false;
    }

    // Delete a user
    public boolean deleteUser(String userId) {
        return users.remove(userId) != null;
    }

    // Display all users (for debugging or administrative purposes)
    public void displayAllUsers() {
        for (User user : users.values()) {
            System.out.println("User ID: " + user.getUserId());
            System.out.println("Name: " + user.getName());
            System.out.println("Age: " + user.getAge());
            System.out.println("-----------");
        }
    }
}

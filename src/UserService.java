import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class UserService {
    private Map<Integer, User> userDatabase = new HashMap<>();
    private Random random = new Random();
    private static final int MAX_ATTEMPTS = 1000; // Maximum attempts to find a unique ID

    private static final int MIN_ID = 1000;
    private static final int MAX_ID = 9999;

    // Create a new user with a unique random ID
    public void createUser(String name, int age) {
        int userId = generateUniqueUserId();
        User user = new User(name, age, userId);
        userDatabase.put(userId, user);
        System.out.println("User created successfully with ID: " + userId);
    }

    // Read user details
    public User readUser(int userId) {
        return userDatabase.get(userId);
    }

    // Update user details
    public void updateUser(int userId, String name, int age) {
        User user = userDatabase.get(userId);
        if (user != null) {
            user.setName(name);
            user.setAge(age);
            System.out.println("User updated successfully.");
        } else {
            System.out.println("User not found.");
        }
    }

    // Delete a user
    public void deleteUser(int userId) {
        if (userDatabase.remove(userId) != null) {
            System.out.println("User deleted successfully.");
        } else {
            System.out.println("User not found.");
        }
    }

    // Generate a unique random user ID
    private int generateUniqueUserId() {
        int userId;
        int attempts = 0;
        do {
            userId = random.nextInt((MAX_ID - MIN_ID) + 1) + MIN_ID; // Generate a 4-digit integer
            attempts++;
        } while (userDatabase.containsKey(userId) && attempts < MAX_ATTEMPTS);

        if (attempts >= MAX_ATTEMPTS) {
            throw new RuntimeException("Unable to generate a unique user ID.");
        }

        return userId;
    }
}

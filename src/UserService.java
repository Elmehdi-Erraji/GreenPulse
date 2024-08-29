import java.util.HashMap;
import java.util.Map;

public class UserService {
    private Map<Integer, User> users;
    private int currentUserId;

    // Constructor
    public UserService() {
        this.users = new HashMap<>();
        this.currentUserId = 1000; // Starting user ID
    }

    // Create a new user
    public void createUser(String name, int age) {
        User newUser = new User(name, age, currentUserId++);
        users.put(newUser.getUserId(), newUser);
        System.out.println("User created: ID = " + newUser.getUserId());
    }

    // Read a user by their ID
    public User readUser(int userId) {
        return users.get(userId);
    }

    // Update a user
    public void updateUser(int userId, String newName, int newAge) {
        User user = users.get(userId);
        if (user != null) {
            user.setName(newName);
            user.setAge(newAge);
            System.out.println("User updated: ID = " + user.getUserId());
        } else {
            System.out.println("User not found.");
        }
    }

    // Delete a user by their ID
    public void deleteUser(int userId) {
        if (users.remove(userId) != null) {
            System.out.println("User deleted: ID = " + userId);
        } else {
            System.out.println("User not found.");
        }
    }
}

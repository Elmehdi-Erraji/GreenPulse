package entities;

public class User {
    private String userId;
    private String name;
    private int age;

    public User(String userId, String name, int age) {
        this.userId = userId;
        this.name = name;
        this.age = age;
    }

    // Getter and Setter for userId
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for age
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User ID: " + userId + ", Name: " + name + ", Age: " + age;
    }
}

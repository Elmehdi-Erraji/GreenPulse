public class User {
    private String name;
    private int age;
    private int userId; // Changed from String to int
    private CarbonConsumption consumption;

    // Constructor
    public User(String name, int age, int userId) {
        this.name = name;
        this.age = age;
        this.userId = userId;
        this.consumption = new CarbonConsumption();
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public int getUserId() { return userId; } // Changed from String to int

    public CarbonConsumption getConsumption() { return consumption; }
}

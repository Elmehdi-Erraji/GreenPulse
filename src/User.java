public class User {
    private String name;
    private int age;
    private int userId;
    private CarbonConsumption consumption;
    private double totalConsumption; // Field to track total consumption

    // Constructor
    public User(String name, int age, int userId) {
        this.name = name;
        this.age = age;
        this.userId = userId;
        this.consumption = new CarbonConsumption();
        this.totalConsumption = 0.0; // Initialize total consumption
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public int getUserId() { return userId; }

    public CarbonConsumption getConsumption() { return consumption; }

    public double getTotalConsumption() { return totalConsumption; }

    public void updateTotalConsumption(double additionalConsumption) {
        this.totalConsumption += additionalConsumption;
    }
}

package utils;

public class Utils {
    public static String generateUniqueId() {
        return String.format("%04d", (int)(Math.random() * 10000));
    }
}
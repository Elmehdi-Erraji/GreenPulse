import java.time.LocalDate;
import java.util.List;

public class CarbonService {

    // Add a carbon record to a user
    public void addCarbonRecord(User user, Carbon carbon) {
        // This method adds a carbon record to the user's list of records
        if (user != null && carbon != null) {
            user.addCarbonRecord(carbon);
            System.out.println("Carbon record added successfully for user: " + user.getUserId());
        } else {
            System.out.println("Failed to add carbon record. User or carbon data is invalid.");
        }
    }

    // Get all carbon records of a user
    public List<Carbon> getCarbonRecordsByUser(User user) {
        // This method returns all carbon records associated with a user
        if (user != null) {
            return user.getCarbonRecords();
        }
        System.out.println("User is null. Cannot retrieve carbon records.");
        return null;
    }

    // Get carbon record by date
    public Carbon getCarbonRecordByDate(User user, LocalDate date) {
        // This method retrieves a carbon record from the user's records by the specified date
        if (user != null && date != null) {
            for (Carbon carbon : user.getCarbonRecords()) {
                if (carbon.getDate().equals(date)) {
                    return carbon;
                }
            }
            System.out.println("No carbon record found for the specified date.");
        } else {
            System.out.println("User or date is invalid.");
        }
        return null;
    }

    // Print all carbon records of a user
    public void printAllCarbonRecords(User user) {
        // This method prints all carbon records of the given user
        if (user != null) {
            List<Carbon> carbonRecords = user.getCarbonRecords();
            if (carbonRecords.isEmpty()) {
                System.out.println("No carbon records found for user: " + user.getUserId());
            } else {
                System.out.println("Carbon records for user: " + user.getUserId());
                for (Carbon carbon : carbonRecords) {
                    System.out.println("Date: " + carbon.getDate() + ", Amount: " + carbon.getAmount());
                }
            }
        } else {
            System.out.println("User is null. Cannot display carbon records.");
        }
    }
}

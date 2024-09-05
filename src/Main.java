import database.Database;

import java.sql.Connection;
import java.sql.SQLException;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {



    public static void main(String[] args) {

        try {
            Database database = Database.getInstance();

            database.getConnection();
//            UserService userService = new UserService();
//            Menu menu = new Menu(userService);
//            menu.start();
//            database.closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public static Connection connect() {
        try {
            String url = Config.get("DB_URL");
            String user = Config.get("DB_USER");
            String password = Config.get("DB_PASSWORD");

            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database!");
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

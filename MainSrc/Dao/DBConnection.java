package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // Update the URL for MySQL database connection
    private static final String URL = "jdbc:mysql://localhost:3306/car_rental"; // MySQL uses port 3306
    private static final String USER = "root"; // Your MySQL username
    private static final String PASSWORD = "Ranjith@2004"; // Your MySQL password

    public static Connection getConnection() {
        try {
            // Load the MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");  // This loads the MySQL driver
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("❌ Connection failed: " + e.getMessage());
            return null;
        } catch (ClassNotFoundException e) {
            System.out.println("❌ MySQL JDBC Driver not found: " + e.getMessage());
            return null;
        }
    }
}

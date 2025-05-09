
package Controller;

import Dao.DBConnection;
import Model.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarController {

    public void addCar(Scanner scanner) {
        System.out.print("Enter Car ID (e.g., C001): ");
        String carId = scanner.next();
        System.out.print("Enter Car Name (Model): ");
        String name = scanner.next();
        System.out.print("Enter Car Color (Red): ");
        String color = scanner.next();
        System.out.print("Enter Price per Day: ");
        double price = scanner.nextDouble();

        String sql = "INSERT INTO cars (id, model, color, price_per_day, available) VALUES (?, ?, ?, ?, true)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, Integer.parseInt(carId.substring(1))); // Remove 'C' prefix
            stmt.setString(2, name);
            stmt.setString(3, color);
            stmt.setDouble(4, price);
            stmt.executeUpdate();

            System.out.println("! Car added successfully to database !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCar(Scanner scanner) {
        System.out.print("Enter Car ID to Update (e.g., C001): ");
        String carId = scanner.next();

        System.out.print("Enter New Car Name: ");
        String name = scanner.next();
        System.out.print("Enter New Car Color: ");
        String color = scanner.next();
        System.out.print("Enter New Price per Day: ");
        double price = scanner.nextDouble();

        String sql = "UPDATE cars SET model = ?, color = ?, price_per_day = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setString(2, color);
            stmt.setDouble(3, price);
            stmt.setInt(4, Integer.parseInt(carId.substring(1)));

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Car updated successfully in database!");
            } else {
                System.out.println("Car not found in database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayCars() {
        String sql = "SELECT * FROM cars WHERE available = true";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.printf("Car ID: C%03d | Name: %s     | Color: %s    | Price/Day: Rs.%.2f%n",
                        rs.getInt("id"),
                        rs.getString("model"),
                        rs.getString("color"),
                        rs.getDouble("price_per_day"));
            }

            if (!found) {
                System.out.println("No available cars in database.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Car> getCars() {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM cars WHERE available = true";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String carId = "C" + String.format("%03d", rs.getInt("id"));
                String name = rs.getString("model");
                String color = rs.getString("color");
                double price = rs.getDouble("price_per_day");

                cars.add(new Car(carId, name, color, price));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cars;
    }
}

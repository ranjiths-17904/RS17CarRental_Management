package Controller;

import Model.Rental;
import Model.Car;
import Model.Customer;
import Dao.DBConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RentalController {
private List<Rental> rentals = new ArrayList<>();

public void rentCar(Scanner scanner, List<Car> cars, List<Customer> customers) {
    System.out.print("Enter Car ID to Rent: ");
    String carId = scanner.next();
    Car car = null;
    for (Car c : cars) {
        if (c.getCarId().equals(carId)) {
            car = c;
            break;
        }
    }
    if (car == null) {
        System.out.println("Car not found!");
        return;
    }

    System.out.print("Enter Customer ID: ");
    String customerId = scanner.next();
    Customer customer = null;
    for (Customer cust : customers) {
        if (cust.getCustomerId().equals(customerId)) {
            customer = cust;
            break;
        }
    }
    if (customer == null) {
        System.out.println("Customer not found!");
        return;
    }

    System.out.print("Enter Rental Days: ");
    int rentalDays = scanner.nextInt();
    double totalAmount = rentalDays * car.getPricePerDay();
    System.out.printf("Total amount: %.2f for %s%n", totalAmount, car.getName());

    System.out.print("Confirm rental? (yes/no): ");
    String confirm = scanner.next();
    if (!confirm.equalsIgnoreCase("yes")) {
        System.out.println("Rental cancelled!");
        return;
    }

    System.out.println("Choose payment method (GPay/PhonePe/UPI/Normal): ");
    String paymentMethod = scanner.next();

    System.out.print("Please enter the exact amount: ");
    double amount = scanner.nextDouble();
    if (amount != totalAmount) {
        System.out.println("Incorrect amount! Payment failed.");
        return;
    }

    LocalDateTime now = LocalDateTime.now();
    Rental rental = new Rental( carId, customerId, paymentMethod, rentalDays, totalAmount, now);
    rentals.add(rental);

    try (Connection conn = DBConnection.getConnection()) {
        if (conn != null) {
            String sql = "INSERT INTO rentals (rental_id, car_id, customer_id, rental_days, total_amount, rental_date) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, rental.getRentalId());
                ps.setInt(2, Integer.parseInt(carId.replaceAll("\\D+", "")));
                ps.setString(3, customerId);
                ps.setInt(4, rentalDays);
                ps.setDouble(5, totalAmount);
                ps.setTimestamp(6, Timestamp.valueOf(now));
                ps.executeUpdate();
            }
        }
    } catch (SQLException e) {
        System.out.println("Error saving rental to database: " + e.getMessage());
    }

    System.out.println("Car rented successfully!");

    System.out.println("========================================================");
    System.out.println("|               RS17 CAR RENTAL MANAGEMENT             |");
    System.out.println("========================================================");
    System.out.printf("| %-20s : %-30s |\n", "Date", rental.getRentalDate());
    System.out.printf("| %-20s : %-30s |\n", "Customer ID", customerId);
    System.out.printf("| %-20s : %-30s |\n", "Car ID", carId);
    System.out.printf("| %-20s : %-30s |\n", "Car Name", car.getName());
    System.out.printf("| %-20s : %-30s |\n", "Rental ID", rental.getRentalId());
    System.out.printf("| %-20s : %-30d |\n", "Rental Days", rentalDays);
    System.out.printf("| %-20s : Rs.%-28.2f|\n", "Total Amount", totalAmount);
    System.out.println("========================================================");
    System.out.println("|        Car rented successfully!!  Visit Again..       |");
    System.out.println("|                       Thank You!!!                    |");
    System.out.println("========================================================");
}

public void displayRentals() {
    List<Rental> dbRentals = new ArrayList<>();

    try (Connection conn = DBConnection.getConnection()) {
        String sql = "SELECT * FROM rentals";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String rentalId = rs.getString("rental_id");
                String carId = "C" + rs.getInt("car_id"); // assuming carId format like C001
                String customerId = rs.getString("customer_id");
                int rentalDays = rs.getInt("rental_days");
                double totalAmount = rs.getDouble("total_amount");
                LocalDateTime rentalDate = rs.getTimestamp("rental_date").toLocalDateTime();

                dbRentals.add(new Rental(rentalId, carId, customerId, rentalDays, totalAmount, rentalDate));
            }
        }
    } catch (SQLException e) {
        System.out.println("Error loading rentals from database: " + e.getMessage());
        return;
    }

    if (dbRentals.isEmpty()) {
        System.out.println("No rentals found.");
        return;
    }

    System.out.println("__________________________ Rentals __________________________");
    for (Rental rental : dbRentals) {
        System.out.println("Rental ID      : " + rental.getRentalId());
        System.out.println("Car ID         : " + rental.getCarId());
        System.out.println("Customer ID    : " + rental.getCustomerId());
        System.out.println("Rental Days    : " + rental.getRentalDays());
        System.out.printf("Total Amount   : Rs.%.2f%n", rental.getTotalAmount());
        System.out.println("Rental Date    : " + rental.getRentalDate());
        System.out.println("------------------------------------------------------------");
    }
}

    public List<Rental> getRentals() {
        return rentals;
    }
}
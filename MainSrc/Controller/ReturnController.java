package Controller;

import Model.Rental;
import Dao.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class ReturnController {


public void returnCar(Scanner scanner, List<Rental> rentals) {
    System.out.print("Enter Rental ID to return car: ");
    String rentalId = scanner.next();

    Rental rental = null;
    for (Rental r : rentals) {
        if (r.getRentalId().equals(rentalId)) {
            rental = r;
            break;
        }
    }

    if (rental == null) {
        System.out.println("Rental ID not found! Please check and try again.");
        return;
    }

    System.out.println("Car found! Proceeding with return...");

    LocalDateTime expectedReturnTime = rental.getRentalDate().plusDays(rental.getRentalDays());
    LocalDateTime actualReturnTime = LocalDateTime.now();

    System.out.println("Expected Return Time: " + expectedReturnTime);
    System.out.println("Actual Return Time  : " + actualReturnTime);

    long hoursLate = Duration.between(expectedReturnTime, actualReturnTime).toHours();
    double lateFee = 0;

    if (hoursLate > 0) {
        lateFee = hoursLate * 500;
        System.out.println("Car returned late by " + hoursLate + " hour(s). Late fee applied: Rs." + lateFee);
    } else {
        System.out.println("Car returned on time. No late fee!");
    }

    // Ask user about damages
    System.out.print("Enter number of damages (enter 0 if no damages): ");
    int damages = scanner.nextInt();
    double damageFee = damages * 700;

    // Early return discount
    long actualDaysUsed = Duration.between(rental.getRentalDate(), actualReturnTime).toDays();
    double baseAmount = rental.getTotalAmount();

    double discount = 0;
    if (damages == 0 && actualDaysUsed <= (rental.getRentalDays() / 2)) {
        discount = baseAmount * 0.30;
        System.out.println("Early return detected with no damages. 30% discount applied: Rs." + discount);
    }

    double finalAmount = baseAmount + lateFee + damageFee - discount;

    System.out.println("\nFINAL BILL:");
    System.out.println("===========================================================");
    System.out.println("|               RS17 CAR RENTAL MANAGEMENT                |");
    System.out.println("===========================================================");
    System.out.printf("| %-20s : %-30s    |\n", "Rental ID", rental.getRentalId());
    System.out.printf("| %-20s : %-30s    |\n", "Car ID", rental.getCarId());
    System.out.printf("| %-20s : %-30s    |\n", "Customer ID", rental.getCustomerId());
    System.out.printf("| %-20s : %-30d    |\n", "Rental Days", rental.getRentalDays());
    System.out.printf("| %-20s : %-30s    |\n", "Return Date", actualReturnTime);
    System.out.printf("| %-20s : Rs.%-28.2f  |\n", "Base Amount", baseAmount);
    System.out.printf("| %-20s : Rs.%-28.2f  |\n", "Late Fee", lateFee);
    System.out.printf("| %-20s : Rs.%-28.2f  |\n", "Damage Fee", damageFee);
    System.out.printf("| %-20s : Rs.%-28.2f  |\n", "Discount", discount);
    System.out.printf("| %-20s : Rs.%-28.2f  |\n", "Total Amount", finalAmount);
    System.out.println("===========================================================");
    System.out.println("|            Thank You for using RS17 Car Rental!         |");
    System.out.println("|                   Please visit again!                   |");
    System.out.println("===========================================================");

    // Save return to database
    try (Connection conn = DBConnection.getConnection()) {
        String insertSQL = "INSERT INTO returns (rental_id, return_date, late_fee) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(insertSQL);
        ps.setString(1, rental.getRentalId());
        ps.setTimestamp(2, java.sql.Timestamp.valueOf(actualReturnTime));
        ps.setDouble(3, lateFee);
        ps.executeUpdate();
        System.out.println("Return record stored in database.");
    } catch (SQLException e) {
        System.out.println("Error storing return record: " + e.getMessage());
    }

    // Remove rental from active list
    rentals.remove(rental);
    }
}
package Controller;

import Model.Rental;

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

        // Assuming the expected return time is rental date + rental days
        LocalDateTime expectedReturnTime = rental.getRentalDate().plusDays(rental.getRentalDays());
        LocalDateTime actualReturnTime = LocalDateTime.now();

        System.out.println("Expected Return Time: " + expectedReturnTime);
        System.out.println("Actual Return Time  : " + actualReturnTime);

        long hoursLate = Duration.between(expectedReturnTime, actualReturnTime).toHours();
        double lateFee = 0;

        if (hoursLate > 0) {
            lateFee = hoursLate * 500; // Rs.500 per hour late fee
            System.out.println("Car returned late by " + hoursLate + " hour(s). Late fee applied: Rs." + lateFee);
        } else {
            System.out.println("Car returned on time. No late fee!");
        }

        double finalAmount = rental.getTotalAmount() + lateFee;

        System.out.println("\nFinal Bill:");
        System.out.println("===========================================================");
        System.out.println("|               RS17 CAR RENTAL MANAGEMENT                |");
        System.out.println("===========================================================");
        System.out.printf("| %-20s : %-30s  |\n", "Rental ID", rental.getRentalId());
        System.out.printf("| %-20s : %-30s  |\n", "Car ID", rental.getCarId());
        System.out.printf("| %-20s : %-30s  |\n", "Customer ID", rental.getCustomerId());
        System.out.printf("| %-20s : %-30d  |\n", "Rental Days", rental.getRentalDays());
        System.out.printf("| %-20s : Rs.%-28.2f |\n", "Return Date", actualReturnTime);
        System.out.printf("| %-20s : Rs.%-28.2f |\n", "Base Amount", rental.getTotalAmount());
        System.out.printf("| %-20s : Rs.%-28.2f |\n", "Late Fee", lateFee);
        System.out.printf("| %-20s : Rs.%-28.2f |\n", "Total Amount", finalAmount);
        System.out.println("===========================================================");
        System.out.println("|            Thank You for using RS17 Car Rental!         |");
        System.out.println("|                   Please visit again!                   |");
        System.out.println("===========================================================");

        // Remove rental from active list after return
        rentals.remove(rental);
    }
}

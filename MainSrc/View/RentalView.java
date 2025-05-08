package View;

import Model.Rental;
import java.util.List;

public class RentalView {
    public void displayRentals(List<Rental> rentals) {
        System.out.println("\nRental Records:");
        for (Rental rental : rentals) {
            System.out.println(
                "Rental ID: " + rental.getRentalId() +
                " | Car ID: " + rental.getCarId() +
                " | Customer ID: " + rental.getCustomerId() +
                " | Days: " + rental.getRentalDays() +
                " | Amount: Rs." + rental.getTotalAmount() +
                " | Date: " + rental.getRentalDate()
            );
        }
    }
}

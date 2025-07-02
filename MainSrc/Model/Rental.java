package Model;

import java.time.LocalDateTime;

public class Rental {
    private String rentalId;
    private String carId;
    private String customerId;
    private int rentalDays; 
    private double totalAmount;
    private LocalDateTime rentalDate;

    // public Rental(String carId, String customerId, int rentalDays, double totalAmount, LocalDateTime rentalDate) {
    //     this.rentalId = "RENT" + System.currentTimeMillis(); // Generate unique ID
    //     this.carId = carId;
    //     this.customerId = customerId;
    //     this.rentalDays = rentalDays;
    //     this.totalAmount = totalAmount;
    //     this.rentalDate = rentalDate;
    // }
    public Rental(String rentalId, String carId, String customerId, int rentalDays, double totalAmount, LocalDateTime rentalDate) {
    this.rentalId = "RENT" + System.currentTimeMillis(); // Generate unique ID
    this.carId = carId;
    this.customerId = customerId;
    this.rentalDays = rentalDays;
    this.totalAmount = totalAmount;
    this.rentalDate = rentalDate;
    }

    public String getRentalId() {
        return rentalId;
    }

    public String getCarId() {
        return carId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public LocalDateTime getRentalDate() {
        return rentalDate;
    }
}

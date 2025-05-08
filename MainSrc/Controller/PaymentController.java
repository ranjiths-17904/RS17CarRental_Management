package Controller;

import Model.Payment;
import java.util.ArrayList;
import java.util.List;

public class PaymentController {
    private List<Payment> payments = new ArrayList<>();

    public void addPayment(String paymentId, String rentalId, double amount, String method) {
        payments.add(new Payment(paymentId, rentalId, amount, method));
        System.out.println("Payment processed successfully!");
    }

    public void displayPayments() {
        for (Payment payment : payments) {
            System.out.println(payment.getPaymentId() + " - " + payment.getRentalId() + " - ₹" + payment.getAmount() + " via " + payment.getMethod());
        }
    }
}

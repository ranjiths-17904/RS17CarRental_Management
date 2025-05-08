package View;

import Model.Payment;
import java.util.List;

public class PaymentView {
    public void displayPayments(List<Payment> payments) {
        System.out.println("\nPayment Records:");
        for (Payment payment : payments) {
            System.out.println(
                "Payment ID: " + payment.getPaymentId() +
                " | Rental ID: " + payment.getRentalId() +
                " | Amount: Rs." + payment.getAmount() +
                " | Method: " + payment.getMethod()
            );
        }
    }
}

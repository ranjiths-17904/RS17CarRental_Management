package Model;

public class Payment {
    private String paymentId;
    private String rentalId;
    private double amount;
    private String method;

    public Payment(String paymentId, String rentalId, double amount, String method) {
        this.paymentId = paymentId;
        this.rentalId = rentalId;
        this.amount = amount;
        this.method = method;
    }

    public String getPaymentId() {
         return paymentId; }
    public String getRentalId() { 
        return rentalId; }
    public double getAmount() { 
        return amount; }
    public String getMethod() { 
        return method; }
}

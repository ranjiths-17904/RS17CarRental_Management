package View;

import Model.Customer;
import java.util.List;

public class CustomerView {
    public void displayCustomers(List<Customer> customers) {
        System.out.println("\nRegistered Customers:");
        for (Customer customer : customers) {
            System.out.println(customer.getCustomerId() + " - " + customer.getName());
        }
    }
}

package Controller;

import Model.Car;
import Model.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerController {
    private List<Customer> customers = new ArrayList<>();

    public CustomerController() {
        customers.add(new Customer("USER01", "Ranjith", "7395860211"));
        customers.add(new Customer("USER02", "Naveen", "7388123045"));
        customers.add(new Customer("USER03", "Madhan", "9987323345"));
    }
        // customers.add(new Customer("C004", "Audi A6", 12000));

    public void addCustomer(Scanner scanner) {
        System.out.println("Enter Customer ID: ");
        String customerId = scanner.next();
        System.out.print("Enter Customer Name: ");
        String name = scanner.next();
        System.out.print("Enter Customer Contact: ");
        String contact = scanner.next();

        if(contact.length() != 10)
        {
            System.out.println("Enter 10 digit Number Correctly");
        }

        Customer customer = new Customer(customerId, name, contact);
        customers.add(customer);
        System.out.println("Customer registered successfully!");
    }

    public void updateCustomer(Scanner scanner) {
        System.out.print("Enter Customer ID to Update: ");
        String customerId = scanner.next();

        Customer customer = null;
        for (Customer c : customers) {
            if (c.getCustomerId().equals(customerId)) {
                customer = c;
                break;
            }
        }

        if (customer == null) {
            System.out.println("Customer not found!");
            return;
        }

        System.out.print("Enter New Name: ");
        customer.setName(scanner.next());
        System.out.print("Enter New Contact: ");
        customer.setContact(scanner.next());

        System.out.println("Customer updated successfully!");
    }

    public void displayCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers found!");
            return;
        }

        for (Customer customer : customers) {
            System.out.println("-----------------------------------");
            System.out.println("Customer ID   : " + customer.getCustomerId());
            System.out.println("Name          : " + customer.getName());
            System.out.println("Contact       : " + customer.getContact());
            System.out.println("-----------------------------------");
        }
    }

    public List<Customer> getCustomers() {
        return customers;
    }
}
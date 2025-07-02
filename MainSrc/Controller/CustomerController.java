package Controller;
 
import Model.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Dao.DBConnection; 
public class CustomerController {
private List<Customer> customers = new ArrayList<>();

public CustomerController() {
    customers.add(new Customer("USER01", "Ranjith", "7395860211"));
    customers.add(new Customer("USER02", "Naveen", "7388123045"));
    customers.add(new Customer("USER03", "Madhan", "9987323345"));
}

public void addCustomer(String customerId, String name, String contact) {
    customers.add(new Customer(customerId, name, contact));
    try (Connection conn = DBConnection.getConnection()) {
        String sql = "INSERT INTO customers (customer_id, name, contact) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, customerId);
        stmt.setString(2, name);
        stmt.setString(3, contact);
        stmt.executeUpdate();
        System.out.println("Customer registered successfully!");
    } catch (SQLException e) {
        System.out.println("Error adding customer to database: " + e.getMessage());
    }
}

public void addCustomer(Scanner scanner) {
    System.out.print("Enter Customer ID (Eg: USER00): ");
    String customerId = scanner.next();
    System.out.print("Enter Customer Name: ");
    String name = scanner.next();
    System.out.print("Enter Customer Contact: ");
    String contact = scanner.next();

    if (contact.length() != 10) {
        System.out.println("Enter 10 digit Number Correctly");
        return;
    }

    Customer customer = new Customer(customerId, name, contact);
    customers.add(customer);

    try (Connection conn = DBConnection.getConnection()) {
        String sql = "INSERT INTO customers (customer_id, name, contact) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, customerId);
        stmt.setString(2, name);
        stmt.setString(3, contact);
        stmt.executeUpdate();
        System.out.println("Customer registered successfully!");
    } catch (SQLException e) {
        System.out.println("Error adding customer to database: " + e.getMessage());
    }
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
    String name = scanner.next();
    System.out.print("Enter New Contact: ");
    String contact = scanner.next();

    customer.setName(name);
    customer.setContact(contact);

    try (Connection conn = DBConnection.getConnection()) {
        String sql = "UPDATE customers SET name = ?, contact = ? WHERE customer_id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, name);
        stmt.setString(2, contact);
        stmt.setString(3, customerId);
        int updated = stmt.executeUpdate();
        if (updated > 0) {
            System.out.println("Customer updated successfully!");
        } else {
            System.out.println("Update failed: Customer not found in database.");
        }
    } catch (SQLException e) {
        System.out.println("Error updating customer: " + e.getMessage());
    }
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
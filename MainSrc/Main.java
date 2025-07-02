import Controller.*;
import View.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CarController carController = new CarController();
        CustomerController customerController = new CustomerController();
        RentalController rentalController = new RentalController();
        PaymentController paymentController = new PaymentController();
        ReturnController returnController = new ReturnController();

        boolean flag = true;
        System.out.println();
        System.out.print("Is Admin Login (Yes/No): ");
        String check = scanner.next();
        if (check.equalsIgnoreCase("Yes")) {
            flag = true;
        } else {
            flag = false;
        }

        while (!flag) {
            System.out.println("\n!!! Warm Welcome to RS17 Car Rental !!!\n");
            System.out.println("1. Register Customer");
            System.out.println("2. Update Customer");
            System.out.println("3. View Available Cars");
            System.out.println("4. Rent a Car");
            System.out.println("5. Return a Car");
            System.out.println("6. Exit");

            System.out.print("\nChoose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> customerController.addCustomer(scanner);
                case 2 -> customerController.updateCustomer(scanner);
                case 3 -> carController.displayCars();
                case 4 -> rentalController.rentCar(scanner, carController.getCars(), customerController.getCustomers());
                case 5 -> returnController.returnCar(scanner, rentalController.getRentals()); 
                case 6 -> {
                    System.out.println();
                    System.out.println("*** Thank you for visiting RS17 Car Rental!!! ***");
                    System.out.println();
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }

        System.out.print("Enter the Password: ");
        String passwordCheck = scanner.next();
        if (!passwordCheck.equals("AdminThe01")) {
            System.out.println("Invalid Password. Access Denied.");
            return;
        }

        System.out.println("\nWelcome!!!");
        while (flag) {
            System.out.println("\n===== RS17 Car Rental Management =====\n");
            System.out.println("1. Add Car");
            System.out.println("2. Update Car");
            System.out.println("3. Register Customer");
            System.out.println("4. Update Customer");
            System.out.println("5. Rent a Car");
            System.out.println("6. View Available Cars");
            System.out.println("7. View Customers");
            System.out.println("8. View Rentals");
            System.out.println("9. View Payments");
            System.out.println("10. Return a Car"); 
            System.out.println("11. Exit");

            System.out.print("\nChoose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> carController.addCar(scanner);
                case 2 -> carController.updateCar(scanner);
                case 3 -> customerController.addCustomer(scanner);
                case 4 -> customerController.updateCustomer(scanner);
                case 5 -> rentalController.rentCar(scanner, carController.getCars(), customerController.getCustomers());
                case 6 -> carController.displayCars();
                case 7 -> customerController.displayCustomers();
                case 8 -> rentalController.displayRentals();
                case 9 -> paymentController.displayPayments();
                case 10 -> returnController.returnCar(scanner, rentalController.getRentals()); 
                case 11 -> {
                    System.out.println("** Thank you for visiting RS17 Car Rental ***");
                    System.out.println();
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
                
            }
        }
    }
}
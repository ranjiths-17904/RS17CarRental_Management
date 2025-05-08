package View;

import Model.Car;
import java.util.List;

public class CarView {
    public void displayCars(List<Car> cars) {
        System.out.println("\nAvailable Cars:");
        for (Car car : cars) {
            System.out.println(car.getCarId() + " - " + car.getName() + " (" + car.getColor() + ") - Rs." + car.getPricePerDay() + "/day");
        }
    }
}

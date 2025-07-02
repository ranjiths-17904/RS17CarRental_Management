package Model;

public class Car {
    private String carId;
    private String name;
    private String color;
    private double pricePerDay;

    public Car(String carId, String name, String color, double pricePerDay) {
        this.carId = carId;
        this.name = name;
        this.color = color;
        this.pricePerDay = pricePerDay;
    }

    public String getCarId() { 
        return carId;
     }
    public String getName() {
         return name;
     }
    public String getColor() { 
        return color; 
    }
    public double getPricePerDay() {
         return pricePerDay; 
        }

    public void setName(String name) {
         this.name = name; 
    }
    public void setColor(String color) {
         this.color = color; 
    }
    public void setPricePerDay(double pricePerDay) {
         this.pricePerDay = pricePerDay; 
    }

    public int getId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getId'");
    }
}

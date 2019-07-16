package by.gsu.pms;

public class Car implements Comparable<Car> {

    private int id;
    private int yearOfIssue;
    private int price;
    private String model;
    private String colour;
    private String registrationNumber;

    public Car() {
    }

    public Car(int id, int yearOfIssue, int price, String model, String colour, String registrationNumber) {
        this.id = id;
        this.yearOfIssue = yearOfIssue;
        this.price = price;
        this.model = model;
        this.colour = colour;
        this.registrationNumber = registrationNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYearOfIssue() {
        return yearOfIssue;
    }

    public void setYearOfIssue(int yearOfIssue) {
        this.yearOfIssue = yearOfIssue;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String toByn(int penny) {
        return penny / 100 + "." + penny / 10 % 10 + penny % 10;
    }

    @Override
    public String toString() {
        return id + ";"
                + yearOfIssue + ";"
                + toByn(price) + ";"
                + model + ";"
                + colour + ';'
                + registrationNumber;
    }

    @Override
    public int compareTo(Car car) {
        return this.model.compareTo(car.model);
    }
}

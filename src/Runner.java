import by.gsu.pms.Car;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(new FileReader("src/in.txt"))) {
            List<Car> cars = new ArrayList<>();
            while (scanner.hasNextLine()) {
                cars.add(new Car(scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.next(), scanner.next(), scanner.next()));
            }
            Collections.sort(cars);
            FileWriter writer = new FileWriter("src/out.txt");
            for (Car car : cars) {
                int id = car.getId();
                int yearOfIssue = car.getYearOfIssue();
                int price = car.getPrice();
                String model = car.getModel();
                String colour = car.getColour();
                String registrationNumber = car.getRegistrationNumber();
                writer.write(id + " " + yearOfIssue + " " + price + " " + model + " " + colour + " " + registrationNumber + System.getProperty("line.separator"));
            }
            writer.close();
            for (Car car : cars) {
                if (car.getModel().equals("Audi") && car.getYearOfIssue() > 2007) {
                    System.out.println(car);
                }
            }
            for (Car car : cars) {
                if (car.getYearOfIssue() == 2012 && car.getPrice() > 630000) {
                    System.out.println(car);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

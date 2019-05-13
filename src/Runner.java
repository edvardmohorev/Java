import by.gsu.pmslab.*;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {

        final int ARRAY_SIZE = 3;
        final String FILE_NAME = "employee.dat";

        Employee[] employees = new Employee[ARRAY_SIZE];

        System.out.println("\tInit array:");

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME)); Scanner in = new Scanner(System.in)){
            int i = 0;
            while (i < ARRAY_SIZE){
                try {
                    System.out.print("employee["+i+"](name, pay, haveChild):\n");
                    String name = in.next();
                    int pay = in.nextInt();
                    boolean haveChild = in.nextBoolean();
                    oos.writeObject(new Employee(name, pay, haveChild));
                    i++;
                }catch (InputMismatchException ex){
                    System.out.println("Invalid value!");
                }
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))){
            for (int i = 0; i < ARRAY_SIZE; i++){
                employees[i] = (Employee) ois.readObject();
            }
        }catch (IOException | ClassNotFoundException ex){
            ex.printStackTrace();
        }

        show(employees);
        Arrays.sort(employees, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getPay() - o2.getPay();
            }
        });
        System.out.println("\tSort by pay:");
        show(employees);

        Arrays.sort(employees, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        System.out.println("\tSort by name:");
        show(employees);

        int sum=+countPay(employees);
        int avg=sum/employees.length;

        System.out.println("\tAverange pay = "+ Byn.toBYN(avg));

        System.out.println("\tEmployees with child = "+countChild(employees));
    }

    private static void show(Employee[] array){
        for (Employee item : array){
            System.out.println(item);
        }
    }
    private static int countPay(Employee[] array){
        int pay = 0;
        for (Employee item : array){
            pay += item.getPay();
        }
        return pay;
    }
    private static int countChild(Employee[] array){
        int num = 0;
        for (Employee item : array){
            if (item.isHaveChild()){
                num++;
            }
        }
        return num;
    }
}
import by.gsu.pmslab.*;

import java.util.Arrays;
import java.util.Comparator;
public class Runner {
    public static void main(String[] args) {
        Employee[] employees = {
                new Employee("Anton",15000,true),
                new Employee("Max",1450000,true),
                new Employee("Sergey",54000,false),
                new Employee("Maria",187878),
                new Employee("Eduard",8595745,true),
                new Employee("Vaniy",98657,false),
                new Employee("Pavel",437334,false),
                new Employee("Andrey",34573,true),
                new Employee("Vlad",123443,false),
                new Employee("Roma",98823,true),
        };

        System.out.println("\tInit array:");
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

        System.out.println("\tAverange pay = "+ by.gsu.pmspr.Byn.toBYN(avg));

        System.out.println("\tEmployees with child = "+countChild(employees));
    }

    private static void show(Employee[] array){
        for (Employee item : array){
            System.out.println(item);
        }
    }
    private static int countPay(Employee[] array){
        int copies = 0;
        for (Employee item : array){
            copies += item.getPay();
        }
        return copies;
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
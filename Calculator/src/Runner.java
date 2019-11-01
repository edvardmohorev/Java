import ru.javamentor.calculator.Operation;


import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        System.out.println("Введите выражение");
        Scanner scanner=new Scanner(System.in);
        Operation operation=new Operation(scanner.next(),scanner.next(),scanner.next());
        operation.action();

    }

}

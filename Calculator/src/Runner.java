import ru.javamentor.calculator.Expression;

import javax.naming.SizeLimitExceededException;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) throws SizeLimitExceededException {
        System.out.println("Введите выражение");
        Scanner scanner=new Scanner(System.in);
        String val=scanner.nextLine();
        String sa=val.replace(" ","");
        Expression expression=new Expression(sa);
        expression.getResult();
    }

}

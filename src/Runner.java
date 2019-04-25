import by.gsu.pmslab.*;


public class Runner {
    public static void main(String[] args) {
        CoffeeTable coffeeTable=new CoffeeTable(1,45,"IKEA");
        DiningTable diningTable=new DiningTable(3,24,"Пинскдрев");
        Chair chair=new Chair("IKEA",false);
        System.out.println(coffeeTable.Write());
        System.out.println("");
        System.out.println(diningTable.Write());
        System.out.println("");
        System.out.println(chair.Write());
    }
}

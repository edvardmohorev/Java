import by.gsu.pmslab.*;


public class Runner {
    public static void main(String[] args) {
        Chair chair=new Chair("Пинскдрев",true);
        Wardrobe wardrobe=new Wardrobe("IKEA",45,68,58);
        Table table=new Table(45,48,"Калинковичи мебель");
        System.out.println(chair.Write());
        System.out.println("");
        System.out.println(wardrobe.Write());
        System.out.println("");
        System.out.println(table.Write());
    }
}

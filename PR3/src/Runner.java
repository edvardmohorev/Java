import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import by.gsu.epamlab.Purchase;
import by.gsu.epamlab.factory.PurchasesFactory;
public class Runner {
    public static void main(String[]args){
        Scanner sc=null;
        try {
            sc=new Scanner(new FileReader("src/in.txt"));
            final int PURCHASES_NUMBER=6;
            Purchase[]purchases=new Purchase[PURCHASES_NUMBER];
            int maxCost=0;
            Purchase maxCostPurchase=null;
            boolean equals=true;
            for (int i=0;i<purchases.length;i++){
                purchases[i]=PurchasesFactory.getPurchaseFromFactory(sc);
                if (maxCost<purchases[i].getCost()){
                    maxCostPurchase=purchases[i];
                    maxCost=purchases[i].getCost();
                }
                System.out.println(purchases[i]);
                if(!purchases[i].equals(purchases[0])){
                    equals=false;
                }
            }
            System.out.println("\nPurchase with maximum cost: \n" + maxCostPurchase);
            if (equals) {
                System.out.println("\nObjects are equals");
            } else {
                System.out.println("\nObjects aren't equals");
            }
        } catch (FileNotFoundException ioe) {
            System.err.println("Input file is not found");
        } finally {
            if(sc != null) {
                sc.close();
            }
        }
    }
}

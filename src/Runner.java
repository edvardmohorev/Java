import by.gsu.pms.PriceDiscountPurchase;
import by.gsu.pms.Purchase;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Runner {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new FileReader("src/in.csv"))) {
            List<Purchase> purchases = new ArrayList<>();
            while (scanner.hasNext()) {
                String[] strings = scanner.nextLine().split(";");
                if (strings.length == 3) {
                    purchases.add(new Purchase(strings[0], Integer.valueOf(strings[1]), Integer.valueOf(strings[2])));
                } else {
                    purchases.add(new PriceDiscountPurchase(strings[0], Integer.valueOf(strings[1]), Integer.valueOf(strings[2]), Integer.valueOf(strings[3])));
                }
            }
            for (Purchase p : purchases) {
                System.out.println(p);
            }
            System.out.println(purchases.get(5));
            Iterator<Purchase> iterator = purchases.listIterator();
            while (iterator.hasNext()) {
                if (iterator.next().getPrice() < 10000) {
                    iterator.remove();
                }
            }
            System.out.println("Sorted collection");
            Collections.sort(purchases);
            for (Purchase p : purchases) {
                System.out.println(p);
            }
            Purchase keyPurchase = new Purchase(null, 35000, 0);
            int value = Collections.binarySearch(purchases, keyPurchase);
            if (value >= 0) {
                System.out.println(purchases.get(value));
            } else {
                System.out.println("No such elements in file");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}

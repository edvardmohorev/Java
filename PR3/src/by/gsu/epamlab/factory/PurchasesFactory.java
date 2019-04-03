package by.gsu.epamlab.factory;
        import by.gsu.epamlab.*;
        import java.util.Scanner;
public class PurchasesFactory {
    private static enum PurchaseKind {
        PURCHASE {
            Purchase getPurchase(Scanner sc) {
                return new Purchase(sc);
            }
        },
        PURCHASE_PRICE {
            Purchase getPurchase(Scanner sc) {
                return new PurchasePrice(sc);
            }
        },
        PURCHASE_PERCENT {
            Purchase getPurchase(Scanner sc) {
                return new PurchasePercent(sc);
            }
        };

        abstract Purchase getPurchase(Scanner sc);
    }

    public static Purchase getPurchaseFromFactory(Scanner sc){
        String id=sc.next();
        return PurchaseKind.valueOf(id).getPurchase(sc);
    }
}
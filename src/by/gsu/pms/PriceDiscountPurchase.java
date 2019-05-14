package by.gsu.pms;

import java.util.Scanner;

public class PriceDiscountPurchase extends Purchase {
    private int discount;

    public PriceDiscountPurchase() {
    }

    public PriceDiscountPurchase(String productName, int price, int numberOfPurchasedUnits, int discount) {
        super(productName, price, numberOfPurchasedUnits);
        this.discount = discount;
    }

    public PriceDiscountPurchase(Scanner sc) {
        super(sc);
        this.discount = sc.nextInt();
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public int getCost() {
        return (super.getPrice() - discount) * super.getNumberOfPurchasedUnits();    }

    @Override
    public String fieldsToString() {
        return super.fieldsToString() + ";" + discount;
    }
}

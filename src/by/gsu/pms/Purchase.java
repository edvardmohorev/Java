package by.gsu.pms;

import java.util.Objects;
import java.util.Scanner;

public class Purchase implements Comparable<Purchase>{

    private String productName;
    private int price;
    private int numberOfPurchasedUnits;

    public Purchase() {
    }

    public Purchase(String productName, int price, int numberOfPurchasedUnits) {
        this.productName = productName;
        this.price =  price;
        this.numberOfPurchasedUnits = numberOfPurchasedUnits;
    }

    public Purchase(Scanner sc){
        this(sc.next(),sc.nextInt(), sc.nextInt());
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumberOfPurchasedUnits() {
        return numberOfPurchasedUnits;
    }

    public void setNumberOfPurchasedUnits(int numberOfPurchasedUnits) {
        this.numberOfPurchasedUnits = numberOfPurchasedUnits;
    }

    public int getCost() {
        return price * numberOfPurchasedUnits;
    }

    public String toByn(int penny) {
        return penny / 100 + "." + penny / 10 % 10 + penny % 10;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Purchase)) return false;
        Purchase purchase = (Purchase) o;
        return numberOfPurchasedUnits == purchase.numberOfPurchasedUnits &&
                Objects.equals(productName, purchase.productName) &&
                Objects.equals(price, purchase.price);
    }

    protected String fieldsToString(){
        return productName + ";" + price + ";" + numberOfPurchasedUnits;
    }

    @Override
    public String toString() {
        return fieldsToString() + ";" + toByn(getCost());
    }

    @Override
    public int compareTo(Purchase purchase) {
        return this.price - purchase.price;
    }
}

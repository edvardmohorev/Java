package by.gsu.epamlab;

import java.util.Scanner;

public class PurchasePrice extends Purchase {
    private int priceDiscount;
    private PurchasePrice(){
        super();
    }
    public PurchasePrice(String productName, int price, int numberOfUnits, int priceDiscount){
        super(productName, price, numberOfUnits);
        this.priceDiscount=priceDiscount;
    }
    public PurchasePrice(Scanner sc){
        super(sc);
        this.priceDiscount=sc.nextInt();
    }
    public int getPriceDiscount() {
        return priceDiscount;
    }
    public void setPriceDiscount(int priceDiscount) {
        this.priceDiscount = priceDiscount;
    }
    public int getCost(){
        return (super.getCost()-priceDiscount)*getNumberOfUnits();
    }
    protected String fieldsToString(){
        return super.fieldsToString()+";"+priceDiscount;
    }
}

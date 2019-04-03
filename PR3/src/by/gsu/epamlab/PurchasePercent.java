package by.gsu.epamlab;
import java.util.Scanner;
public class PurchasePercent extends Purchase{
    private double percent;
    private final int NECESSARY_NUMBER=10;
    public PurchasePercent(){
        super();
    }
    public PurchasePercent(String productName,int price,int numberOfUnits,double percent){
        super(productName, price, numberOfUnits);
        this.percent=percent;
    }
    public PurchasePercent(Scanner sc){
        super(sc);
        this.percent=sc.nextDouble();
    }
    public double getPercent() {
        return percent;
    }
    public void setPercent(double percent) {
        this.percent = percent;
    }
    public int getCost(){
        if(getNumberOfUnits()>NECESSARY_NUMBER){
            return (int)(super.getCost()*(1-percent/100));
        }else {
            return super.getCost();
        }
    }
    protected String fieldsToString(){
        return super.fieldsToString()+";"+percent;
    }
}

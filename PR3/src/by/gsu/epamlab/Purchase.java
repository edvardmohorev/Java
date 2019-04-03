package by.gsu.epamlab;
import java.util.Scanner;
public class Purchase {
    private String productName;
    private Byn price;
    private int numberOfUnits;
    public Purchase(){
        super();
    }
    public Purchase(String productName,Byn price,int numberOfUnits){
        super();
        this.productName=productName;
        this.price=price;
        this.numberOfUnits=numberOfUnits;
    }
    public Purchase(Scanner sc){
        this.productName=sc.next();
        this.price=new Byn(sc.nextInt());
        this.numberOfUnits=sc.nextInt();
    }
    public String getProductName() {
        return productName;
    }
    public Byn getPrice() {
        return price;
    }
    public int getNumberOfUnits() {
        return numberOfUnits;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public void setPrice(Byn price) {
        this.price = price;
    }
    public void setNumberOfUnits(int numberOfUnits) {
        this.numberOfUnits = numberOfUnits;
    }
    public int getCost(){
        int Cost= price*numberOfUnits;
        return Cost;
    }
    public boolean equals(Object obj){
        if (this==obj){
            return true;
        }
        if(obj==null){
            return false;
        }
        if(!(obj instanceof Purchase)){
            return false;
        }
        Purchase other=(Purchase)obj;
        if (productName==null){
            if(other.productName!=null){
                return false;
            }
        }else if (!productName.equals(other.productName)){
            return false;
        }
        if(price!=other.price){
            return false;
        }
        return true;
    }
    protected String fieldsToString(){
        return productName+";"+price+";"+numberOfUnits;
    }
    public String toString(){
        return fieldsToString()+";"+getCost();
    }
}

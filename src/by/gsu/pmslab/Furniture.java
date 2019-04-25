package by.gsu.pmslab;

public class Furniture {
    private String manufacture;
    private int price;
    private String material;
    public Furniture(String manufacture,int price,String material){
        super();
        this.manufacture=manufacture;
        this.price=price;
        this.material=material;
    }
    public Furniture(){
        super();
        this.manufacture=manufacture;
        this.price=0;
        this.material=material;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
    public String field(){
        return "Manufacture: "+manufacture+";"+" Material: "+material+";"+" Price: "+Byn.toBYN(price);
    }
    public String toString(){
        return "Furniture: \n"+field();
    }
}

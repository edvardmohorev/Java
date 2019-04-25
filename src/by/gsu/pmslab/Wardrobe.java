package by.gsu.pmslab;
import by.gsu.pmslab.Furniture;

public class Wardrobe extends Furniture{
    private double width;
    private double depth;
    private double height;

    public Wardrobe(String manufacture, int price, String material,double width,double depth, double height) {
        super(manufacture, price, material);
        this.width=width;
        this.depth=depth;
        this.height=height;
    }
    public Wardrobe(String manufacture, int price, String material) {
        super(manufacture, price, material);
        this.width=0;
        this.depth=0;
        this.height=0;
    }
    public double getWidth() {
        return width;
    }
    public void setWidth(double width) {
        this.width = width;
    }
    public double getDepth() {
        return depth;
    }
    public void setDepth(double depth) {
        this.depth = depth;
    }
    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }
    public String toString(){
        return "Wardrobe: \n"+super.field()+";"+" Width: "+width+";"+" Height: "+height+";"+" Depth: "+depth;
    }
}

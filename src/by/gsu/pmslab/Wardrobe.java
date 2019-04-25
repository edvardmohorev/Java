package by.gsu.pmslab;
import by.gsu.pmslab.Furniture;

public class Wardrobe implements Furniture,WriteInfo{
    private String manufacture;
    private double width;
    private double depth;
    private double height;

    public Wardrobe(String manufacture,double width,double depth, double height) {
        this.width=width;
        this.depth=depth;
        this.height=height;
        this.manufacture=manufacture;
    }
    public Wardrobe() {
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
    public String getManufacture() {
        return manufacture;
    }
    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }
    public String Write(){
        return toString()+Country();
    }
    public String toString(){
        return "Wardrobe: \n"+"Manufacture: "+manufacture+"; "+"Width: "+width+";"+" Height: "+height+";"+" Depth: "+depth;
    }
    public String Country() {
        if(getManufacture().equals("Пинскдрев")||getManufacture().equals("Калинковичи мебель")){
            return "\nЭто булорусский производитель";
        }
        else return "\nЭто не булорусский производитель";
    }
}

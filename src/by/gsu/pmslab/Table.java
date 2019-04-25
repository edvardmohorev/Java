package by.gsu.pmslab;
import by.gsu.pmslab.Furniture;
public class Table extends Furniture {
    private int number_of_boxes;
    private double working_surface_area;

    public Table(String manufacture, int price, String material,int number_of_boxes,double working_surface_area) {
        super(manufacture, price, material);
        this.number_of_boxes=number_of_boxes;
        this.working_surface_area=working_surface_area;
    }
    public Table(String manufacture, int price, String material) {
        super(manufacture, price, material);
        this.number_of_boxes=0;
        this.working_surface_area=0;
    }
    public int getNumber_of_boxes() {
        return number_of_boxes;
    }
    public void setNumber_of_boxes(int number_of_boxes) {
        this.number_of_boxes = number_of_boxes;
    }
    public double getWorking_surface_area() {
        return working_surface_area;
    }
    public void setWorking_surface_area(double working_surface_area) {
        this.working_surface_area = working_surface_area;
    }
    public String toString(){
        return "Table: \n"+super.field()+";"+" Number of boxes: "+number_of_boxes+";"+" Working surface area: "+working_surface_area;
    }
}

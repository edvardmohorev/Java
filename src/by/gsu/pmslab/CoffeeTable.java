package by.gsu.pmslab;

public class CoffeeTable extends Table {
    private final String type="Coffee Table";
    public CoffeeTable(int number_of_boxes, double working_surface_area, String manufacture) {
        super( manufacture,number_of_boxes, working_surface_area);
    }
    public String toString(){
        return super.toString()+" Type: "+type;
    }
}


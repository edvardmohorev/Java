package by.gsu.pmslab;
public abstract class Table implements Furniture,WriteInfo {
    private String manufacture;
    private int number_of_boxes;
    private double working_surface_area;


    public Table(String manufacture,int number_of_boxes,double working_surface_area) {
        this.number_of_boxes=number_of_boxes;
        this.working_surface_area=working_surface_area;
        this.manufacture=manufacture;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
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
    public String Write(){
        return toString()+Country();
    }
    public String toString(){
        return "Table: \n"+"Manufacture: "+manufacture+"; "+"Number of boxes: "+number_of_boxes+";"+" Working surface area: "+working_surface_area ;
    }
    @Override
    public String Country() {
        if(getManufacture().equals("Пинскдрев")||getManufacture().equals("Калинковичи мебель")){
            return "\nЭто булорусский производитель";
        }
        else return "\nЭто не булорусский производитель";
    }
}

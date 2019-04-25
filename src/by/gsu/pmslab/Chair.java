package by.gsu.pmslab;
import by.gsu.pmslab.Furniture;
public class Chair implements Furniture,WriteInfo{
    private String manufacture;
    private boolean adjusting_the_pos_of_the_back;

    public Chair(){
        this.manufacture=null;
    }

    public Chair(String manufacture,boolean adjusting_the_pos_of_the_back) {
        this.manufacture=manufacture;
        this.adjusting_the_pos_of_the_back=adjusting_the_pos_of_the_back;
    }

    public String getManufacture() {
        return manufacture;
    }
    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }
    public boolean isAdjusting_the_pos_of_the_back() {
        return adjusting_the_pos_of_the_back;
    }
    public void setAdjusting_the_pos_of_the_back(boolean adjusting_the_pos_of_the_back) {
        this.adjusting_the_pos_of_the_back = adjusting_the_pos_of_the_back;
    }
    public String Write(){
        return toString()+Country();
    }

    public String toString(){
        return "Chair: \n"+manufacture+";"+" Adjusting the pos of the back: "+adjusting_the_pos_of_the_back;
    }

    @Override
    public String Country() {
        if(getManufacture().equals("Пинскдрев")||getManufacture().equals("Калинковичи мебель")){
            return "\nЭто булорусский производитель";
        }
        else return "\nЭто не булорусский производитель";
    }
}

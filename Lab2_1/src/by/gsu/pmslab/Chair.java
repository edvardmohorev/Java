package by.gsu.pmslab;
import by.gsu.pmslab.Furniture;
public class Chair extends Furniture{
    private boolean adjusting_the_pos_of_the_back;

    public Chair(String manufacture, int price, String material,boolean adjusting_the_pos_of_the_back) {
        super(manufacture, price, material);
        this.adjusting_the_pos_of_the_back=adjusting_the_pos_of_the_back;
    }
    public boolean isAdjusting_the_pos_of_the_back() {
        return adjusting_the_pos_of_the_back;
    }
    public void setAdjusting_the_pos_of_the_back(boolean adjusting_the_pos_of_the_back) {
        this.adjusting_the_pos_of_the_back = adjusting_the_pos_of_the_back;
    }
    public String toString(){
        return "Chair: \n"+super.field()+";"+" Adjusting the pos of the back: "+adjusting_the_pos_of_the_back;
    }
}

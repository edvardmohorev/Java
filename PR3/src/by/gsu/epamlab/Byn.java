package by.gsu.epamlab;

public class Byn {
    private int values;
    public Byn(int values){
        this.values=values;
    }
    int getRubs(){
        return values/100;
    }
    int getCoins(){
            return values/10%10+values%10;
        }
        public String toString(){
        return getRubs()+"."+getCoins();
        }
}

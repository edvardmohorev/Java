package by.gsu.pmspr;

public class Byn {
    public static String toBYN(int copic){
        return copic/100 + "." + copic/10%10 + copic % 10+" BYN";
    }
}

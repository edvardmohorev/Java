import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Card {
    String suit;
    String num;
    int value;

    public Card(String suit, String num, int value) {
        this.suit = suit;
        this.num = num;
        this.value = value;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "#########\n" +
                "#" + num + "      #\n" +
                "#   " + suit + "   #\n" +
                "#      " + num + "#\n" +
                "#########\n";
    }
}



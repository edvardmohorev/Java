package by.gsu.pmslab;

public class ExchangeRate {
    String charCode;
    int scale;
    String name;
    float rate;
    int id;
    public void setId(int id) {
        this.id = id;
    }
    public String getCharCode() {
        return charCode;
    }
    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }
    public int getScale() {
        return scale;
    }
    public void setScale(int scale) {
        this.scale = scale;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public float getRate() {
        return rate;
    }
    public void setRate(float rate) {
        this.rate = rate;
    }
    public String toString(){
        return name+"\n"+scale+" "+charCode+"="+rate+" BYN";
    }
}


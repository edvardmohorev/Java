package by.gsu.pmslab.classes;

public class Wind {
    int speed;
    int deg;
    String degree;

    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public String getDeg() {
        if(deg==0 || deg==360)
            degree="N";
        if (deg > 0 && deg < 90)
            degree = "NE";
        if (deg==90)
            degree="E";
        if (deg>90 && deg<180)
            degree="SE";
        if (deg==180)
            degree="S";
        if (deg>180 && deg<270)
            degree="SW";
        if (deg==270)
            degree="W";
        if (deg>270 && deg<360)
            degree="NW";
        return degree;
    }
    public void setDeg(int deg) {
        this.deg = deg;
    }
}

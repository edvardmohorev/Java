package by.gsu.pmslab.classes;

public class Main {
    float temp;
    float temp_min;
    float temp_max;
    int pressure;
    int humidity;
    public float getTemp() {
        return temp-273;
    }
    public void setTemp(float temp) {
        this.temp = temp;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public float getTemp_min() {
        return temp_min-273;
    }
    public void setTemp_min(float temp_min) {
        this.temp_min = temp_min;
    }
    public float getTemp_max() {
        return temp_max-273;
    }
    public void setTemp_max(float temp_max) {
        this.temp_max = temp_max;
    }
    public int getHumidity() {
        return humidity;
    }
    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }
}

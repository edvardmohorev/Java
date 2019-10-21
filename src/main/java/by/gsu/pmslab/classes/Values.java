package by.gsu.pmslab.classes;

import java.util.List;

public class Values {
    Main main;
    Wind wind;
    List<Weather> weather;
    Clouds clouds;
    String name;
    int cod;
    String icon;
    String extended_desc;
    String brief_desc;
    float temp;
    int pressure;
    int humidity;
    float min_temp;
    float max_temp;
    String degree;
    int speed;
    int cloud;

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getIcon() {
        String array=String.valueOf(weather.get(0));
        String[] icon=array.split(" ");
        return icon[0];
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getExtended_desc() {
        String array=String.valueOf(weather.get(0));
        String[] extended_desc=array.split(" ");
        return extended_desc[1];
    }

    public void setExtended_desc(String extended_desc) {
        this.extended_desc = extended_desc;
    }

    public String getBrief_desc() {
        String array=String.valueOf(weather.get(0));
        String[] brief_desc=array.split(" ");
        return brief_desc[2];
    }

    public void setBrief_desc(String brief_desc) {
        this.brief_desc = brief_desc;
    }

    public float getTemp() {
        return main.getTemp();
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public int getPressure() {
        return main.getPressure();
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return main.getHumidity();
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public float getMin_temp() {
        return main.getTemp_min();
    }

    public void setMin_temp(float min_temp) {
        this.min_temp = min_temp;
    }

    public float getMax_temp() {
        return main.getTemp_max();
    }

    public void setMax_temp(float max_temp) {
        this.max_temp = max_temp;
    }

    public int getSpeed() {
        return wind.getSpeed();
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getDegree() {
        return wind.getDeg();
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }
    public int getCloud() {
        return clouds.getAll();
    }
    public void setCloud(int cloud) {
        this.cloud = cloud;
    }
}

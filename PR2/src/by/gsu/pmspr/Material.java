package by.gsu.pmspr;
public enum  Material {
    STEEL("steel",7850),COPPER("copper",8500);
    private String name;
    private double DENSITY;
    Material(String name ,double DENSITY){
        this.name=name;
        this.DENSITY=DENSITY;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDENSITY() {
        return this.DENSITY;
    }

    public void setDENSITY(double DENSITY) {
        this.DENSITY = DENSITY;
    }

    public String toString(){
        return name+";"+DENSITY;
    }
}

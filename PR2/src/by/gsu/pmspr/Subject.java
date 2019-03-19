package by.gsu.pmspr;

public class Subject {
private final static String name="wire";
private Material material;
private final static double volume=0.03;

public Subject(Material material) {
    this.material = material;

}
    public String getName() {
        return name;
    }
    public Material getMaterial() {
        return material;
    }
    public double getVolume() {
        return volume;
    }
    public void setMaterial(Material material) {
        this.material = material;
    }
    public double getMass(){
    return material.getDENSITY()*volume;
    }
    public String toString(){
    return name + ";" + material + ";" + volume + ";" + getMass();
    }
}

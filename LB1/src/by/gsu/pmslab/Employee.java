package by.gsu.pmslab;

public class Employee {
    private String name;
    private int pay;
    private boolean haveChild;

    public Employee(String name, int pay, boolean haveChild){
        this.name = name;
        this.pay = pay;
        this.haveChild = haveChild;
    }
    public Employee(String name, int pay){
        this(name,pay,true);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPay() {
        return pay;
    }
    public void setPay(int pay) {
        this.pay = pay;
    }

    public boolean isHaveChild() {
        return haveChild;
    }

    public void setHaveChild(boolean haveChild) {
        this.haveChild = haveChild;
    }
    @Override
    public String toString(){
        return name+";"+ by.gsu.pmspr.Byn.toBYN(pay)+";"+haveChild;
    }
}

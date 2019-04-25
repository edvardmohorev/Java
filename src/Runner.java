import by.gsu.pmslab.*;


public class Runner {
    public static void main(String[] args) {
        Furniture[] furniture=new Furniture[4];
        furniture[0]=new Furniture("Пинск",6000,"Береза");
        furniture[1]=new Wardrobe("Минск",5000,"Сосна",2.1,1.2,2.5);
        furniture[2]=new Table("Гомель",3000,"Клен",4,2.3);
        furniture[3]=new Chair("Тула",200,"Дуб",false);
        for(int i=0;i<furniture.length;i++){
            System.out.println(furniture[i]);
        }
    }
}

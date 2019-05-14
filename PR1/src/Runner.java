import by.gsu.pmspr.*;

public class Runner {

    public static void main(String[] args) {
        int maxTotalExpenses=0;
        BusinessTrip[] businessTrips = {
                new BusinessTrip("Anton", 500, 31),
                null,
                new BusinessTrip("Sergey", 1235, 7),
                new BusinessTrip("Misha", 12767, 1),
                new BusinessTrip("Ken", 543, 23),
                new BusinessTrip("Edik", 8767, 32),
                new BusinessTrip()
        };
        for (BusinessTrip item : businessTrips){
            if (item != null){
                item.show();
            }
            System.out.println();
        }
        businessTrips[6].setTransport(55);

        System.out.println("Duration = "+(businessTrips[2].getDays()+businessTrips[3].getDays()));

        for (BusinessTrip item : businessTrips){
            System.out.println(item);
            System.out.println();
        }
        int sum = 0;
        for (BusinessTrip item : businessTrips){
            if (item != null){
                sum += item.getTotal();
            }

        }
        System.out.println("Sum of the total expenses = "+ Byn.toBYN(sum));
        for (BusinessTrip businessTrip : businessTrips) {
            if (businessTrip != null) {
                if (businessTrip.getTotal() > maxTotalExpenses) {
                    maxTotalExpenses = businessTrip.getTotal();
                }
            }
        }

        for (BusinessTrip businessTrip : businessTrips) {
            if (businessTrip != null) {
                if (businessTrip.getTotal() > maxTotalExpenses) {
                    maxTotalExpenses = businessTrip.getTotal();
                }
                if (businessTrip.getTotal() == maxTotalExpenses) {
                    System.out.println("Accoutnt with max total: "+businessTrip.getAccount());
                }
            }
        }
    }
}
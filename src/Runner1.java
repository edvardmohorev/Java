import by.gsu.pms.AccountType;
import by.gsu.pms.BuisnessTrip;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Runner1 {
    private static final String FILE_PATH = "src/in.txt";
    private static final String ACCOUNT_FIELD = "account";
    private static final String TRANSPORT_FIELD = "transport";
    private static final String DAYS_FIELD = "days";

    public static void main(String[] args) {

        int dailyRate = 0;
        String[] indices = null;
        List<BuisnessTrip> buisnessTrips = new ArrayList<>();
        String[] line = null;

        try(Scanner scanner = new Scanner(new File(FILE_PATH))) {

            line = scanner.nextLine().split("=");
            dailyRate = Integer.parseInt(line[1].trim());

            line = scanner.nextLine().split("=");
            indices = line[1].trim().split(";");

        }catch (FileNotFoundException ex){
            ex.printStackTrace();
        }

        for (String item : indices){

            BuisnessTrip currentTrip = new BuisnessTrip();
            currentTrip.setDailyRate(dailyRate);

            try(Scanner scanner = new Scanner(new File(FILE_PATH))) {
                scanner.nextLine();scanner.nextLine();

                while (scanner.hasNextLine()){

                    line = scanner.nextLine().split("=");
                    String leftPart = line[0].trim();

                    int currIndex = Integer.parseInt(leftPart.substring(leftPart.length()-2));
                    String field = leftPart.substring(0, leftPart.length()-2);
                    String value = line[1].trim();

                    if (Integer.parseInt(item.trim()) == currIndex){

                        currentTrip.setId(currIndex);
                        switch (field){
                            case ACCOUNT_FIELD:{
                                if (args.length == 2){
                                    if (value.contains(args[0])){
                                        value = args[1];
                                    }
                                }
                                currentTrip.setAccount(AccountType.valueOf(value.toUpperCase()));break;
                            }
                            case TRANSPORT_FIELD: currentTrip.setTransportExpences(Integer.parseInt(value));break;
                            case DAYS_FIELD: currentTrip.setDays(Integer.parseInt(value));break;
                        }
                    }
                }

                buisnessTrips.add(currentTrip);

            }catch (FileNotFoundException | NullPointerException ex){
                ex.printStackTrace();
            }
        }

        System.out.println("\tScanned data:");
        for (BuisnessTrip item : buisnessTrips){
            System.out.println(item);
        }
        Collections.sort(buisnessTrips, new Comparator<BuisnessTrip>() {
            @Override
            public int compare(BuisnessTrip o1, BuisnessTrip o2) {
                return o2.getTotalCost() - o1.getTotalCost();
            }
        });
        System.out.println("\tSorted list:");
        for (BuisnessTrip item : buisnessTrips){
            System.out.println(item);
        }
    }
}

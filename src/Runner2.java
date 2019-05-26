import by.gsu.pms.AccountType;
import by.gsu.pms.BuisnessTrip;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Runner2 {

    private static final String FILE_PATH = "src/in.csv";
    private static final String CUT_EXPRESSION ="crazy";

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(new File(FILE_PATH))) {
            int arraySize = scanner.nextInt();
            BuisnessTrip[] scannedTrips = new BuisnessTrip[arraySize];
            int iterator = 0;
            while (scanner.hasNextLine()){

                String[] line = scanner.nextLine().split(";");
                if (line[0].contains(CUT_EXPRESSION)){
                    iterator--;
                    continue;
                }

                if (line.length > 1){
                    scannedTrips[iterator] = new BuisnessTrip(iterator, AccountType.valueOf(line[0].trim().toUpperCase()),
                            Integer.parseInt(line[2].trim()), Integer.parseInt(line[1].trim()));
                }else {
                    scannedTrips[iterator] = new BuisnessTrip();
                }
                iterator++;
            }
            System.out.println("\tScanned data:");
            for (BuisnessTrip buisnessTrip : scannedTrips){
                System.out.println(buisnessTrip);
            }
            BuisnessTrip[] buisnessTrips = cutNullElements(scannedTrips);
            Arrays.sort(buisnessTrips, new Comparator<BuisnessTrip>() {
                @Override
                public int compare(BuisnessTrip o1, BuisnessTrip o2) {
                    return o1.getTotalCost() - o2.getTotalCost();
                }
            });
            System.out.println("\tSorted array:");
            for (BuisnessTrip item : buisnessTrips){
                System.out.println(item);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    private static BuisnessTrip[] cutNullElements(BuisnessTrip[] array){
        List<BuisnessTrip> list = new ArrayList<>();
        int nullElements = 0;

        for (BuisnessTrip item : array){
            if(item != null){
                list.add(item);
            }else {
                nullElements++;
            }
        }
        return list.toArray(new BuisnessTrip[array.length - nullElements]);
    }
}

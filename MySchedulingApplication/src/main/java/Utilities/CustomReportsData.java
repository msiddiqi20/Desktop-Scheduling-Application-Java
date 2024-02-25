package Utilities;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;

/**This is the abstract Custom Reports class.*/
public abstract class CustomReportsData {

    private static ObservableList<CustomReports> customReports = FXCollections.observableArrayList();

    /**This is the Custom Reports getter.
     * This method returns all the Custom Reports.
     * @return This returns all the custom reports in the form of an ObservableList (CustomReports)*/
    public static ObservableList<CustomReports> getCustomReports(){
        return customReports;
    }

    /**This is the Custom Reports setter.
     * This method sets all the custom reports data.*/
    public static void setCustomReports() {

        HashMap<String, Integer> dvisionHM = new HashMap<>();

        for (Customers customer : CustomerData.getAllCustomers()) {

            String divisionCheck = FirstLevelDivisionData.getFirstLevelDivisionsHM().get(customer.getDivisionID()).getDivisionName();

            if (!dvisionHM.containsKey(divisionCheck)) {

                dvisionHM.put(divisionCheck, 1);

            } else {

                dvisionHM.put(divisionCheck, dvisionHM.get(divisionCheck) + 1);

            }

        }


        dvisionHM.forEach((Key, Value) -> {

            customReports.add(new CustomReports(Key, Value));

        });

    }

}

package Utilities;

import DAO.FirstLevelDivisionsQuery;
import Model.FirstLevelDivisions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**This is the abstract First Level Divisions Data class.*/
public abstract class FirstLevelDivisionData {

    private static ObservableList<FirstLevelDivisions> allFirstLevelDivisions = FXCollections.observableArrayList();
    private static HashMap<Integer, FirstLevelDivisions> firstLevelDivisionsHM = new HashMap<>();
    private static ObservableList<String> firstLevelDivisionsNames = FXCollections.observableArrayList();

    /**This is the All First Level Divisions getter.
     * This method returns all the First Level Divisions.
     * @return This returns all the First Level Divisions in the form of an ObservableList (CFirstLevelDivisions)*/
    public static ObservableList<FirstLevelDivisions> getAllFirstLevelDivisions(){
        return allFirstLevelDivisions;
    }

    /**This is the First Level Divisions Hash Map getter.
     * This method returns the First Level Divisions Hash Map.
     * @return This returns all the First Level Divisions in the form of a Hash Map (Integer, First Level Divisions).*/
    public static HashMap<Integer, FirstLevelDivisions> getFirstLevelDivisionsHM(){
        return firstLevelDivisionsHM;
    }

    /**This is the First Level Division Names getter.
     * This method returns all the First Level Division Names.
     * @return This returns all the First Level Division names in the form of an ObservableList (String)*/
    public static  ObservableList<String> getFirstLevelDivisionsNames(){
        return firstLevelDivisionsNames;
    }

    /**This is the All First Level Divisions setter.
     * This method calls the First Level Divisions Query to retrieve and set all the local First Level Division data.
     * @throws SQLException Throws SQLException.*/
    public static void setAllFirstLevelDivisions() throws SQLException {
        ResultSet resultSet = FirstLevelDivisionsQuery.read();

        while(resultSet.next()){

            int divisionID = resultSet.getInt("Division_ID");
            String divisionName = resultSet.getString("Division");
            int countryID = resultSet.getInt("Country_ID");

            allFirstLevelDivisions.add(new FirstLevelDivisions(divisionID, divisionName, countryID));

        }

        for (FirstLevelDivisions firstLevelDivision : allFirstLevelDivisions) {

            firstLevelDivisionsHM.put(firstLevelDivision.getDivisionID(), firstLevelDivision);
            CountriesData.getCountriesHM().get(firstLevelDivision.getCountryID()).getDivisions().add(firstLevelDivision.getDivisionName());
            firstLevelDivisionsNames.add(firstLevelDivision.getDivisionName());

        }

    }

}

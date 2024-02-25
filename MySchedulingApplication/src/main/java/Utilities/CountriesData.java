package Utilities;

import DAO.CountriesQuery;
import Model.Countries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**This is the abstract Countries Data class.*/
public abstract class CountriesData {

    private static ObservableList<Countries> allCountries = FXCollections.observableArrayList();
    private static HashMap<Integer, Countries> countriesHM = new HashMap<>();
    private static ObservableList<String> countryNames = FXCollections.observableArrayList();

    /**This is the All Countries getter.
     * This method returns all the Countries.
     * @return This returns all the countries in the form of an ObservableList (Countries)*/
    public static ObservableList<Countries> getAllCountries(){
        return allCountries;
    }

    /**This is the Countries Hash Map getter.
     * This method returns the Countries Hash Map.
     * @return This returns all the countries in the form of a Hash Map (Integer, Countries).*/
    public static HashMap<Integer, Countries> getCountriesHM(){
        return countriesHM;
    }

    /**This is the Country Names getter.
     * This method returns all the Country Names.
     * @return This returns all the country names in the form of an ObservableList (String)*/
    public static ObservableList<String> getCountryNames(){
        return countryNames;
    }

    /**This is the All Countries setter.
     * This method calls the Countries Query to retrieve and set all the local country data.
     * @throws SQLException Throws SQLException.*/
    public static void setAllCountries() throws SQLException {
        ResultSet resultSet = CountriesQuery.read();

        while(resultSet.next()){
            int countryID = resultSet.getInt("Country_ID");
            String countryName = resultSet.getString("Country");

            allCountries.add(new Countries(countryID, countryName));
        }

        for (Countries country : allCountries){
            countriesHM.put(country.getCountryID(), country);
            countryNames.add(country.getCountryName());
        }

    }

}

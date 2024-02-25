package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**This is the Countries class.*/
public class Countries {

    /**This is the Countries class constructor.
     * @param countryID This is the country ID for the country.
     * @param countryName This is the country Name for the country.*/
    public Countries(int countryID, String countryName) {
        this.countryID = countryID;
        this.countryName = countryName;
    }

    private int countryID;
    private String countryName;
    private ObservableList<String> divisionNames = FXCollections.observableArrayList();

    /**This is the Country ID getter.
     * This method returns the ID of the country.
     * @return The country ID (int).*/
    public int getCountryID() {
        return countryID;
    }

    /**This is the Country ID setter.
     * This method sets the ID for the country.
     * @param countryID The desired country ID (int).*/
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    /**This is the Country Name getter.
     * This method returns the name of the country.
     * @return The country name (String).*/
    public String getCountryName() {
        return countryName;
    }

    /**This is the Country Name setter.
     * This method sets the name for the country.
     * @param countryName The desired country name (String).*/
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**This is the Divisions getter.
     * This method returns the list of division names.
     * @return The list of division names ObservableList(String).*/
    public ObservableList<String> getDivisions() {
        return divisionNames;
    }

}

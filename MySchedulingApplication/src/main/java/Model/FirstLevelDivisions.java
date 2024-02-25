package Model;

/**This is the First Level Divisions class.*/
public class FirstLevelDivisions {

    /**This is the First Level Divisions class constructor.
     * @param divisionID This is the division id.
     * @param divisionName This is the division name.
     * @param countryID This is th country ID that this division is a part of.*/
    public FirstLevelDivisions(int divisionID, String divisionName, int countryID) {
        this.divisionID = divisionID;
        this.divisionName = divisionName;
        this.countryID = countryID;
    }

    private int divisionID;
    private String divisionName;
    private int countryID;

    /**This is the Division ID getter.
     * This method returns the ID of the division.
     * @return The division ID (int).*/
    public int getDivisionID() {
        return divisionID;
    }

    /**This is the Division ID setter.
     * This method sets the ID for the division.
     * @param divisionID The desired division ID (int).*/
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    /**This is the Division Name getter.
     * This method returns the name of the division.
     * @return The division name (String).*/
    public String getDivisionName() {
        return divisionName;
    }

    /**This is the Division Name setter.
     * This method sets the name for the division.
     * @param divisionName The desired division name (String).*/
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    /**This is the Country ID getter.
     * This method returns the ID of the country associated with the division.
     * @return The country ID (int).*/
    public int getCountryID() {
        return countryID;
    }

    /**This is the Country ID setter.
     * This method sets the ID for the country associated with the division.
     * @param countryID The desired country ID (int).*/
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

}

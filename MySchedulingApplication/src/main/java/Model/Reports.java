package Model;

/**This is the Reports class.*/
public class Reports {

    /**This is the overloaded Reports class constructor.
     * @param year This is the year (int).
     * @param month This is the month (String).
     * @param totalThisMonth This is the total Appointments for the month (int).*/
    public Reports(int year, String month, int totalThisMonth) {
        this.year = String.valueOf(year);
        this.month = month;
        this.totalThisMonth = totalThisMonth;
    }

    /**This is the overloaded Reports class constructor.
     * @param month This is the month (String).
     * @param type This is the type of Appointment (String).
     * @param totalOfType This is the total Appointments for of the type (int).*/
    public Reports(String month, String type, int totalOfType) {
        this.month = month;
        this.type = type;
        this.totalOfType = totalOfType;
    }

    private String year;
    private String month;
    private int totalThisMonth;
    private String type;
    private int totalOfType;

    /**This is the Year getter.
     * This method returns the year as an integer.
     * @return The year (int).*/
    public int getYear() {
        return Integer.parseInt(year);
    }

    /**This is the Year setter.
     * This method sets the year using an integer.
     * @param year The desired year (int).*/
    public void setYear(int year) {
        this.year = String.valueOf(year);
    }

    /**This is the Month getter.
     * This method returns the month as a string.
     * @return The month (String).*/
    public String getMonth() {
        return month;
    }

    /**This is the Month setter.
     * This method sets the month using a string.
     * @param month The desired month (String).*/
    public void setMonth(String month) {
        this.month = month;
    }

    /**This is the Total This Month getter.
     * This method returns the total for this month.
     * @return The total for this month (int).*/
    public int getTotalThisMonth() {
        return totalThisMonth;
    }

    /**This is the Total This Month setter.
     * This method sets the total for this month.
     * @param totalThisMonth The desired total for this month (int).*/
    public void setTotalThisMonth(int totalThisMonth) {
        this.totalThisMonth = totalThisMonth;
    }

    /**This is the Type getter.
     * This method returns the type.
     * @return The type (String).*/
    public String getType() {
        return type;
    }

    /**This is the Type setter.
     * This method sets the type.
     * @param type The desired type (String).*/
    public void setType(String type) {
        this.type = type;
    }

    /**This is the Total of Type getter.
     * This method returns the total of the specified type.
     * @return The total of the specified type (int).*/
    public int getTotalOfType() {
        return totalOfType;
    }

    /**This is the Total of Type setter.
     * This method sets the total of the specified type.
     * @param totalOfType The desired total of the specified type (int).*/
    public void setTotalOfType(int totalOfType) {
        this.totalOfType = totalOfType;
    }

}

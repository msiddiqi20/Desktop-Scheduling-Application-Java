package Model;

/**This is the Custom Reports class.*/
public class CustomReports {

    /**This is the Custom Reports class constructor.
     * @param division This is the name of the division.
     * @param totalCustomers This is the total customers based in that division.*/
    public CustomReports(String division, int totalCustomers) {
        this.division = division;
        this.totalCustomers = totalCustomers;
    }

    private String division;
    private int totalCustomers;

    /**This is the Division getter.
     * This method returns the division.
     * @return The division (String).*/
    public String getDivision() {
        return division;
    }

    /**This is the Division setter.
     * This method sets the division.
     * @param division The desired division (String).*/
    public void setDivision(String division) {
        this.division = division;
    }

    /**This is the Total Customers getter.
     * This method returns the total number of customers.
     * @return The total number of customers (int).*/
    public int getTotalCustomers() {
        return totalCustomers;
    }

    /**This is the Total Customers setter.
     * This method sets the total number of customers.
     * @param totalCustomers The desired total number of customers (int).*/
    public void setTotalCustomers(int totalCustomers) {
        this.totalCustomers = totalCustomers;
    }

}

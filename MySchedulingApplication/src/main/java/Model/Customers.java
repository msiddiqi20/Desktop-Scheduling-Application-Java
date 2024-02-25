package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**This is the Customers class.*/
public class Customers {

    /**This is the Customers class constructor.
     @param customerID The customer ID (int).
     @param customerName The customer name (String).
     @param address The customer address (String).
     @param postalCode The customer postal code (String).
     @param phoneNumber The customer phone number (String).
     @param createDate The date when the customer was created (String).
     @param createdBy The user who created the customer (String).
     @param last_update The last update date of the customer (String).
     @param lastUpdatedBy The user who last updated the customer (String).
     @param divisionID The ID of the division associated with the customer (int).*/
    public Customers(int customerID,
                     String customerName,
                     String address,
                     String postalCode,
                     String phoneNumber,
                     String createDate,
                     String createdBy,
                     String last_update,
                     String lastUpdatedBy,
                     int divisionID) {

        this.customerID = customerID;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.last_update = last_update;
        this.lastUpdatedBy = lastUpdatedBy;
        this.divisionID = divisionID;

    }

    private int customerID;
    private String customerName;
    private String address;
    private String postalCode;
    private String phoneNumber;
    private String createDate;
    private String createdBy;
    private String last_update;
    private String lastUpdatedBy;
    private int divisionID;
    private ObservableList<Appointments> myAppointments = FXCollections.observableArrayList();

    /**This is the Customer ID getter.
     * This method returns the ID of the customer.
     * @return The customer ID (int).*/
    public int getCustomerID() {
        return customerID;
    }

    /**This is the Customer ID setter.
     * This method sets the ID for the customer.
     * @param customerID The desired customer ID (int).*/
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**This is the Customer Name getter.
     * This method returns the name of the customer.
     * @return The customer name (String).*/
    public String getCustomerName() {
        return customerName;
    }

    /**This is the Customer Name setter.
     * This method sets the name for the customer.
     * @param customerName The desired customer name (String).*/
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**This is the Address getter.
     * This method returns the address of the customer.
     * @return The customer address (String).*/
    public String getAddress() {
        return address;
    }

    /**This is the Address setter.
     * This method sets the address for the customer.
     * @param address The desired customer address (String).*/
    public void setAddress(String address) {
        this.address = address;
    }

    /**This is the Postal Code getter.
     * This method returns the postal code of the customer.
     * @return The customer postal code (String).*/
    public String getPostalCode() {
        return postalCode;
    }

    /**This is the Postal Code setter.
     * This method sets the postal code for the customer.
     * @param postalCode The desired customer postal code (String).*/
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**This is the Phone Number getter.
     * This method returns the phone number of the customer.
     * @return The customer phone number (String).*/
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**This is the Phone Number setter.
     * This method sets the phone number for the customer.
     * @param phoneNumber The desired customer phone number (String).*/
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**This is the Create Date getter.
     * This method returns the creation date of the customer.
     * @return The customer create date (String).*/
    public String getCreateDate() {
        return createDate;
    }

    /**This is the Create Date setter.
     * This method sets the creation date for the customer.
     * @param createDate The desired customer create date (String).*/
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**This is the Created By getter.
     * This method returns the "created by" value of the customer.
     * @return The "created by" value (String).*/
    public String getCreatedBy() {
        return createdBy;
    }

    /**This is the Created By setter.
     * This method sets the "created by" value for the customer.
     * @param createdBy The desired "created by" value (String).*/
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**This is the Last Update getter.
     * This method returns the last update date of the customer.
     * @return The customer last update date (String).*/
    public String getLast_update() {
        return last_update;
    }

    /**This is the Last Update setter.
     * This method sets the last update date for the customer.
     * @param last_update The desired customer last update date (String).*/
    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }

    /**This is the Last Updated By getter.
     * This method returns the "last updated by" value of the customer.
     * @return The "last updated by" value (String).*/
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**This is the Last Updated By setter.
     * This method sets the "last updated by" value for the customer.
     * @param lastUpdatedBy The desired "last updated by" value (String).*/
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**This is the Division ID getter.
     * This method returns the ID of the division associated with the customer.
     * @return The division ID (int).*/
    public int getDivisionID() {
        return divisionID;
    }

    /**This is the Division ID setter.
     * This method sets the ID for the division associated with the customer.
     * @param divisionID The desired division ID (int).*/
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    /**This is the MyAppointments getter.
     * This method returns the list of appointments associated with the customer.
     * @return The list of appointments ObservableList(Appointments).*/
    public ObservableList<Appointments> getMyAppointments() {
        return myAppointments;
    }

    /**This method adds an appointment to the list of appointments associated with the customer.
     * @param appointment The appointment to be added (Appointments).*/
    public void addMyAppointments(Appointments appointment) {
        myAppointments.add(appointment);
    }

}

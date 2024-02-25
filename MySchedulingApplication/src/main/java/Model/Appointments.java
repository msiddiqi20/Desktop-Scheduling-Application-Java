package Model;

/**This is the Appointments class.*/
public class Appointments {

    /**This is the Appointments class constructor.
     * @param appointmentID This is the Appointment ID (int).
     * @param title This is the Appointment Title (String).
     * @param description This is the Appointment Description (String).
     * @param location This is the Appointment Location (String).
     * @param type This is the Appointment Type (String).
     * @param startDateTime This is the Appointment Start Date-Time (YYYY-MM-DD HH:MM:SS) (String).
     * @param endDateTime This is the Appointment End Date-Time (YYYY-MM-DD HH:MM:SS) (String).
     * @param createDate This is the Date-Time the Appointment was created (YYYY-MM-DD HH:MM:SS) (String).
     * @param createdBy This is the User that created the Appointment (String).
     * @param lastUpdate his is the Date-Time the Appointment was last updated (YYYY-MM-DD HH:MM:SS) (String).
     * @param lastUpdatedBy This is the User that last updated the Appointment (String).
     * @param customerID This is the Customer ID of the customer the appointment is with (int).
     * @param userID This is the User ID of the employee the Appointment is with (int).
     * @param contact This is the name of the Contact for the Appointment (String).*/
    public Appointments(int appointmentID,
                        String title,
                        String description,
                        String location,
                        String type,
                        String startDateTime,
                        String endDateTime,
                        String createDate,
                        String createdBy,
                        String lastUpdate,
                        String lastUpdatedBy,
                        int customerID,
                        int userID,
                        String contact) {

        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.customerID = customerID;
        this.userID = userID;
        this.contact = contact;

    }

    private int appointmentID;
    private String title;
    private String description;
    private String location;
    private String type;
    private String startDateTime;
    private String endDateTime;
    private String createDate;
    private String createdBy;
    private String lastUpdate;
    private String lastUpdatedBy;
    private int customerID;
    private int userID;
    private String contact;


    /**This is the Appointment ID getter.
     * This method returns the ID of the Appointment.
     * @return Returns the Appointment ID (int).*/
    public int getAppointmentID() {
        return appointmentID;
    }

    /**This is the Appointment ID setter.
     * This method sets the ID for the Appointment.
     * @param appointmentID This is the desired Appointment ID (int).*/
    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    /**This is the Title getter.
     * This method returns the title of the Appointment.
     * @return Returns the title of the Appointment (String).*/
    public String getTitle() {
        return title;
    }

    /**This is the Title setter.
     * This method sets the title for the Appointment.
     * @param title This is the desired title of the Appointment (String).*/
    public void setTitle(String title) {
        this.title = title;
    }

    /**This is the Description getter.
     * This method returns the description of the Appointment.
     * @return Returns the description of the Appointment (String).*/
    public String getDescription() {
        return description;
    }

    /**This is the Description setter.
     * This method sets the description for the Appointment.
     * @param description This is the desired description of the Appointment (String).*/
    public void setDescription(String description) {
        this.description = description;
    }

    /**This is the Location getter.
     * This method returns the location of the Appointment.
     * @return Returns the location of the Appointment (String).*/
    public String getLocation() {
        return location;
    }

    /**This is the Location setter.
     * This method sets the location for the Appointment.
     * @param location This is the desired location of the Appointment (String).*/
    public void setLocation(String location) {
        this.location = location;
    }

    /**This is the Type getter.
     * This method returns the type of the Appointment.
     * @return Returns the type of the Appointment (String).*/
    public String getType() {
        return type;
    }

    /**This is the Type setter.
     * This method sets the type for the Appointment.
     * @param type This is the desired type of the Appointment (String).*/
    public void setType(String type) {
        this.type = type;
    }

    /**This is the Start Date and Time getter.
     * This method returns the start date and time of the Appointment.
     * @return Returns the start date and time of the Appointment (String).*/
    public String getStartDateTime() {
        return startDateTime;
    }

    /**This is the Start Date and Time setter.
     * This method sets the start date and time for the Appointment.
     * @param startDateTime This is the desired start date and time of the Appointment (String).*/
    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    /**This is the End Date and Time getter.
     * This method returns the end date and time of the Appointment.
     * @return Returns the end date and time of the Appointment (String).*/
    public String getEndDateTime() {
        return endDateTime;
    }

    /**This is the End Date and Time setter.
     * This method sets the end date and time for the Appointment.
     * @param endDateTime This is the desired end date and time of the Appointment (String).*/
    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    /**This is the Create Date getter.
     * This method returns the creation date of the Appointment.
     * @return Returns the creation date of the Appointment (String).*/
    public String getCreateDate() {
        return createDate;
    }

    /**This is the Create Date setter.
     * This method sets the creation date for the Appointment.
     * @param createDate This is the desired creation date of the Appointment (String).*/
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**This is the Created By getter.
     * This method returns the creator of the Appointment.
     * @return Returns the creator of the Appointment (String).*/
    public String getCreatedBy() {
        return createdBy;
    }

    /**This is the Created By setter.
     * This method sets the creator for the Appointment.
     * @param createdBy This is the desired creator of the Appointment (String).*/
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**This is the Last Update getter.
     * This method returns the last update date of the Appointment.
     * @return Returns the last update date of the Appointment (String).*/
    public String getLastUpdate() {
        return lastUpdate;
    }

    /**This is the Last Update setter.
     * This method sets the last update date for the Appointment.
     * @param lastUpdate This is the desired last update date of the Appointment (String).*/
    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**This is the Last Updated By getter.
     * This method returns the last user who updated the Appointment.
     * @return Returns the last user who updated the Appointment (String).*/
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**This is the Last Updated By setter.
     * This method sets the last user who updated the Appointment.
     * @param lastUpdatedBy This is the desired last user who updated the Appointment (String).*/
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**This is the Customer ID getter.
     * This method returns the ID of the customer associated with the Appointment.
     * @return Returns the customer ID associated with the Appointment (int).*/
    public int getCustomerID() {
        return customerID;
    }

    /**This is the Customer ID setter.
     * This method sets the ID of the customer associated with the Appointment.
     * @param customerID This is the desired customer ID associated with the Appointment (int).*/
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**This is the User ID getter.
     * This method returns the ID of the user associated with the Appointment.
     * @return Returns the user ID associated with the Appointment (int).*/
    public int getUserID() {
        return userID;
    }

    /**This is the User ID setter.
     * This method sets the ID of the user associated with the Appointment.
     * @param userID This is the desired user ID associated with the Appointment (int).*/
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**This is the Contact getter.
     * This method returns the contact information associated with the Appointment.
     * @return Returns the contact information associated with the Appointment (String).*/
    public String getContact() {
        return contact;
    }

    /**This is the Contact setter.
     * This method sets the contact information for the Appointment.
     * @param contact This is the desired contact information for the Appointment (String).*/
    public void setContact(String contact) {
        this.contact = contact;
    }

}

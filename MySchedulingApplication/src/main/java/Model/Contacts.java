package Model;

/**This is the Contacts class.*/
public class Contacts {

    /**This is the Contacts class constructor.
     * @param contactID This is the Contact ID for the Contact.
     * @param contactName This is the Contact Name for the Contact.*/
    public Contacts(int contactID, String contactName) {
        this.contactID = contactID;
        this.contactName = contactName;
    }

    private int contactID;
    private String contactName;

    /**This is the Contact ID getter.
     * This method returns the ID of the contact.
     * @return Returns the contact ID (int).*/
    public int getContactID() {
        return contactID;
    }

    /**This is the Contact ID setter.
     * This method sets the ID for the contact.
     * @param contactID This is the desired contact ID (int).*/
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    /**This is the Contact Name getter.
     * This method returns the name of the contact.
     * @return Returns the name of the contact (String).*/
    public String getContactName() {
        return contactName;
    }

    /**This is the Contact Name setter.
     * This method sets the name for the contact.
     * @param contactName This is the desired name of the contact (String).*/
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }


}

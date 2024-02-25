package Utilities;

import DAO.ContactsQuery;
import Model.Contacts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**This is the abstract Contacts Data class.*/
public abstract class ContactsData {

    private static ObservableList<Contacts> allContacts = FXCollections.observableArrayList();
    private static HashMap<Integer, Contacts> contactsHM = new HashMap<>();
    private static  ObservableList<String> contactNames = FXCollections.observableArrayList();

    /**This is the All Contacts getter.
     * This method returns all the Contacts.
     * @return This returns all the contacts in the form of an ObservableList (Contacts)*/
    public static ObservableList<Contacts> getAllContacts(){
       return allContacts;
    }

    /**This is the Contacts Hash Map getter.
     * This method returns the Contacts Hash Map.
     * @return This returns all the contacts in the form of an Hash Map (Integer, Contacts).*/
    public static HashMap<Integer, Contacts> getContactsHM() {
        return contactsHM;
    }

    /**This is the Contact Names getter.
     * This method returns all the Contacts Names.
     * @return This returns all the contact names in the form of an ObservableList (String)*/
    public static ObservableList<String> getContactNames(){
        return contactNames;
    }

    /**This is the All Contacts setter.
     * This method calls the Contacts Query to retrieve and set all the local contact data.
     * @throws SQLException Throws SQLException.*/
    public static void setAllContacts() throws SQLException {
        ResultSet resultSet = ContactsQuery.read();

        while(resultSet.next()){
            int contactID = resultSet.getInt("Contact_ID");
            String contactName = resultSet.getString("Contact_Name");

            allContacts.add(new Contacts(contactID, contactName));
        }

        for (Contacts contact : allContacts){
            contactsHM.put(contact.getContactID(), contact);
            contactNames.add(contact.getContactName());
        }

    }

}

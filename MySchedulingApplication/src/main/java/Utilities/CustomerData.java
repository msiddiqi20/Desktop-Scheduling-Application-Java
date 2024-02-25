package Utilities;

import DAO.CustomersQuery;
import Model.Customers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**This is the abstract Customer Data class.*/
public abstract class CustomerData {

    private static ObservableList<Customers> allCustomers = FXCollections.observableArrayList();
    private static HashMap<Integer, Customers> customersHM = new HashMap<>();
    private static ObservableList <String> customerIDs = FXCollections.observableArrayList();

    /**This is the All Customers getter.
     * This method returns all the Customers.
     * @return This returns all the customers in the form of an ObservableList (Customers)*/
    public static ObservableList<Customers> getAllCustomers() {
        return allCustomers;
    }

    /**This is the Customers Hash Map getter.
     * This method returns the Customers Hash Map.
     * @return This returns all the customers in the form of a Hash Map (Integer, Customers).*/
    public static HashMap<Integer, Customers> getCustomersHM(){
        return customersHM;
    }

    /**This is the Customer IDs getter.
     * This method returns all the Customer IDs.
     * @return This returns all the customer ids in the form of an ObservableList (String)*/
    public static ObservableList <String> getCustomerIDs(){
        return customerIDs;
    }

    /**This is the All Customers setter.
     * This method calls the Customers Query to retrieve and set all the local customer data.
     * @throws SQLException Throws SQLException.*/
    public static void setAllCustomers() throws SQLException {

        ResultSet resultSet = CustomersQuery.read();

        while(resultSet.next()){

            int customerID = resultSet.getInt("Customer_ID");
            String customerName = resultSet.getString("Customer_Name");
            String address = resultSet.getString("Address");
            String postalCode = resultSet.getString("Postal_Code");
            String phoneNumber = resultSet.getString("Phone");
            String createDate = resultSet.getString("Create_Date");
            String createdBy = resultSet.getString("Created_By");
            String last_update = resultSet.getString("Last_Update");
            String lastUpdatedBy = resultSet.getString("Last_Updated_By");
            int divisionID = resultSet.getInt("Division_ID");

            allCustomers.add(new Customers(customerID, customerName, address, postalCode, phoneNumber, createDate,
                    createdBy, last_update, lastUpdatedBy, divisionID));

        }

        for (Customers customer : allCustomers) {

            customersHM.put(customer.getCustomerID(), customer);
            customerIDs.add(String.valueOf(customer.getCustomerID()));

        }

    }

}

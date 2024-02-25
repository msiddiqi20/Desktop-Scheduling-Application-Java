package DAO;

import Model.Customers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**This is the abstract Customers Query Class.*/
public abstract class CustomersQuery {

    /**This is the create method for the Customers Query Class.
     * This method creates a customer record in the mySQL database using an SQL Query.
     * @param customer This the customer object you would like to create a record of.
     * @return Returns the number of rows effected (int).
     * @throws SQLException Throws SQLException.*/
    public static int create(Customers customer) throws SQLException {

        String sql = "INSERT INTO CUSTOMERS (Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, " +
                "Last_Update, Last_Updated_By, Division_ID) VALUES(?, ?, ?, ?, NOW(), ?, NOW(), ?, ?)";

        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        preparedStatement.setString(1, customer.getCustomerName());
        preparedStatement.setString(2, customer.getAddress());
        preparedStatement.setString(3, customer.getPostalCode());
        preparedStatement.setString(4, customer.getPhoneNumber());
        preparedStatement.setString(5, customer.getCreatedBy());
        preparedStatement.setString(6, customer.getLastUpdatedBy());
        preparedStatement.setInt(7, customer.getDivisionID());
        int rowsEffected = preparedStatement.executeUpdate();
        return rowsEffected;

    }

    /**This is the read method for the Customers Query Class.
     * This method reads all the customer records in the mySQL database using an SQL Query.
     * @return Returns a Result Set containing all customers ordered by customer id.
     * @throws SQLException Throws SQLException.*/
    public static ResultSet read() throws SQLException {

        String sql = "SELECT * FROM CUSTOMERS ORDER BY Customer_ID";

        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;

    }

    /**This is the update method for the Customers Query Class.
     * This method updates the customer record in the mySQL database using an SQL Query.
     * @param customer This the customer object you would like to update the record of.
     * @return Returns the number of rows effected (int).
     * @throws SQLException Throws SQLException.*/
    public static int update(Customers customer) throws SQLException {

        String sql = "UPDATE CUSTOMERS SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, " +
                "Last_Update = NOW(), Last_Updated_By = ?, Division_ID = ? WHERE Customer_ID = ?";

        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        preparedStatement.setString(1, customer.getCustomerName());
        preparedStatement.setString(2, customer.getAddress());
        preparedStatement.setString(3, customer.getPostalCode());
        preparedStatement.setString(4, customer.getPhoneNumber());
        preparedStatement.setString(5, customer.getLastUpdatedBy());
        preparedStatement.setInt(6, customer.getDivisionID());
        preparedStatement.setInt(7, customer.getCustomerID());
        int rowsEffected = preparedStatement.executeUpdate();
        return rowsEffected;

    }

    /**This is the delete method for the Customers Query Class.
     * This method deletes the customer record in the mySQL database using an SQL Query.
     * @param customer This the customer object you would like to delete the record of.
     * @return Returns the number of rows effected (int).
     * @throws SQLException Throws SQLException.*/
    public static int delete(Customers customer) throws SQLException {

        String sql = "DELETE FROM CUSTOMERS WHERE Customer_ID = ?";

        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        preparedStatement.setInt(1, customer.getCustomerID());
        int rowsEffected = preparedStatement.executeUpdate();
        return rowsEffected;

    }
}

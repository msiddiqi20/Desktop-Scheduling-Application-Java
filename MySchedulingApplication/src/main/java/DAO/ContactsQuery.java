package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**This is the abstract Contacts Query Class.*/
public abstract class ContactsQuery {

    /**This is the read method for the Contact Query Class.
     * This method reads all the contact records in the mySQL database using an SQL Query.
     * @return Returns a Result Set containing all contacts ordered by contact id.
     * @throws SQLException Throws SQLException.*/
    public static ResultSet read() throws SQLException {

        String sql = "SELECT * FROM CONTACTS ORDER BY Contact_ID";

        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;

    }

}

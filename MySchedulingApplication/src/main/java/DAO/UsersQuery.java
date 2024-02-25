package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**This is the abstract Users Query Class.*/
public abstract class UsersQuery {

    /**This is the overloaded read method for the Users Query Class.
     * This method reads the user records that match the username and password given and returns a Result Set.
     * @param userName This is the username entered.
     * @param password This is the password entered.
     * @return If the result set is empty, this method returns 0. If the result set is populated, then this method returns the user id.
     * @throws SQLException Throws SQLException.*/
    public static int read(String userName, String password) throws SQLException {

        String sql = "SELECT * FROM USERS WHERE User_Name = ? AND Password = ?";

        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        preparedStatement.setString(1, userName);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {

            return resultSet.getInt("User_ID");

        } else {

            return 0;

        }

    }

    /**This is the overloaded read method for the Users Query Class.
     * This method reads all the user records (only the User ID, and Username) in the mySQL database using an SQL Query.
     * @return Returns a Result Set containing all users ordered by user id.
     * @throws SQLException Throws SQLException.*/
    public static ResultSet read() throws SQLException {

        String sql = "SELECT User_ID, User_Name FROM USERS ORDER BY User_ID";

        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;

    }

}

package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**This is the abstract First Level Divisions Query Class.*/
public abstract class FirstLevelDivisionsQuery {

    /**This is the read method for the First Level Divisions Query Class.
     * This method reads all the First Level Division records in the mySQL database using an SQL Query.
     * @return Returns a Result Set containing all first level divisions ordered by division id.
     * @throws SQLException Throws SQLException.*/
    public static ResultSet read() throws SQLException {

        String sql = "SELECT * FROM FIRST_LEVEL_DIVISIONS ORDER BY Division_ID";

        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;

    }

}

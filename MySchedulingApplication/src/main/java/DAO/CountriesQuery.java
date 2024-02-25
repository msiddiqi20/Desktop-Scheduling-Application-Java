package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**This is the abstract Countries Query Class.*/
public abstract class CountriesQuery {

    /**This is the read method for the Countries Query Class.
     * This method reads all the country records in the mySQL database using an SQL Query.
     * @return Returns a Result Set containing all countries ordered by country id.
     * @throws SQLException Throws SQLException.*/
    public static ResultSet read() throws SQLException {

        String sql = "SELECT * FROM COUNTRIES ORDER BY Country_ID";

        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
        
    }

}

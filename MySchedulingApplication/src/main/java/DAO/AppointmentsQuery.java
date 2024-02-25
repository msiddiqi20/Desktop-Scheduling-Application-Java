package DAO;

import Model.Appointments;
import Model.Contacts;
import Utilities.ContactsData;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**This is the abstract Appointments Query Class.*/
public abstract class AppointmentsQuery {

    /**This is the create method for the Appointments Query Class.
     * This method creates an appointment record in the mySQL database using an SQL Query.
     * @param appointment This the appointment object you would like to create a record of.
     * @return Returns the number of rows effected (int).
     * @throws SQLException Throws SQLException.*/
    public static int create(Appointments appointment) throws SQLException {

        String sql = "INSERT INTO APPOINTMENTS (Title, Description, Location, Type, Start, End, Create_Date, " +
                "Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) " +
                "VALUES(?, ?, ?, ?, ?, ?, NOW(), ?, NOW(), ?, ?, ?, ?)";

        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        preparedStatement.setString(1, appointment.getTitle());
        preparedStatement.setString(2, appointment.getLocation());
        preparedStatement.setString(3, appointment.getDescription());
        preparedStatement.setString(4, appointment.getType());
        preparedStatement.setString(5, appointment.getStartDateTime());
        preparedStatement.setString(6, appointment.getEndDateTime());
        preparedStatement.setString(7, appointment.getCreatedBy());
        preparedStatement.setString(8, appointment.getLastUpdatedBy());
        preparedStatement.setInt(9, appointment.getCustomerID());
        preparedStatement.setInt(10, appointment.getUserID());

        for (Contacts contact : ContactsData.getAllContacts()) {

            if (appointment.getContact().equals(contact.getContactName())) {

                int correspondingContactID = contact.getContactID();
                preparedStatement.setInt(11, correspondingContactID);

                int rowsEffected = preparedStatement.executeUpdate();
                return rowsEffected;

            }

        }

        return 0;

    }

    /**This is the read method for the Appointments Query Class.
     * This method reads all the appointment records in the mySQL database using an SQL Query
     * @return Returns a Result Set containing all appointments ordered by appointment id.
     * @throws SQLException Throws SQLException.*/
    public static ResultSet read() throws SQLException {

        String sql = "SELECT * FROM APPOINTMENTS ORDER BY Appointment_ID";

        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;

    }

    /**This is the update method for the Appointments Query Class.
     * This method updates the appointment record in the mySQL database using an SQL Query.
     * @param appointment This the appointment object you would like to update the record of.
     * @return Returns the number of rows effected (int).
     * @throws SQLException Throws SQLException.*/
    public static int update(Appointments appointment) throws SQLException {

        String sql = "UPDATE APPOINTMENTS SET Title = ?, Description = ?,Location = ?, Type = ?, Start = ?, " +
                "End = ?, Last_Update = NOW(), Last_Updated_By = ?, " +
                "Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";

        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        preparedStatement.setString(1, appointment.getTitle());
        preparedStatement.setString(2, appointment.getLocation());
        preparedStatement.setString(3, appointment.getDescription());
        preparedStatement.setString(4, appointment.getType());
        preparedStatement.setString(5, appointment.getStartDateTime());
        preparedStatement.setString(6, appointment.getEndDateTime());
        preparedStatement.setString(7, appointment.getLastUpdatedBy());
        preparedStatement.setInt(8, appointment.getCustomerID());
        preparedStatement.setInt(9, appointment.getUserID());

        for (Contacts contact : ContactsData.getAllContacts()) {

            if (appointment.getContact().equals(contact.getContactName())) {

                int correspondingContactID = contact.getContactID();
                preparedStatement.setInt(10, correspondingContactID);

                preparedStatement.setInt(11, appointment.getAppointmentID());
                int rowsEffected = preparedStatement.executeUpdate();
                return rowsEffected;

            }

        }

        return 0;

    }

    /**This is the delete method for the Appointments Query Class.
     * This method deletes an appointment record in the mySQL database using an SQL Query.
     * @param appointment This the appointment object you would like to delete the record of.
     * @return Returns the number of rows effected (int).
     * @throws SQLException Throws SQLException.*/
    public static int delete(Appointments appointment) throws SQLException {

        String sql = "DELETE FROM APPOINTMENTS WHERE Appointment_ID = ?";

        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        preparedStatement.setInt(1, appointment.getAppointmentID());
        int rowsEffected = preparedStatement.executeUpdate();
        return rowsEffected;

    }

}

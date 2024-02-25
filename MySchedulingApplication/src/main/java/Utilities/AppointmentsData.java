package Utilities;

import DAO.AppointmentsQuery;
import Helper.TimeZoneConversion;
import Model.Appointments;
import Model.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

/**This is the abstract Appointments Data class.*/
public abstract class AppointmentsData {

    private static ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();

    /**This is the All Appointments getter.
     * This method returns an Observable list of Appointments.
     * @return Returns all appointments as an observable list.*/
    public static ObservableList<Appointments> getAllAppointments() {
        return allAppointments;
    }

    /**This is the All Appointments Setter.
     * This method calls the Appointments Query to retrieve and set all the local appointment data.
     * @throws SQLException Throws SQLException.*/
    public static void setAllAppointments() throws SQLException {

        ResultSet resultSet = AppointmentsQuery.read();

        while (resultSet.next()) {

            int appointmentID = resultSet.getInt("Appointment_ID");
            String title = resultSet.getString("Title");
            String description = resultSet.getString("Description");
            String location = resultSet.getString("Location");
            String type = resultSet.getString("Type");

            String startDateTime = TimeZoneConversion.UTCtoLocal(resultSet.getString("Start"));
            String endDateTime = TimeZoneConversion.UTCtoLocal(resultSet.getString("End"));

            String createDate = resultSet.getString("Create_Date");
            String createdBy = resultSet.getString("Created_By");
            String  lastUpdate = resultSet.getString("Last_Update");
            String lastUpdatedBy = resultSet.getString("Last_Updated_By");
            int customerID = resultSet.getInt("Customer_ID");
            int userID = resultSet.getInt("User_ID");
            int contactID = resultSet.getInt("Contact_ID");

            String contact = ContactsData.getContactsHM().get(contactID).getContactName();

            allAppointments.add(new Appointments(appointmentID, title, description, location, type, startDateTime,
                    endDateTime, createDate, createdBy, lastUpdate, lastUpdatedBy, customerID, userID, contact));

        }

        for (Appointments appointment : allAppointments) {

            CustomerData.getCustomersHM().get(appointment.getCustomerID()).addMyAppointments(appointment);

        }

    }

    /**This is the Appointment Notification method.
     * This method creates the notification alerts for the current user by checking all appointments that are upcoming for the user in the next 15 minutes.
     * @param currentUser The current user.
     * @return Returns the notifications for the current user (String).*/
    public static String appointmentNotification(Users currentUser) {

        String appointmentNotification = "";

        for (Appointments appointment : allAppointments) {

            if (appointment.getUserID() == currentUser.getUserID() && TimeZoneConversion.within15Minutes(appointment.getStartDateTime())) {

                String[] appointmentDetails = appointment.getStartDateTime().split(" ");

                appointmentNotification = appointmentNotification + "\nAppointmentID: " + String.valueOf(appointment.getAppointmentID())
                        + "     Date: " + appointmentDetails[0] + "     Time: " + appointmentDetails[1];

            }

        }

        if (appointmentNotification.equals("")) {

            return "\nNo Upcoming Appointments!";

        } else {

            return appointmentNotification;

        }

    }

}

package Helper;

import Utilities.*;

import java.sql.SQLException;

/**This is the abstract Initialize Data class.*/
public abstract class InitializeData {

    /**This is the initialize all method.
     * This method initializes all the local data by calling all the appropriate set methods from the various abstract classes stored in the utilities package.
     * @throws SQLException Throws SQLException.*/
    public static void initializeAll() throws SQLException {

        UserData.setAllUsers();
        TimeData.setAllTime();
        CalendarData.setThisWeek();
        CalendarData.setThisMonth();
        CountriesData.setAllCountries();
        FirstLevelDivisionData.setAllFirstLevelDivisions();
        ContactsData.setAllContacts();
        CustomerData.setAllCustomers();
        AppointmentsData.setAllAppointments();
        ReportsData.setMonthsOfYear();
        ReportsData.setAllReports();
        CustomReportsData.setCustomReports();

    }

}

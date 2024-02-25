package Helper;

import Utilities.AppointmentsData;
import Utilities.CustomReportsData;
import Utilities.CustomerData;
import Utilities.ReportsData;

import java.sql.SQLException;

/**This is the abstract Refresh Data class.*/
public abstract class RefreshData {

    /**This is the refresh all method.
     * This method refreshes the appropriate local data by clearing the appropriate local date and calling the appropriate set methods from the various abstract classes stored in the utilities package.
     * @throws SQLException Throws SQLException.*/
    public static void refreshAll() throws SQLException {

        CustomerData.getCustomersHM().clear();
        CustomerData.getAllCustomers().clear();
        CustomerData.getCustomerIDs().clear();
        AppointmentsData.getAllAppointments().clear();
        ReportsData.getReportsByMonth().clear();
        ReportsData.getReportsByType().clear();
        CustomReportsData.getCustomReports().clear();

        CustomerData.setAllCustomers();
        AppointmentsData.setAllAppointments();
        ReportsData.setAllReports();
        CustomReportsData.setCustomReports();

    }

}

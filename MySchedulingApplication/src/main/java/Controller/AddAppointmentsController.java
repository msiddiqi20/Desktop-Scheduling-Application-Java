package Controller;

import DAO.AppointmentsQuery;
import Helper.RefreshData;
import Helper.TimeZoneConversion;
import Model.Appointments;
import Utilities.ContactsData;
import Utilities.CustomerData;
import Utilities.TimeData;
import Utilities.UserData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

/** This is the Add Appointments Controller class, which implements the Initialization class.*/
public class AddAppointmentsController implements Initializable {

    /**This the Scene Loader method.
     * This method enables the application to load and direct to the desired scene after an event occurs.
     * @param event This is the ActionEvent object created when the desired event occurs (ActionEvent).
     * @param location This is the location of the desired scene's path file (.fxml, passed as a String).
     * @throws IOException Throws IOException.*/
    public void sceneLoader(ActionEvent event, String location) throws IOException {

        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource(location));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /**This is the Error Box method.
     * This method creates an Error Dialog Box with the desired content.
     * @param errorContentText This is the desired content to be displayed in the Error Dialog Box (String).*/
    public void errorBox(String errorContentText) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(errorContentText);
        alert.showAndWait();

    }

    @FXML
    private TextField appointmentIDField;

    @FXML
    private TextField titleField;

    @FXML
    private TextField typeField;

    @FXML
    private TextField descriptionField;

    @FXML
    private TextField locationField;

    @FXML
    private ComboBox<String> contactComboBox;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private ComboBox<String> startTimeComboBox;

    @FXML
    private ComboBox<String> endTimeComboBox;

    @FXML
    private ComboBox<String> customerIDComboBox;

    @FXML
    private ComboBox<String> userIDComboBox;

    /** This is the action taken when the "Cancel" button is pressed.
     * This method calls the Scene Loader and loads the Appointments Scene (passing the ActionEvent object and the file path of the Appointments Scene).
     * @param event This is the ActionEvent object created when the desired event occurs (ActionEvent) (Button is Pressed).
     * @throws IOException*/
    @FXML
    void cancelAppointmentAdd(ActionEvent event) throws IOException {

        sceneLoader(event, "/view/Appointments.fxml");

    }

    /** This is the action taken when the "Save" button is pressed.
     * This method gets the values entered into the text fields by the user to create a new Appointment. This method then loads the Appointments scene.
     * This method displays an Error Dialog Box if there are any empty field values.
     * This method also displays an Error Dialog Box if the appointment for the particular customer conflicts with any existing appointments.
     * This method also displays an Error Dialog Box if the appointment is outside of company business hours.
     * This method also displays another Error Dialog Box if the appointment start and end times conflict.
     * @param event This is the ActionEvent object created when the desired event occurs (ActionEvent) (Button is Pressed).
     * @throws IOException*/
    @FXML
    void saveAppointmentAdd(ActionEvent event) throws SQLException, IOException {

        try {

            String startDate = startDatePicker.getValue().toString();
            String startTime = startTimeComboBox.getValue();
            String endDate = endDatePicker.getValue().toString();
            String endTime = endTimeComboBox.getValue();

            int customerID = Integer.parseInt(customerIDComboBox.getValue());

            if (

                    LocalDate.parse(startDate).isAfter(LocalDate.parse(endDate))
                                                ||
                    LocalTime.parse(startTime).isAfter(LocalTime.parse(endTime))
                                                ||
                    LocalTime.parse(startTime).equals(LocalTime.parse(endTime))

            ) {

                errorBox("The appointment start date and time conflict with the end date and time!" +
                        " \nPlease select a different time for this appointment.");

            } else if (TimeZoneConversion.timeOverlap(startDate, startTime, endDate, endTime, customerID, -1)) {

                errorBox("This appointment overlaps another appointment with this customer!" +
                        " \nPlease select a different time for this appointment.");

            } else if (

                            !TimeZoneConversion.checkIfBusinessHours(startDate, startTime)
                                                        &&
                            !TimeZoneConversion.checkIfBusinessHours(endDate, endTime)

            ) {

                errorBox("This appointment is outside of company business hours!" +
                        " \nPlease select a different time for this appointment.");

            } else {

                int appointmentID = 0;
                String title = titleField.getText();
                String type = typeField.getText();
                String description = descriptionField.getText();
                String location = locationField.getText();

                String startDateTime = TimeZoneConversion.LocaltoUTC(startDate, startTime);
                String endDateTime = TimeZoneConversion.LocaltoUTC(endDate, endTime);

                String createDate = "";
                String createdBy = UserData.getUser().getUserName();
                String lastUpdate = "";
                String lastUpdatedBy = UserData.getUser().getUserName();
                int userID = Integer.parseInt(userIDComboBox.getValue());
                String contact = contactComboBox.getValue();

                AppointmentsQuery.create(new Appointments(appointmentID, title, type, description, location,
                        startDateTime, endDateTime, createDate, createdBy, lastUpdate, lastUpdatedBy,
                        customerID, userID, contact));

                RefreshData.refreshAll();
                sceneLoader(event, "/view/Appointments.fxml");

            }

        } catch (NullPointerException e) {

            errorBox("Some of the fields were left blank!" +
                    " \nPlease enter information into all fields.");

        }

    }

    /** This is an override Initialize method.
     * This method disables the Appointment ID text field and sets the values for the combo boxes.
     * @param url URL.
     * @param resourceBundle Resource Bundle.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        appointmentIDField.setDisable(true);
        appointmentIDField.setText("Automatically Generated");

        contactComboBox.setItems(ContactsData.getContactNames());
        startTimeComboBox.setItems(TimeData.getAllTime());
        endTimeComboBox.setItems(TimeData.getAllTime());
        customerIDComboBox.setItems(CustomerData.getCustomerIDs());
        userIDComboBox.setItems((UserData.getUserIDs()));

    }

}
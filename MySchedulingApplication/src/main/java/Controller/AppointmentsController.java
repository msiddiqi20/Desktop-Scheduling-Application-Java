package Controller;

import DAO.AppointmentsQuery;
import Helper.RefreshData;
import Model.Appointments;
import Utilities.AppointmentsData;
import Utilities.CalendarData;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

/** This is the Appointments Controller class, which implements the Initialization class.*/
public class AppointmentsController implements Initializable {

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
    private TableView<Appointments> appointmentsTV;

    @FXML
    private TableColumn<Appointments, Integer> appointmentIDCol;

    @FXML
    private TableColumn<Appointments, String> titleCol;

    @FXML
    private TableColumn<Appointments, String> descriptionCol;

    @FXML
    private TableColumn<Appointments, String> locationCol;

    @FXML
    private TableColumn<Appointments, String> contactCol;

    @FXML
    private TableColumn<Appointments, String> typeCol;

    @FXML
    private TableColumn<Appointments, String> startCol;

    @FXML
    private TableColumn<Appointments, String> endCol;

    @FXML
    private TableColumn<Appointments, Integer> customerIDCol;

    @FXML
    private TableColumn<Appointments, Integer> userIDCol;

    /** This is the action taken when the "Add" button is pressed.
     * This method calls the Scene Loader and loads the Add Appointment Scene (passing the ActionEvent object and the file path of the Add Appointment Scene).
     * @param event This is the ActionEvent object created when the desired event occurs (ActionEvent) (Button is Pressed).
     * @throws IOException*/
    @FXML
    void addAppointment(ActionEvent event) throws IOException {

        sceneLoader(event, "/view/AddAppointments.fxml");

    }

    /** This is the action taken when the "Update" button is pressed.
     * This method creates an instance of the Update Appointments Controller and calls the method toUpdateAppointment in order to pass the selected object to the Update Appointments Scene.
     * This method also displays an Error Dialog Box if no item is selected to Update.
     * @param event This is the ActionEvent object created when the desired event occurs (ActionEvent) (Button is Pressed).
     * @throws IOException*/
    @FXML
    void updateAppointment(ActionEvent event) throws IOException {

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/UpdateAppointments.fxml"));
            loader.load();

            UpdateAppointmentsController updateAppointmentsController = loader.getController();
            updateAppointmentsController.toUpdateAppointment(appointmentsTV.getSelectionModel().getSelectedItem());

            Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();

        } catch (NullPointerException e) {

            errorBox("No appointment was selected for updation!" +
                    " \nPlease select a appointment to update.");

        }

    }

    /** This is the action taken when the "Delete" button is pressed.
     * This method only Deletes the selected Appointment. A Confirmation Dialog Box is displayed to confirm deletion.
     * This method also displays an Error Dialog Box if no appointment is selected to be Deleted.
     * @param event This is the ActionEvent object created when the desired event occurs (ActionEvent) (Button is Pressed).*/
    @FXML
    void deleteAppointment(ActionEvent event) throws SQLException {


        if (appointmentsTV.getSelectionModel().getSelectedItem() == null) {

            errorBox("No appointment was selected for deletion!" +
                    " \nPlease select a appointment to delete.");

        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete");
            alert.setContentText("Appointment ID: " +  appointmentsTV.getSelectionModel().getSelectedItem().getAppointmentID()
                    + "\nType: " + appointmentsTV.getSelectionModel().getSelectedItem().getType()
                    + "\n\nAre you sure you want to delete this appointment?");
            ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Yes");
            ((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("No");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {

                AppointmentsQuery.delete(appointmentsTV.getSelectionModel().getSelectedItem());
                RefreshData.refreshAll();

            }

        }

    }

    @FXML
    private RadioButton allSortRB;

    @FXML
    private RadioButton thisMonthRB;

    @FXML
    private RadioButton thisWeekRB;

    /**This method displays all the appointments scheduled.
     * This method displays an unfiltered appointments table.
     * @param event This is the ActionEvent object created when the desired event occurs (ActionEvent) (Button is Pressed).*/
    @FXML
    void allSort(ActionEvent event) {
        appointmentsTV.setItems(AppointmentsData.getAllAppointments());
    }

    /**This method displays all the appointments scheduled in this month.
     * This method filters the list using a filtered list, predicate, and sorted list.
     * This method uses a lambda expression.
     * A filtered list is created using all the appointments and returning true for each (first lambda expression).
     * We then set the predicate using a lambda expression (second lambda expression) by taking each appointment in the filtered list and checking if the Start Date-Time is within this month.
     * @param event This is the ActionEvent object created when the desired event occurs (ActionEvent) (Button is Pressed).*/
    @FXML
    void thisMonthSort(ActionEvent event) {

        FilteredList<Appointments> appointmentsFilteredList = new FilteredList<>(AppointmentsData.getAllAppointments(), s -> true);

        appointmentsFilteredList.setPredicate(appointment -> {

                String[] splitStartDateTime = appointment.getStartDateTime().split(" ");

                if (CalendarData.getThisMonth().contains(splitStartDateTime[0])) {

                    return true;

                } else {

                    return false;

                }

        });

        SortedList<Appointments> appointmentsSortedListrtedList = new SortedList<>(appointmentsFilteredList);
        appointmentsSortedListrtedList.comparatorProperty().bind(appointmentsTV.comparatorProperty());
        appointmentsTV.setItems(appointmentsSortedListrtedList);

    }

    /**This method displays all the appointments scheduled in this week.
     * This method filters the list using a filtered list, predicate, and sorted list.
     * @param event This is the ActionEvent object created when the desired event occurs (ActionEvent) (Button is Pressed).*/
    @FXML
    void thisWeekSort(ActionEvent event) {

        FilteredList<Appointments> appointmentsFilteredList = new FilteredList<>(AppointmentsData.getAllAppointments(), s -> true);

        appointmentsFilteredList.setPredicate(appointment -> {

            String[] splitStartDateTime = appointment.getStartDateTime().split(" ");

            if (CalendarData.getThisWeek().contains(splitStartDateTime[0])) {

                return true;

            } else {

                return false;

            }

        });

        SortedList<Appointments> appointmentsSortedListrtedList = new SortedList<>(appointmentsFilteredList);
        appointmentsSortedListrtedList.comparatorProperty().bind(appointmentsTV.comparatorProperty());
        appointmentsTV.setItems(appointmentsSortedListrtedList);

    }

    @FXML
    private ToggleGroup appointmentSortTG;

    /** This is the action taken when the "Back" button is pressed.
     * This method calls the Scene Loader and loads the Main Menu Scene (passing the ActionEvent object and the file path of the Main Menu Scene).
     * @param event This is the ActionEvent object created when the desired event occurs (ActionEvent) (Button is Pressed).
     * @throws IOException*/
    @FXML
    void backToMainMenu(ActionEvent event) throws IOException {

        sceneLoader(event, "/view/MainMenu.fxml");

    }

    /** This is an override Initialize method.
     * This method sets the Appointments table with the correct fields and values. This also pre-selects the sort radio button to all.
     * @param url URL.
     * @param resourceBundle Resource Bundle.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        appointmentsTV.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        appointmentsTV.setItems(AppointmentsData.getAllAppointments());
        appointmentIDCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contact"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        userIDCol.setCellValueFactory(new PropertyValueFactory<>("userID"));

        allSortRB.setSelected(true);

    }

}
package Controller;

import Model.Appointments;
import Model.Countries;
import Model.CustomReports;
import Model.Reports;
import Utilities.*;
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
import java.util.ResourceBundle;

/** This is the Reports Controller class, which implements the Initialization class.*/
public class ReportsController implements Initializable {

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

    @FXML
    private ComboBox<String> contactComboBox;

    @FXML
    private TableView<Appointments> contactScheduleTV;

    @FXML
    private TableColumn<Appointments, Integer> appointmentIDCol;

    @FXML
    private TableColumn<Appointments, String> titleCol;

    @FXML
    private TableColumn<Appointments, String> descriptionCol;

    @FXML
    private TableColumn<Appointments, String> locationCol;

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

    /**This method displays all the appointments scheduled based on the contact selected.
     * This method filters the list using a filtered list, predicate, and sorted list.
     * @param event This is the ActionEvent object created when the desired event occurs (ActionEvent) (Button is Pressed).*/
    @FXML
    void contactSelected(ActionEvent event) {

        contactScheduleTV.setItems(AppointmentsData.getAllAppointments());

        FilteredList<Appointments> appointmentsFilteredList = new FilteredList<>(AppointmentsData.getAllAppointments(), s -> true);

        appointmentsFilteredList.setPredicate(appointment -> {

            if (appointment.getContact().equals(contactComboBox.getValue())) {

                return true;

            } else {

                return false;

            }

        });

        SortedList<Appointments> appointmentsSortedListrtedList = new SortedList<>(appointmentsFilteredList);
        appointmentsSortedListrtedList.comparatorProperty().bind(contactScheduleTV.comparatorProperty());
        contactScheduleTV.setItems(appointmentsSortedListrtedList);

    }



    @FXML
    private TableView<Reports> reportOneTV;

    @FXML
    private TableColumn<Reports, String> leftOne;

    @FXML
    private TableColumn<Reports, String> leftTwo;

    @FXML
    private TableColumn<Reports, Integer> leftThree;

    @FXML
    private RadioButton monthRB;

    @FXML
    private ComboBox<String> multiComboBox;

    @FXML
    private RadioButton typeRB;

    @FXML
    private ToggleGroup reportOneTG;

    /**This method displays the number of appointments scheduled for the year, categorized monthly.
     * This method sets the first Reports table with the correct fields and values.
     * @param event This is the ActionEvent object created when the desired event occurs (ActionEvent) (Button is Pressed).*/
    @FXML
    void selectedMonth(ActionEvent event) {

        multiComboBox.setValue("2023");
        multiComboBox.setDisable(true);

        reportOneTV.setItems(ReportsData.getReportsByMonth());

        leftOne.setText("Year");
        leftTwo.setText("Month");
        leftThree.setText("Total Appointments");

        leftOne.setCellValueFactory(new PropertyValueFactory<>("year"));
        leftTwo.setCellValueFactory(new PropertyValueFactory<>("month"));
        leftThree.setCellValueFactory(new PropertyValueFactory<>("totalThisMonth"));

    }

    /**This method displays the number of appointments scheduled for the year, categorized by type.
     * This method sets the first Reports table with the correct fields and values.
     * @param event This is the ActionEvent object created when the desired event occurs (ActionEvent) (Button is Pressed).*/
    @FXML
    void typeSelected(ActionEvent event) {

        multiComboBox.setValue(null);
        multiComboBox.setDisable(false);

        multiComboBox.setItems(ReportsData.getMonthsOfYear());

        reportOneTV.setItems(ReportsData.getReportsByType());

        leftOne.setText("Month");
        leftTwo.setText("Type");
        leftThree.setText("Total Appointments");

        leftOne.setCellValueFactory(new PropertyValueFactory<>("month"));
        leftTwo.setCellValueFactory(new PropertyValueFactory<>("type"));
        leftThree.setCellValueFactory(new PropertyValueFactory<>("totalOfType"));

    }

    /**This method displays the number of appointments scheduled in the month, based on the month selected, categorized by type.
     * This method filters the list using a filtered list, predicate, and sorted list.
     * @param event This is the ActionEvent object created when the desired event occurs (ActionEvent) (Button is Pressed).*/
    @FXML
    void comboBoxSelected(ActionEvent event) {

        FilteredList<Reports> reportsFilteredList = new FilteredList<>(ReportsData.getReportsByType(), s -> true);

        reportsFilteredList.setPredicate(report -> {

            if (report.getMonth().equals(multiComboBox.getValue())) {

                return true;

            } else {

                return false;

            }

        });

        SortedList<Reports> reportsSortedListrtedList = new SortedList<>(reportsFilteredList);
        reportsSortedListrtedList.comparatorProperty().bind(reportOneTV.comparatorProperty());
        reportOneTV.setItems(reportsSortedListrtedList);

    }

    @FXML
    private TableView<CustomReports> reportTwoTV;

    @FXML
    private TableColumn<CustomReports, String> divisionCol;

    @FXML
    private TableColumn<CustomReports, Integer> totalCustomersCol;

    @FXML
    private ComboBox<String> countryComboBox;

    /**This method displays the number of customers based in a specific province or state, based on the country selected.
     * This method filters the list using a filtered list, predicate, and sorted list.
     * @param event This is the ActionEvent object created when the desired event occurs (ActionEvent) (Button is Pressed).*/
    @FXML
    void countrySelected(ActionEvent event) {

        FilteredList<CustomReports> crFilteredList = new FilteredList<>(CustomReportsData.getCustomReports(), s -> true);

        crFilteredList.setPredicate(customReport -> {

            Countries tempCountry = null;
            
            for (Countries country : CountriesData.getAllCountries()) {

                if (countryComboBox.getValue().equals(country.getCountryName())) {

                    tempCountry = country;

                }

            }
            
            for (String division : tempCountry.getDivisions()) {

                if (customReport.getDivision().equals(division)) {

                    return true;

                }

            }
            
            return false;

        });

        SortedList<CustomReports> crSortedListrtedList = new SortedList<>(crFilteredList);
        crSortedListrtedList.comparatorProperty().bind(reportTwoTV.comparatorProperty());
        reportTwoTV.setItems(crSortedListrtedList);

    }

    /** This is the action taken when the "Back" button is pressed.
     * This method calls the Scene Loader and loads the Main Menu Scene (passing the ActionEvent object and the file path of the Main Menu Scene).
     * @param event This is the ActionEvent object created when the desired event occurs (ActionEvent) (Button is Pressed).
     * @throws IOException*/
    @FXML
    void backToMainMenu(ActionEvent event) throws IOException {

        sceneLoader(event, "/view/MainMenu.fxml");

    }

    /** This is an override Initialize method.
     * This method sets the Reports table with the correct fields and values. This also sets the combo box values.
     * @param url URL.
     * @param resourceBundle Resource Bundle.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        contactComboBox.setItems(ContactsData.getContactNames());

        reportOneTV.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        reportTwoTV.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        contactScheduleTV.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        appointmentIDCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        userIDCol.setCellValueFactory(new PropertyValueFactory<>("userID"));

        countryComboBox.setItems(CountriesData.getCountryNames());

        reportTwoTV.setItems(CustomReportsData.getCustomReports());
        divisionCol.setCellValueFactory(new PropertyValueFactory<>("division"));
        totalCustomersCol.setCellValueFactory(new PropertyValueFactory<>("totalCustomers"));

    }

}
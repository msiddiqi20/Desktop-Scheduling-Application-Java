package Controller;

import DAO.CustomersQuery;
import Helper.RefreshData;
import Model.Countries;
import Model.Customers;
import Model.FirstLevelDivisions;
import Utilities.CountriesData;
import Utilities.FirstLevelDivisionData;
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
import java.util.ResourceBundle;

/** This is the Update Customer Records Controller class, which implements the Initialization class.*/
public class UpdateCustomerRecordsController implements Initializable {

    private Customers currentCustomer;

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

    /**This method allows for the transfer of the appointment to the Update Customer Records scene.
     * This method takes the customer from the customer records scene and sets it to the local customer.
     * This method takes the fields of the customer object and pre-populates the text fields and combo boxes with the corresponding data.
     * @param customer This is the selected appointment being passed for update.*/
    public void toUpdateCustomer(Customers customer) {

        currentCustomer = customer;

        customerIDField.setText(String.valueOf(customer.getCustomerID()));
        nameField.setText(customer.getCustomerName());
        addressField.setText(customer.getAddress());
        phoneNumberField.setText(customer.getPhoneNumber());

        FirstLevelDivisions currentFirstLevelDivision = FirstLevelDivisionData.getFirstLevelDivisionsHM().get(customer.getDivisionID());
        provinceComboBox.setValue(currentFirstLevelDivision.getDivisionName());

        Countries currentCountry = CountriesData.getCountriesHM().get(currentFirstLevelDivision.getCountryID());
        countryComboBox.setValue(currentCountry.getCountryName());

        postalCodeField.setText(customer.getPostalCode());

    }

    @FXML
    private TextField customerIDField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private ComboBox<String> countryComboBox;

    @FXML
    private Label stateORprovinceLabel;

    @FXML
    private ComboBox<String> provinceComboBox;

    @FXML
    private TextField postalCodeField;

    /**This method sets the values of the Province or States combo box based on the country selected.
     * This method also sets the Province or State label based on the country selected
     * @param event This is the ActionEvent object created when the desired event occurs (ActionEvent) (Button is Pressed).*/
    @FXML
    void countrySelected(ActionEvent event) {

        if (countryComboBox.getValue().equals("U.S")){

            stateORprovinceLabel.setText("State");

        } else {

            stateORprovinceLabel.setText("Province");

        }

        for (Countries country : CountriesData.getAllCountries()){

            if (countryComboBox.getValue().equals(country.getCountryName())) {

                provinceComboBox.setItems(country.getDivisions());

            }

        }

    }

    /** This is the action taken when the "Cancel" button is pressed.
     * This method calls the Scene Loader and loads the Customer Records Scene (passing the ActionEvent object and the file path of the Customer Records Scene).
     * @param event This is the ActionEvent object created when the desired event occurs (ActionEvent) (Button is Pressed).
     * @throws IOException*/
    @FXML
    void cancelCustomerUpdate(ActionEvent event) throws IOException {

        sceneLoader(event, "/view/CustomerRecords.fxml");

    }

    /** This is the action taken when the "Save" button is pressed.
     * This method gets the values entered into the text fields by the user to update the Customer Record. This method then loads the Customer Records scene.
     * This method displays an Error Dialog Box if there are any empty field values.
     * @param event This is the ActionEvent object created when the desired event occurs (ActionEvent) (Button is Pressed).
     * @throws IOException*/
    @FXML
    void saveCustomerUpdate(ActionEvent event) throws SQLException, IOException {

        try {

            currentCustomer.setCustomerName(nameField.getText());
            currentCustomer.setAddress(addressField.getText());
            currentCustomer.setPostalCode(postalCodeField.getText());
            currentCustomer.setPhoneNumber(phoneNumberField.getText());
            currentCustomer.setLastUpdatedBy(UserData.getUser().getUserName());

            for (FirstLevelDivisions firstLevelDivision : FirstLevelDivisionData.getAllFirstLevelDivisions()){

                if (provinceComboBox.getValue().equals(firstLevelDivision.getDivisionName())){

                    currentCustomer.setDivisionID(firstLevelDivision.getDivisionID());
                    CustomersQuery.update(currentCustomer);
                    break;

                }

            }

            RefreshData.refreshAll();
            sceneLoader(event, "/view/CustomerRecords.fxml");

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

        customerIDField.setDisable(true);
        countryComboBox.setItems(CountriesData.getCountryNames());

    }

}
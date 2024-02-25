package Controller;

import DAO.CustomersQuery;
import Helper.RefreshData;
import Model.Customers;
import Utilities.CustomerData;
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

/** This is the Customer Records Controller class, which implements the Initialization class.*/
public class CustomerRecordsController implements Initializable {

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
    private TableView<Customers> customerRecordsTV;

    @FXML
    private TableColumn<Customers, Integer> customerIDCol;

    @FXML
    private TableColumn<Customers, String> nameCol;

    @FXML
    private TableColumn<Customers, String> addressCol;

    @FXML
    private TableColumn<Customers, String> postalCodeCol;

    @FXML
    private TableColumn<Customers, String> phoneNumberCol;

    @FXML
    private TableColumn<Customers, String> dateCreatedCol;

    @FXML
    private TableColumn<Customers, String> createdByCol;

    @FXML
    private TableColumn<Customers, String> lastUpdateCol;

    @FXML
    private TableColumn<Customers, String> lastUpdatedByCol;

    @FXML
    private TableColumn<Customers, Integer> divisionIDCol;

    /** This is the action taken when the "Add" button is pressed.
     * This method calls the Scene Loader and loads the Add Customer Records Scene (passing the ActionEvent object and the file path of the Add Customer Records Scene).
     * @param event This is the ActionEvent object created when the desired event occurs (ActionEvent) (Button is Pressed).
     * @throws IOException*/
    @FXML
    void addCustomer(ActionEvent event) throws IOException {

        sceneLoader(event, "/view/AddCustomerRecords.fxml");

    }

    /** This is the action taken when the "Update" button is pressed.
     * This method creates an instance of the Update Customer Records Controller and calls the method toUpdateCustomer in order to pass the selected object to the Update Customer Records Scene.
     * This method also displays an Error Dialog Box if no item is selected to Update.
     * @param event This is the ActionEvent object created when the desired event occurs (ActionEvent) (Button is Pressed).
     * @throws IOException*/
    @FXML
    void updateCustomer(ActionEvent event) throws IOException {

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/UpdateCustomerRecords.fxml"));
            loader.load();

            UpdateCustomerRecordsController updateCustomerRecordsController = loader.getController();
            updateCustomerRecordsController.toUpdateCustomer(customerRecordsTV.getSelectionModel().getSelectedItem());

            Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();

        } catch (NullPointerException e) {

            errorBox("No customer record was selected for updation!" +
                    " \nPlease select a customer record to update.");

        }

    }

    /** This is the action taken when the "Delete" button is pressed.
     * This method only Deletes the selected Customer Record if no Appointments are associated with this customer. A Confirmation Dialog Box is displayed to confirm deletion.
     * This method also displays an Error Dialog Box if no customer record is selected to be Deleted.
     * @param event This is the ActionEvent object created when the desired event occurs (ActionEvent) (Button is Pressed).*/
    @FXML
    void deleteCustomer(ActionEvent event) throws SQLException {

        if (customerRecordsTV.getSelectionModel().getSelectedItem() == null) {

            errorBox("No customer record was selected for deletion!" +
                    " \nPlease select a customer record to delete.");

        } else if (!customerRecordsTV.getSelectionModel().getSelectedItem().getMyAppointments().isEmpty()) {

            errorBox("The selected customer record cannot be deleted because it has appointments associated with it.");

        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete");
            alert.setContentText("Are you sure you want to delete this customer record?");
            ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Yes");
            ((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("No");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {

                CustomersQuery.delete(customerRecordsTV.getSelectionModel().getSelectedItem());
                RefreshData.refreshAll();

            }

        }

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
     * This method sets the Customer Records table with the correct fields and values.
     * @param url URL.
     * @param resourceBundle Resource Bundle.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        customerRecordsTV.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        customerRecordsTV.setItems(CustomerData.getAllCustomers());
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        dateCreatedCol.setCellValueFactory(new PropertyValueFactory<>("createDate"));
        createdByCol.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
        lastUpdateCol.setCellValueFactory(new PropertyValueFactory<>("last_update"));
        lastUpdatedByCol.setCellValueFactory(new PropertyValueFactory<>("lastUpdatedBy"));
        divisionIDCol.setCellValueFactory(new PropertyValueFactory<>("divisionID"));

    }

}
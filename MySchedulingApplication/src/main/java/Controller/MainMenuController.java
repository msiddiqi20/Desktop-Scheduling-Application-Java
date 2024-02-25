package Controller;

import Helper.InitializeData;
import Model.Users;
import Utilities.AppointmentsData;
import Utilities.UserData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/** This is the Main Menu Controller class, which implements the Initialization class.*/
public class MainMenuController implements Initializable {

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

    /**This is the Alert Box method.
     * This method creates a Warning Dialog Box with the desired content.
     * @param contentText This is the desired content to be displayed in the Warning Dialog Box (String).*/
    public void alertBox(String contentText) {

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Upcoming Appointments");
        alert.setContentText(contentText);
        alert.showAndWait();

    }

    /** This method allows for the transfer of the User information to the Main Menu scene.
     * This method takes the User information from the log in scene and sets it to the local user.
     * This method sets a numbered user, indicating that a user has already logged in when another attempt is made.
     * This method initializes all local data and sets the notifications for the user.
     * @param currentUser This is the current user that logged into the system.
     * @throws SQLException Throws SQLException.*/
    public void LoginToMainFirstUser(Users currentUser) throws SQLException {

        UserData.setUser(currentUser);
        UserData.setNumberedUser();
        InitializeData.initializeAll();
        notificationsLabel.setText(AppointmentsData.appointmentNotification(currentUser));

    }

    /**This method allows for the transfer of the User information to the Main Menu scene.
     * This method takes the User information from the log in scene and sets it to the local user.
     * This method is only called after the numbered user is set, indicating another user logged in before this one.
     * This method sets the notifications for the user.
     * @param currentUser This is the current user that logged into the system.*/
    public void LogintoMainNumberedUser(Users currentUser){

        UserData.setUser(currentUser);
        notificationsLabel.setText(AppointmentsData.appointmentNotification(currentUser));

    }

    /**This method alerts the user of any upcoming appointments.
     * This method uses an Warning Dialog Box to display any appointments upcoming in the next 15 minutes.*/
    public void alertUser() {

        if (!notificationsLabel.getText().equals("\nNo Upcoming Appointments!")) {

            alertBox("Upcoming Appointments: \n" + notificationsLabel.getText());

        }

    }

    @FXML
    private Label notificationsLabel;

    /** This is the action taken when the "Appointments" button is pressed at the Main Menu.
     * This method calls the Scene Loader and loads the Appointment Scene (passing the ActionEvent object and the file path of the Appointment Scene).
     * @param event This is the ActionEvent object created when the desired event occurs (ActionEvent) (Button is Pressed).
     * @throws IOException*/
    @FXML
    void gotoAppointments(ActionEvent event) throws IOException {

        sceneLoader(event, "/view/Appointments.fxml");

    }

    /** This is the action taken when the "Customer Records" button is pressed at the Main Menu.
     * This method calls the Scene Loader and loads the Customer Records Scene (passing the ActionEvent object and the file path of the Customer Records Scene).
     * @param event This is the ActionEvent object created when the desired event occurs (ActionEvent) (Button is Pressed).
     * @throws IOException*/
    @FXML
    void gotoCustomerRecords(ActionEvent event) throws IOException {

        sceneLoader(event, "/view/CustomerRecords.fxml");

    }

    /** This is the action taken when the "Reports" button is pressed at the Main Menu.
     * This method calls the Scene Loader and loads the Reports Scene (passing the ActionEvent object and the file path of the Reports Scene).
     * @param event This is the ActionEvent object created when the desired event occurs (ActionEvent) (Button is Pressed).
     * @throws IOException*/
    @FXML
    void gotoReports(ActionEvent event) throws IOException {

        sceneLoader(event, "/view/Reports.fxml");

    }

    /** This is the action taken when the "Log Out" button is pressed at the Main Menu.
     * This method calls the Scene Loader and loads the Login Scene (passing the ActionEvent object and the file path of the Login Scene).
     * @param event This is the ActionEvent object created when the desired event occurs (ActionEvent) (Button is Pressed).
     * @throws IOException*/
    @FXML
    void logOut(ActionEvent event) throws IOException {

        sceneLoader(event, "/view/Login.fxml");

    }

    /**This is an override Initialize method.
     * This method sets the notifications for the user and displays it.
     * @param url URL.
     * @param resourceBundle Resource Bundle.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        notificationsLabel.setText("");
        notificationsLabel.setText(AppointmentsData.appointmentNotification(UserData.getUser()));

    }

}
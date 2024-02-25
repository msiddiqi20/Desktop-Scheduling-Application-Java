package Controller;

import DAO.JDBC;
import DAO.UsersQuery;
import Model.Users;
import Utilities.UserData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

/** This is the Login Controller class, which implements the Initialization class.*/
public class LoginController implements Initializable {

    private ResourceBundle resourceBundle;

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
    private Label welcomeBackLabel;

    @FXML
    private Label messageLabel;

    @FXML
    private Label usernameLabel;

    @FXML
    private TextField usernameField;

    @FXML
    private Label passwordLabel;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginLabel;

    @FXML
    private Label locationLabel;

    @FXML
    private Label zoneIDLabel;

    @FXML
    private Button exitLabel;

    /**This is attempt login method.
     * This method attempts to log in the user by checking the database to see if a matching username-password pair exists.
     * This method writes all log in activity to the login_activity.txt file.
     * This method sends the user information for data initialization.
     * @param event This is the ActionEvent object created when the desired event occurs (ActionEvent) (Button is Pressed).
     * @throws SQLException
     * @throws IOException*/
    @FXML
    void attemptLogin(ActionEvent event) throws SQLException, IOException {

        int userID = UsersQuery.read(usernameField.getText(), passwordField.getText());
        BufferedWriter writeActivity = new BufferedWriter(new FileWriter("src/main/login_activity.txt", true));

        if (userID != 0) {

            Users currentUser = new Users(userID, usernameField.getText());

            writeActivity.append("\nUser Name: " + usernameField.getText() + "  |  Date: " + LocalDate.now().toString() + "  |  Status: SUCCESSFUL");
            writeActivity.close();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/MainMenu.fxml"));
            loader.load();

            MainMenuController mainMenuController = loader.getController();

            if (UserData.getNumberedUser() == 0) {

                mainMenuController.LoginToMainFirstUser(currentUser);

            } else {

                mainMenuController.LogintoMainNumberedUser(currentUser);

            }

            Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
            mainMenuController.alertUser();

        } else {

            writeActivity.append("\nUser Name: " + usernameField.getText() + "  |  Date: " + LocalDate.now().toString() + "  |  Status: FAILED");
            writeActivity.close();

            errorBox(resourceBundle.getString("ErrorMessage"));

        }

    }

    /**This is the action taken when the "Exit" button is pressed at the Log-In Screen.
     * This method exits and closes the Application safely while disconnecting the database.
     * @param event This is the ActionEvent object created when the desired event occurs (ActionEvent) (Button is Pressed).*/
    @FXML
    void exitApplication(ActionEvent event) {

        JDBC.closeConnection();
        System.exit(0);

    }

    /**This is an override Initialize method.
     * This method determines the ZoneID of the user and displays it.
     * This method changes the language of the application based on the users system settings through the use of the resource bundle.
     * @param url URL.
     * @param resourceBundle Resource Bundle.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        zoneIDLabel.setText(String.valueOf(ZoneId.systemDefault()));

        if (Locale.getDefault().getLanguage() == "fr") {

            Locale locale = new Locale("fr", "CA");
            resourceBundle = ResourceBundle.getBundle("/Bundles/FrenchBundle", locale);

        } else {

            Locale locale = new Locale("en", "US");
            resourceBundle = ResourceBundle.getBundle("/Bundles/EnglishBundle", locale);

        }

        this.resourceBundle = resourceBundle;

        welcomeBackLabel.setText(resourceBundle.getString("WelcomeMessage"));
        messageLabel.setText(resourceBundle.getString("DetailsMessage"));
        usernameLabel.setText(resourceBundle.getString("UsernameLabel"));
        passwordLabel.setText(resourceBundle.getString("PasswordLabel"));
        loginLabel.setText(resourceBundle.getString("LoginLabel"));
        locationLabel.setText(resourceBundle.getString("LocationLabel"));
        exitLabel.setText(resourceBundle.getString("ExitLabel"));

    }

}
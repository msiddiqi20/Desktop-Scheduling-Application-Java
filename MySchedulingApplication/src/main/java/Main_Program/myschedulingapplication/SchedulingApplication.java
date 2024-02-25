package Main_Program.myschedulingapplication;

import DAO.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**The Scheduling Application class, which extends the Application class.*/
public class SchedulingApplication extends Application {

    /**The start method.
     * This method starts the application.
     * @param stage Stage.
     * @throws IOException Throws IOException.*/
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(SchedulingApplication.class.getResource("/View/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setTitle("Scheduling Application!");
        stage.setScene(scene);
        stage.show();

    }

    /**The main method of the application.
     * This method connects to the database and then launches the application. Upon closing the application the connection is terminated.
     * @param args args*/
    public static void main(String[] args) {

        JDBC.openConnection();
        launch();
        JDBC.closeConnection();

    }
}
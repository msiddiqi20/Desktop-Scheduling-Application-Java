module Main_Program.myschedulingapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens Main_Program.myschedulingapplication to javafx.fxml;
    exports Main_Program.myschedulingapplication;
    exports Controller;
    opens Controller to javafx.fxml;

    opens Model to javafx.fxml;
    exports Model;
}
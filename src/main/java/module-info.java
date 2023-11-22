module com.example.cmpt365_project3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cmpt365_project3 to javafx.fxml;
    exports com.example.cmpt365_project3;
}
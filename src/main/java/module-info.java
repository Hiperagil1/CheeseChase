module com.example.cheesechase {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.cheesechase to javafx.fxml;
    exports com.example.cheesechase;
    exports view;
    opens view to javafx.fxml;
    exports model.repository;
    exports model;
}
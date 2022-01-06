module com.project.passwordmanager {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.project.passwordmanager to javafx.fxml;
    exports com.project.passwordmanager;
    exports com.project.passwordmanager.helper;
    opens com.project.passwordmanager.helper to javafx.fxml;
}
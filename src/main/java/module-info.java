module com.example.mutuelle {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires java.sql;

    opens com.example.mutuelle.controllers to javafx.base, javafx.fxml;
    exports com.example.mutuelle;
}
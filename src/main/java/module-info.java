module com.example.mutuelle {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires java.sql;
    requires bcrypt;
    requires java.mail;
    requires apache.log4j.extras;

    opens com.example.mutuelle.controllers to javafx.base, javafx.fxml;
    exports com.example.mutuelle;
}
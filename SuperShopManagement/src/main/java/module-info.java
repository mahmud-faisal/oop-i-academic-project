module com.example.supershopmanagement {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.desktop;

    opens com.example.supershopmanagement to javafx.fxml;
    exports com.example.supershopmanagement;
}
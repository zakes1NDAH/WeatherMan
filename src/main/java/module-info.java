module com.example.weatherman {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;
    requires org.json;
    requires json.simple;

    opens com.example.weatherman to javafx.fxml;
    exports com.example.weatherman;
}
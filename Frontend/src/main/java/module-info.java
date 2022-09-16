module com.example.frontend {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.net.http;

    opens com.example.frontend to javafx.fxml;
    exports com.example.frontend;
}
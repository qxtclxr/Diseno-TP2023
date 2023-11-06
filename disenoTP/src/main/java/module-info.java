module org.openjfx.disenoTP {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.openjfx.disenoTP to javafx.fxml;
    exports org.openjfx.disenoTP;
}

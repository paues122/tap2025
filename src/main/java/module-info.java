module org.example.tap2025 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.tap2025 to javafx.fxml;
    requires org.kordamp.bootstrapfx.core;
    exports org.example.tap2025;
    requires mysql.connector.j;
    requires java.sql;
    requires jdk.accessibility;
    opens org.example.tap2025.modelos;
}
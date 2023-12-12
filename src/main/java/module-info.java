module com.example.informationfinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;
    requires java.sql;


    opens com.example.informationfinal to javafx.fxml;
    exports com.example.informationfinal;
}
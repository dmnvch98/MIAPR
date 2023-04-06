module com.example.miapr {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.miapr to javafx.fxml;
    exports com.example.miapr;
}
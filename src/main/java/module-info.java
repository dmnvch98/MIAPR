module com.example.miapr {
    requires javafx.controls;
    requires javafx.fxml;

    exports com.example.miapr.iprFirst;
    opens com.example.miapr.iprFirst to javafx.fxml;
    exports com.example.miapr.iprFirst.model;
    opens com.example.miapr.iprFirst.model to javafx.fxml;
    exports com.example.miapr.iprFirst.algorithms;
    opens com.example.miapr.iprFirst.algorithms to javafx.fxml;
    exports com.example.miapr.iprFirst.utils;
    opens com.example.miapr.iprFirst.utils to javafx.fxml;
}
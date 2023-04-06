package com.example.miapr;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.List;


public class HelloApplication extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        Visualization visualization = new Visualization();

        // Отображение сцены
        primaryStage.setTitle("Clusters");
        primaryStage.setScene(visualization.showClusters(10000));
        primaryStage.show();
    }
}
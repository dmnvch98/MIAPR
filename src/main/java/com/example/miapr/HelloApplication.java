package com.example.miapr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class HelloApplication extends Application {

    public static void main(String[] args) {
        launch();
    }

//    @Override
//    public void start(Stage primaryStage) {
//        Visualization visualization = new Visualization();
//
//        // Отображение сцены
//        primaryStage.setTitle("Clusters");
//        primaryStage.setScene(visualization.showClusters(10000));
//        primaryStage.show();
//    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 600);
        stage.setTitle("Clusters");
        stage.setScene(scene);
        stage.show();
    }
}
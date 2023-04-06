package com.example.miapr;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Visualization {
    public Scene showClusters(int pointsCount) {
        List<DataPoint> dataPoints = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < pointsCount; i++) {
            DataPoint dataPoint = new DataPoint(random.nextInt(501), random.nextInt(501));
            dataPoints.add(dataPoint);
        }

        KMeans kMeans = new KMeans(5, 100, dataPoints);
        List<Cluster> clusters = kMeans.run();

        Group root = new Group();
        Scene scene = new Scene(root, 500, 500);

        for (Cluster cluster : clusters) {
            Color color = cluster.getColor();

            for (DataPoint point : cluster.getPoints()) {
                Circle circle = new Circle(point.getX(), point.getY(), 2, color);
                root.getChildren().add(circle);
            }
        }

        return scene;
    }
}

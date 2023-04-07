package com.example.miapr;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Visualization {
    private final int elementsQty;
    private final int iterationQty;
    private final int clustersQty;

    public Visualization(int elementsQty, int iterationQty, int clustersQty) {
        this.elementsQty = elementsQty;
        this.iterationQty = iterationQty;
        this.clustersQty = clustersQty;
    }

    public List<Circle> showClusters() {
        List<DataPoint> dataPoints = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < elementsQty; i++) {
            DataPoint dataPoint = new DataPoint(random.nextInt(501), random.nextInt(501));
            dataPoints.add(dataPoint);
        }

        KMeans kMeans = new KMeans(clustersQty, iterationQty, dataPoints);
        List<Cluster> clusters = kMeans.run();
//
//        Group root = new Group();
//        Scene scene = new Scene(root, 500, 500);
//
//        for (Cluster cluster : clusters) {
//            Color color = cluster.getColor();
//
//            for (DataPoint point : cluster.getPoints()) {
//                Circle circle = new Circle(point.getX(), point.getY(), 2, color);
//                root.getChildren().add(circle);
//            }
//        }
//
//        return scene;
        List<Circle> circles = new ArrayList<>();
        for (Cluster cluster : clusters) {
            Color color = cluster.getColor();

            for (DataPoint point : cluster.getPoints()) {
                Circle circle = new Circle(point.getX(), point.getY(), 2, color);
                circles.add(circle);
            }
        }

        return circles;
    }
}

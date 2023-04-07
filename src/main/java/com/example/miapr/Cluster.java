package com.example.miapr;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Cluster {

    private final int id; // идентификатор кластера
    private DataPoint center; // центр кластера
    private List<DataPoint> points; // список точек в кластере
    private Color color;

    public Cluster(int id, DataPoint center, Color color) {
        this.id = id;
        this.center = center;
        this.points = new ArrayList<>();
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public DataPoint getCenter() {
        return center;
    }

    public void setCenter(DataPoint center) {
        this.center = center;
    }

    public List<DataPoint> getPoints() {
        return points;
    }

    public void addPoint(DataPoint point) {
        points.add(point);
    }

    public void clearPoints() {
        points.clear();
    }

    public Color getColor() {
        return color;
    }

    public void setPoints(List<DataPoint> points) {
        this.points = points;
    }
}
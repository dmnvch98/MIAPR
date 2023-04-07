package com.example.miapr.iprFirst.model;

import java.util.List;

public class DataPoint {
    private final double x;
    private final double y;

    public DataPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public static DataPoint mean(List<DataPoint> points) {
        double sumX = 0;
        double sumY = 0;
        int size = points.size();

        for (DataPoint point : points) {
            sumX += point.getX();
            sumY += point.getY();
        }

        double meanX = sumX / size;
        double meanY = sumY / size;

        return new DataPoint(meanX, meanY);
    }

    public double distanceTo(DataPoint other) {
        double dx = this.x - other.getX();
        double dy = this.y - other.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }
}
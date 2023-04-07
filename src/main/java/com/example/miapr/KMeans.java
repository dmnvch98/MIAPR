package com.example.miapr;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KMeans {
    private final int k; // количество кластеров
    private final int maxIterations; // максимальное количество итераций
    private final List<DataPoint> dataPoints; // список точек данных
    private List<Cluster> clusters; // список кластеров
    private int iteration = 0;
    private List<Color> colors;

    public KMeans(int k, int maxIterations, List<DataPoint> dataPoints) {
        this.k = k;
        this.maxIterations = maxIterations;
        this.dataPoints = dataPoints;
    }

    public List<Cluster> run() {
        colors = List.of(Color.RED, Color.BROWN, Color.YELLOW, Color.GREEN, Color.GRAY, Color.BLUE, Color.CADETBLUE);
        // Инициализируем кластеры случайными точками данных
        initializeClusters();

        boolean converged = false;

        while (iteration < maxIterations && !converged) {
            // Присваиваем каждую точку данных ближайшему кластеру
            assignPointsToClusters();

            // Пересчитываем центр каждого кластера
            converged = recalculateClusterCenters();

            iteration++;
        }
        return clusters;
    }

    private void initializeClusters() {
        clusters = new ArrayList<>();

        // Используем генератор случайных чисел для выбора k случайных точек из данных
        Random random = new Random();
        for (int i = 0; i < k; i++) {
            //генерируется случайный индекс в диапазоне количества точек
            int randomIndex = random.nextInt(dataPoints.size());
            //Из массива точек извлекается точка по сгенерированному индексу
            DataPoint randomPoint = dataPoints.get(randomIndex);
            //Создается кластер, где наша точка указывается как центр
            Cluster cluster = new Cluster(i, randomPoint, colors.get(i));
            clusters.add(cluster);
        }
    }

    private void assignPointsToClusters() {
        for (DataPoint dataPoint : dataPoints) {
            double minDistance = Double.MAX_VALUE;
            int clusterId = -1;
            for (Cluster cluster : clusters) {
                double distance = dataPoint.distanceTo(cluster.getCenter());
                if (distance < minDistance) {
                    minDistance = distance;
                    clusterId = cluster.getId();
                }
            }
            if (clusterId >= 0) {
                clusters.get(clusterId).addPoint(dataPoint);
            }
        }
    }

    private boolean recalculateClusterCenters() {
        boolean converged = true;

        for (Cluster cluster : clusters) {
            if (cluster.getPoints().isEmpty()) {
                // Если кластер пуст, пропускаем его
                continue;
            }

            // Сохраняем предыдущее положение центра кластера
            DataPoint oldCenter = cluster.getCenter();

            // Рассчитываем новое положение центра кластера как среднее всех точек в кластере
            DataPoint newCenter = DataPoint.mean(cluster.getPoints());

            // Обновляем центр кластера
            cluster.setCenter(newCenter);

            // Проверяем, сходится ли положение центра кластера
            if (!oldCenter.equals(newCenter)) {
                converged = false;
            }
            // Очищаем список точек в кластере перед следующей итерацией если это не последняя итерация
            if (iteration != maxIterations - 1) {
                cluster.clearPoints();
            }
        }


        return converged;
    }
}

class DataPoint {
    private double x;
    private double y;

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

class Cluster {

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
}

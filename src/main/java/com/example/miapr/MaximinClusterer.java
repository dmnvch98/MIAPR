package com.example.miapr;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MaximinClusterer {
    private final int k; // количество кластеров
    private List<DataPoint> data; // данные для кластеризации
    private List<Cluster> clusters; // кластеры

    public MaximinClusterer(int k, List<DataPoint> data) {
        this.k = k;
        this.data = data;
        this.clusters = new ArrayList<>();
    }

    private final List<Color> colors = List.of(
        Color.RED,
        Color.BROWN,
        Color.GREENYELLOW,
        Color.GREEN,
        Color.GRAY,
        Color.BLUE,
        Color.DARKRED,
        Color.DARKGRAY,
        Color.DARKBLUE,
        Color.CADETBLUE,
        Color.CYAN,
        Color.DEEPSKYBLUE,
        Color.HOTPINK,
        Color.INDIGO,
        Color.LIGHTGREEN,
        Color.MAGENTA,
        Color.ORANGERED,
        Color.SADDLEBROWN,
        Color.TURQUOISE,
        Color.YELLOWGREEN);

    public List<Cluster> cluster() {
        int colorSelectedIndex = 0;
        // выбираем случайную точку как центр первого кластера
        DataPoint center = data.get((int) (Math.random() * data.size()));
        Cluster firstCluster = new Cluster(0, center, colors.get(colorSelectedIndex));
        clusters.add(firstCluster);
        colorSelectedIndex++;

        // пока не сформируем нужное количество кластеров
        while (clusters.size() < k) {
            double maxMinDistance = Double.MIN_VALUE;
            DataPoint newCenter = null;

            // находим точку, наиболее удаленную от всех кластеров
            for (DataPoint point : data) {
                double minDistance = Double.MAX_VALUE;
                for (Cluster cluster : clusters) {
                    double distance = point.distanceTo(cluster.getCenter());
                    if (distance < minDistance) {
                        minDistance = distance;
                    }
                }
                if (minDistance > maxMinDistance) {
                    maxMinDistance = minDistance;
                    newCenter = point;
                }
            }

            // создаем новый кластер с найденным центром и добавляем его
            Cluster newCluster = new Cluster(clusters.size(), newCenter, colors.get(colorSelectedIndex));
            colorSelectedIndex++;
            clusters.add(newCluster);
        }

        // распределяем точки по кластерам
        for (DataPoint point : data) {
            double minDistance = Double.MAX_VALUE;
            Cluster nearestCluster = null;

            // находим ближайший кластер для точки
            for (Cluster cluster : clusters) {
                double distance = point.distanceTo(cluster.getCenter());
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestCluster = cluster;
                }
            }

            // добавляем точку в кластер
            nearestCluster.addPoint(point);
        }
        return clusters;
    }
}

class Test {
    public static void main(String[] args) {
        List<DataPoint> dataPoints = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 10000; i++) {
            DataPoint dataPoint = new DataPoint(random.nextInt(501), random.nextInt(501));
            dataPoints.add(dataPoint);
        }
        MaximinClusterer maximinClusterer = new MaximinClusterer(5, dataPoints);
        List<Cluster> clusters = maximinClusterer.cluster();
        System.out.println("sad");
    }
}
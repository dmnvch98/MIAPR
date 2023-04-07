package com.example.miapr.iprFirst.algorithms;

import com.example.miapr.iprFirst.utils.Utils;
import com.example.miapr.iprFirst.model.Cluster;
import com.example.miapr.iprFirst.model.DataPoint;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Maximin {
    private final int k; // количество кластеров
    private final List<DataPoint> data; // данные для кластеризации
    private List<Cluster> clusters; // кластеры

    public Maximin(int k, List<DataPoint> data) {
        this.k = k;
        this.data = data;
        this.clusters = new ArrayList<>();
    }



    public List<Cluster> run() {
        // выбираем случайную точку как центр первого кластера
        DataPoint center = data.get(new Random().nextInt(data.size())); // выбираем случайную точку из данных
        Cluster firstCluster = new Cluster(0, center, Utils.COLORS.get(0));
        clusters.add(firstCluster);

        // пока не сформируем нужное количество кластеров
        while (clusters.size() < k) {
            double maxMinDistance = Double.MIN_VALUE;
            DataPoint newCenter = null;

            // находим точку, наиболее удаленную от всех кластеров
            for (DataPoint point : data) {
                double minDistance = Double.MAX_VALUE;
                for (Cluster cluster : clusters) {
                    // вычисляем расстояние между точкой и центром каждого кластера
                    double distance = point.distanceTo(cluster.getCenter());
                    if (distance < minDistance) {
                        // выбираем минимальное расстояние до ближайшего кластера
                        minDistance = distance;
                    }
                }
                if (minDistance > maxMinDistance) {
                    // выбираем наибольшее минимальное расстояние
                    maxMinDistance = minDistance;
                    // выбираем точку с максимальным минимальным расстоянием
                    newCenter = point;
                }
            }

            // создаем новый кластер с найденным центром и добавляем его
            Cluster newCluster = new Cluster(clusters.size(), newCenter, Utils.COLORS.get(clusters.size())); // создаем новый кластер с центром, найденным на предыдущем шаге, и следующим цветом из списка цветов
            clusters.add(newCluster); // добавляем новый кластер в список кластеров
        }

        // распределяем точки по кластерам
        for (DataPoint point : data) {
            double minDistance = Double.MAX_VALUE;
            Cluster nearestCluster = null;

            // находим ближайший кластер для точки
            for (Cluster cluster : clusters) {
                double distance = point.distanceTo(cluster.getCenter()); // вычисляем расстояние между точкой и центром каждого кластера
                if (distance < minDistance) {
                    minDistance = distance; // выбираем минимальное расстояние до ближайшего кластера
                    nearestCluster = cluster; // выбираем ближайший кластер
                }
            }

            // добавляем точку в кластер
            nearestCluster.addPoint(point); // добавляем точку в ближайший кластер
        }
        return clusters;
    }

}


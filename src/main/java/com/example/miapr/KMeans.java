package com.example.miapr;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class KMeans {
    private final int k; // количество кластеров
    private final int maxIterations; // максимальное количество итераций
    private final List<DataPoint> dataPoints; // список точек данных
    private List<Cluster> clusters; // список кластеров
    private int iteration = 0;
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


    private List<List<Cluster>> intermediateClusters;

    public KMeans(int k, int maxIterations, List<DataPoint> dataPoints) {
        this.k = k;
        this.maxIterations = maxIterations;
        this.dataPoints = dataPoints;
    }

    public List<List<Cluster>> run() {
        // Инициализируем кластеры случайными точками данных
        initializeClusters();
        // Инициализируем промежуточные список промежуточных кластеров
        intermediateClusters = new ArrayList<>();


        boolean converged = false;

        while (iteration < maxIterations && !converged) {
            // Присваиваем каждую точку данных ближайшему кластеру
            assignPointsToClusters();

            // Пересчитываем центр каждого кластера
            converged = recalculateClusterCenters();

            iteration++;
        }
        return intermediateClusters;
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

    List<Cluster> iterateClusters = new ArrayList<>();
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

        // Создаем копию кластера, содержащую только точки, назначенные на текущей итерации
        Cluster iterateCluster = new Cluster(cluster.getId(), cluster.getCenter(), cluster.getColor());
        iterateCluster.setPoints(new ArrayList<>(cluster.getPoints()));

        // Добавляем новый кластер в список
        iterateClusters.add(iterateCluster);

        // Очищаем список точек в кластере перед следующей итерацией, если это не последняя итерация
        if (iteration != maxIterations - 1) {
            cluster.clearPoints();
        }
    }
    intermediateClusters.add(iterateClusters);
    return converged;
}

}

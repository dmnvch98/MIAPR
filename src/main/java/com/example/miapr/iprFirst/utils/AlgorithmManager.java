package com.example.miapr.iprFirst.utils;

import com.example.miapr.iprFirst.algorithms.KMeans;
import com.example.miapr.iprFirst.algorithms.Maximin;
import com.example.miapr.iprFirst.model.Cluster;
import com.example.miapr.iprFirst.model.DataPoint;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AlgorithmManager {
    private int iterationQty;
    private int clustersQty;
    List<DataPoint> dataPoints;

    public AlgorithmManager() {
    }


    public AlgorithmManager(int elementsQty, int iterationQty, int clustersQty) {
        this.iterationQty = iterationQty;
        this.clustersQty = clustersQty;
        dataPoints = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < elementsQty; i++) {
            //Генерация точки на плоскости с расположением от 0 до 500
            DataPoint dataPoint = new DataPoint(random.nextInt(501), random.nextInt(501));
            dataPoints.add(dataPoint);
        }
    }

    public List<List<Cluster>> getKMeansClusters() {
        KMeans kMeans = new KMeans(clustersQty, iterationQty, dataPoints);
        return kMeans.run();
    }

    public List<Cluster> getMaxminClusters() {
        Maximin maximinCluster = new Maximin(clustersQty, dataPoints);
        return maximinCluster.run();
    }


}

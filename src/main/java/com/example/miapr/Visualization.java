package com.example.miapr;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Visualization {
    private int elementsQty;
    private int iterationQty;
    private int clustersQty;
    List<DataPoint> dataPoints;

    public Visualization() {
    }


    public Visualization(int elementsQty, int iterationQty, int clustersQty) {
        this.elementsQty = elementsQty;
        this.iterationQty = iterationQty;
        this.clustersQty = clustersQty;
        dataPoints = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < elementsQty; i++) {
            DataPoint dataPoint = new DataPoint(random.nextInt(501), random.nextInt(501));
            dataPoints.add(dataPoint);
        }
    }

    public synchronized List<List<Cluster>> showClusters() {
        KMeans kMeans = new KMeans(clustersQty, iterationQty, dataPoints);
        return kMeans.run();
    }

    public synchronized List<Cluster> getMaxMinClusters() {
        MaximinClusterer maximinClusterer = new MaximinClusterer(clustersQty, dataPoints);
        return maximinClusterer.cluster();
    }


}

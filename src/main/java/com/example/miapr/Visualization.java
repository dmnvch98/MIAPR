package com.example.miapr;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Visualization {
    private int elementsQty;
    private int iterationQty;
    private int clustersQty;

    public Visualization() {
    }


    public Visualization(int elementsQty, int iterationQty, int clustersQty) {
        this.elementsQty = elementsQty;
        this.iterationQty = iterationQty;
        this.clustersQty = clustersQty;
    }

    public List<List<Cluster>> showClusters() {
        List<DataPoint> dataPoints = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < elementsQty; i++) {
            DataPoint dataPoint = new DataPoint(random.nextInt(501), random.nextInt(501));
            dataPoints.add(dataPoint);
        }

        KMeans kMeans = new KMeans(clustersQty, iterationQty, dataPoints);
        return kMeans.run();
    }
}

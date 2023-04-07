package com.example.miapr;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class HelloController {
    private int iteration = 0;
    @FXML
    private Canvas maximinCanvas;
    @FXML
    private Canvas canvas;
    private List<List<Cluster>> intermediateClusters;
    private List<Cluster> maximinClusters;
    @FXML
    private Button nextIteration;
    @FXML
    private Text iterationText;

    @FXML
    private TextField clustersQtyInput;

    @FXML
    private TextField elementsQtyInput;

    @FXML
    private TextField iterationQtyInput;

    @FXML
    private Button start;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button clear;

    @FXML
    void start(MouseEvent event) {
        clearCanvases();
        int elementsQty = Integer.parseInt(elementsQtyInput.getText());
        int iterationsQty = Integer.parseInt(iterationQtyInput.getText());
        int clustersQty = Integer.parseInt(clustersQtyInput.getText());
        Visualization visualization = new Visualization(elementsQty, iterationsQty, clustersQty);

        intermediateClusters = visualization.showClusters();
        maximinClusters = visualization.getMaxMinClusters();

        iteration = 1;
        // Отображение точек первой итерации
        initCirclesForKMeans(iteration);
        initCirclesForMaxMin();
        iterationText.setText(String.valueOf(iteration));
        nextIteration.setDisable(false);
        iteration++;

    }

    @FXML
    void nextInteration() {
        initCirclesForKMeans(iteration);
        iteration++;
        iterationText.setText(String.valueOf(iteration));
        nextIteration.setDisable(iteration == Integer.parseInt(iterationQtyInput.getText()));
    }

    private void initCirclesForKMeans(int iteration) {
        List<Circle> circles = new ArrayList<>();
        for (Cluster cluster : intermediateClusters.get(iteration)) {
            Color color = cluster.getColor();

            for (DataPoint point : cluster.getPoints()) {
                Circle circle = new Circle(point.getX(), point.getY(), 2, color);
                circles.add(circle);
            }
        }
        fillCanvas(circles, canvas);
    }

    private void fillCanvas(List<Circle> circles, Canvas canvas) {
        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (Circle circle : circles) {
            canvas.getGraphicsContext2D().setFill(circle.getFill());
            canvas.getGraphicsContext2D().fillOval(circle.getCenterX() - circle.getRadius(), circle.getCenterY() - circle.getRadius(), circle.getRadius() * 2, circle.getRadius() * 2);
        }
    }

    private void initCirclesForMaxMin() {
        List<Circle> circles = new ArrayList<>();
        for (Cluster cluster : maximinClusters) {
            Color color = cluster.getColor();
            for (DataPoint point : cluster.getPoints()) {
                Circle circle = new Circle(point.getX(), point.getY(), 2, color);
                circles.add(circle);
            }
        }
        fillCanvas(circles, maximinCanvas);
    }

    @FXML
    void clearAll() {
        clustersQtyInput.clear();
        elementsQtyInput.clear();
        iterationQtyInput.clear();
        anchorPane.getChildren().removeIf(node -> node instanceof Circle);
        clearCanvases();
        iterationText.setText("-");
        iteration = 0;
    }

    private void clearCanvases() {
        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        maximinCanvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
}
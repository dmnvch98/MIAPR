package com.example.miapr;

import javafx.fxml.FXML;
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

    private List<List<Cluster>> intermediateClusters;
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
        anchorPane.getChildren().removeIf(node -> node instanceof Circle);
        int elementsQty = Integer.parseInt(elementsQtyInput.getText());
        int iterationsQty = Integer.parseInt(iterationQtyInput.getText());
        int clustersQty = Integer.parseInt(clustersQtyInput.getText());
        Visualization visualization = new Visualization(elementsQty, iterationsQty, clustersQty);
        intermediateClusters = visualization.showClusters();
        iteration = 1;
        // Отображение точек первой итерации
        showCirclesForIteration(iteration);
        iterationText.setText(String.valueOf(iteration));
        nextIteration.setDisable(false);
        iteration++;
    }

    @FXML
    void nextInteration() {
        anchorPane.getChildren().removeIf(node -> node instanceof Circle);
        showCirclesForIteration(iteration);
        iteration++;
        iterationText.setText(String.valueOf(iteration));
        nextIteration.setDisable(iteration == Integer.parseInt(iterationQtyInput.getText()));
    }

    @FXML
    void clear() {
        clustersQtyInput.clear();
        elementsQtyInput.clear();
        iterationQtyInput.clear();
        anchorPane.getChildren().removeIf(node -> node instanceof Circle);
        iterationText.setText("-");
        iteration = 0;
    }

    private void showCirclesForIteration(int iteration) {
        anchorPane.getChildren().removeIf(node -> node instanceof Circle);
        List<Circle> circles = new ArrayList<>();
        for (Cluster cluster : intermediateClusters.get(iteration)) {
            Color color = cluster.getColor();

            for (DataPoint point : cluster.getPoints()) {
                Circle circle = new Circle(point.getX(), point.getY(), 2, color);
                circles.add(circle);
            }
        }
        anchorPane.getChildren().addAll(circles);
    }
}
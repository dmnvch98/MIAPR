package com.example.miapr;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController {

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
    void start(MouseEvent event) {
        anchorPane.getChildren().removeIf(node -> node instanceof Circle);
        int elementsQty = Integer.parseInt(elementsQtyInput.getText());
        int iterationsQty = Integer.parseInt(iterationQtyInput.getText());
        int clustersQty = Integer.parseInt(clustersQtyInput.getText());
        Visualization visualization = new Visualization(elementsQty, iterationsQty, clustersQty);
        List<Circle> circles = visualization.showClusters();
        anchorPane.getChildren().addAll(circles);
    }

}
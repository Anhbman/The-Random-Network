package finalOOP.view.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class Vertex {

    public static String id = null;
    @FXML
    private AnchorPane pane;
    @FXML
    Label label;

    @FXML
    void onMouseExited(MouseEvent event){
        this.pane.setStyle("-fx-background-color: yellow;-fx-background-radius: 100%;-fx-border-radius: 100%;-fx-border-width: 2;-fx-border-color: gray;");
    }

    @FXML
    void onMouseMoved(MouseEvent event) {
        this.pane.setStyle("-fx-background-color: #06f6b5;-fx-background-radius: 100%;-fx-border-radius: 100%;-fx-border-width: 2;-fx-border-color: gray;");
    }

    @FXML
    void onMouseClick(MouseEvent event) {
        Vertex.id = null;
    }

    @FXML
    void onMoustDragged(MouseEvent event) {
        this.pane.setStyle("-fx-background-color: #06f6b5;-fx-background-radius: 100%;-fx-border-radius: 100%;-fx-border-width: 2;-fx-border-color: gray;");
        Vertex.id = this.pane.getId();
    }
}
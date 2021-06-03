package finalOOP.model.display;

import finalOOP.model.algorithms.SmallWorldNW;
import finalOOP.view.animation.AnimationJFX;
import finalOOP.view.animation.LineAnimation;
import finalOOP.view.animation.LineRemove;
import finalOOP.view.controller.MainController;
import finalOOP.view.home.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import java.io.IOException;

public class SmallWorldDisplay extends Display {

    @Override
    public void setupGraph() {
        super.setupGraph();
        for (int i = 0; i < this.getAlgorithms().getTotalNodes(); i++) {
            for (int j = i+1; j < ((((SmallWorldNW)this.getAlgorithms()).getNeighborNodes()) + 1 +i) ; j++) {
                int num = j;
                if (num >= this.getAlgorithms().getTotalNodes()) {
                    num = num - this.getAlgorithms().getTotalNodes();
                }
                this.addedge1(i,num);
            }
        }
    }

    @Override
    public void display() throws IOException {
        BorderPane root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
        ((AnchorPane) root.getLeft()).getChildren().get(10).setVisible(false);
        ((AnchorPane) root.getLeft()).getChildren().get(11).setVisible(false);
        ((AnchorPane) root.getLeft()).getChildren().get(8).setVisible(false);
        ((Label) ((AnchorPane) root.getTop()).getChildren().get(0)).setText(Controller.title);
        ((AnchorPane) root.getLeft()).getChildren().get(15).setVisible(false);
        ((AnchorPane) root.getLeft()).getChildren().get(16).setVisible(false);
        Scene scene = new Scene(root);
        MainController.createGraph.setScene(scene);
        Controller.home.hide();
        MainController.createGraph.show();
    }

    public void addedge(int s, int e) {
        Pane start = MainController.vertex.get(s);
        Pane end = MainController.vertex.get(e);
        Line line = new Line();
        line.setStartX(start.getLayoutX() + 22.5);
        line.setStartY(start.getLayoutY() + 22.5);
        line.setEndX(end.getLayoutX() + 22.5);
        line.setEndY(end.getLayoutY() + 22.5);
        line.setId(start.getId() + "-" + end.getId());
        Line line1 = new Line();
        line1.setStartX(end.getLayoutX() + 22.5);
        line1.setStartY(end.getLayoutY() + 22.5);
        line1.setEndX(start.getLayoutX() + 22.5);
        line1.setEndY(start.getLayoutY() + 22.5);
        line1.setId(end.getId() + "-" + start.getId());
        line.setStrokeWidth(3);
        line.setFill(Color.BLANCHEDALMOND);
        line1.setStrokeWidth(3);
        line1.setFill(Color.BLANCHEDALMOND);
        int k = 0;
        for (int j = 0; j < MainController.edges.size(); j++) {
            if (MainController.edges.get(j).getId().compareTo(line.getId()) == 0 || MainController.edges.get(j).getId().compareTo(line1.getId()) == 0) {
                k++;
            }
        }
        if (k == 0) {
            MainController.edges.add(line);
        }
    }

    @Override
    public void StepByStep(AnchorPane ViewBack, AnchorPane ViewTop) {

        if (getCount() < MainController.edges1.size()) {
                AnimationEdge(MainController.edges.get(getCount()));
               ViewBack.getChildren().remove(MainController.edges1.get(getCount()));
                ViewBack.getChildren().add(MainController.edges.get(getCount()));
            setCount(getCount()+ 1);
        }
    }

    @Override
    public void run(AnchorPane viewback, AnchorPane viewtop) {
        this.animationJFX = new LineRemove(new Line(), Duration.millis(100));
        this.current = this.animationJFX;
        for (int i = getCount(); i < MainController.edges.size(); i++){
            Line line = MainController.edges.get(i);
            double endx = line.getEndX();
            double endy = line.getEndY();
            line.setEndX(line.getStartX());
            line.setEndY(line.getStartY());
            AnimationJFX ss = new LineRemove( MainController.edges1.get(i),Duration.millis(300));
            current.setNextAnimation(ss);
            current = ss;
            viewback.getChildren().add(line);
            AnimationJFX aasdsd = new LineAnimation(line,Duration.millis(300),endx,endy,Color.RED,3);
            current.setNextAnimation(aasdsd);
            current = aasdsd;
        }
        this.animationJFX.getNextAnimation().play();
    }

    public void addedge1(int s, int e) {
        Pane start = MainController.vertex.get(s);
        Pane end = MainController.vertex.get(e);
        Line line = new Line();
        line.setStartX(start.getLayoutX() + 22.5);
        line.setStartY(start.getLayoutY() + 22.5);
        line.setEndX(end.getLayoutX() + 22.5);
        line.setEndY(end.getLayoutY() + 22.5);
        line.setId(start.getId() + "-" + end.getId());

        line.setStrokeWidth(3);
        line.setFill(Color.BLANCHEDALMOND);
        MainController.edges1.add(line);
    }

}

package finalOOP.model.display;

import finalOOP.view.animation.*;
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

public class ScaleFreeDisplay extends Display {

    @Override
    public void setupGraph() {
        super.setupGraph();
    }

    @Override
    public void display() throws IOException {
        BorderPane root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
        Label label = new Label("Start");
        ((AnchorPane)root.getLeft()).getChildren().get(0).setVisible(false);
        ((AnchorPane)root.getLeft()).getChildren().add(label);
        ((AnchorPane)root.getLeft()).getChildren().get(1).setVisible(false);
        //((AnchorPane)root.getLeft()).getChildren().get(5).setVisible(false);
        //((AnchorPane)root.getLeft()).getChildren().get(6).setVisible(false);
        ((AnchorPane)root.getLeft()).getChildren().get(8).setVisible(false);
        ((AnchorPane)root.getLeft()).getChildren().get(14).setVisible(false);
        ((AnchorPane)root.getLeft()).getChildren().get(16).setVisible(false);
        ((Label) ((AnchorPane) root.getTop()).getChildren().get(0)).setText(Controller.title);
        Scene scene = new Scene(root);
        //scene.setFill(Color.TRANSPARENT);
        //FinalOOP.view.MainController.createGraph.initStyle(StageStyle.TRANSPARENT);
        MainController.createGraph.setScene(scene);
        Controller.home.hide();
        MainController.createGraph.show();
    }

    public void addedge(int s,int e){
        Pane start = MainController.vertex.get(s);
        Pane end = MainController.vertex.get(e);
        Line line = new Line();
        line.setStartX(start.getLayoutX() + 22.5);
        line.setStartY(start.getLayoutY() + 22.5);
        line.setEndX(end.getLayoutX() + 22.5);
        line.setEndY(end.getLayoutY() + 22.5);
        line.setId(start.getId()+ "-" + end.getId());
        Line line1 = new Line();
        line1.setStartX(end.getLayoutX() + 22.5);
        line1.setStartY(end.getLayoutY() + 22.5);
        line1.setEndX(start.getLayoutX() + 22.5);
        line1.setEndY(start.getLayoutY() + 22.5);
        line1.setId(end.getId()+ "-" + start.getId());
        //
        line.setStrokeWidth(3);
        line.setFill(Color.BLANCHEDALMOND);
        line1.setStrokeWidth(3);
        line1.setFill(Color.BLANCHEDALMOND);
        int k = 0;
        for (int j = 0; j< MainController.edges.size(); j++){
            if (MainController.edges.get(j).getId().compareTo(line.getId()) == 0 || MainController.edges.get(j).getId().compareTo(line1.getId()) == 0) {
                k++;
            }
        }
        if (k==0) {
            MainController.edges.add(line);
        }
    }

    @Override
    public void StepByStep(AnchorPane Viewback, AnchorPane Viewtop) {
        if (getCount() < MainController.totalNode) {
            setCount(Viewtop.getChildren().size());
            if (getCount() > MainController.neibor) {
                removeAnimationVertex(getCount() - 1);
            }
            if (Viewback.getChildren().size() < MainController.edges.size()) {
                Viewtop.getChildren().add(MainController.vertex.get(getCount()));
                AnimationVertext(getCount());
                for (int i = getCount(); i < MainController.edges.size(); i++) {
                    String a[] = MainController.edges.get(i).getId().split("-");
                    if (a[0].compareTo(String.valueOf(getCount())) == 0) {
                        Viewback.getChildren().add(MainController.edges.get(i));
                    }
                }
            }
        }
    }

    @Override
    public void run(AnchorPane viewback, AnchorPane viewtop) {
        this.animationJFX = new LineRemove(new Line(), Duration.millis(100));
        this.current = this.animationJFX;
        int i = viewback.getChildren().size() ;
        int j = viewtop.getChildren().size();
        removeAnimationVertex(j-1);
        while (i < MainController.edges.size() && j <  MainController.totalNode){
            if (j <  MainController.totalNode){
                MainController.vertex.get(j);
                MainController.vertex.get(j).setOpacity(0);
                viewtop.getChildren().add( MainController.vertex.get(j));
                AnimationJFX bb = new VertexZoomColor( MainController.vertex.get(j),Duration.millis(300),null);
                current.setNextAnimation(bb);
                current =  bb;
            }
            if (i < MainController.edges.size()){
                for (int k = i; k < MainController.edges.size(); k++){
                    String a[] = MainController.edges.get(k).getId().split("-");
                    if (Integer.valueOf(a[0]) == j){
                        Line line = MainController.edges.get(k);
                        double endx = line.getEndX();
                        double endy = line.getEndY();
                        line.setEndX(line.getStartX());
                        line.setEndY(line.getStartY());
                        viewback.getChildren().add(line);
                        AnimationJFX aasdsd = new LineAnimation(line,Duration.millis(300),endx,endy,Color.BLACK,3);
                        current.setNextAnimation(aasdsd);
                        current = aasdsd;
                        i++;
                        continue;
                    }
                    break;
                }
            }
            j++;
        }
        this.animationJFX.getNextAnimation().play();
    }

}




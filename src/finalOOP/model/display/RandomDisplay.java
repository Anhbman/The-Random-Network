package finalOOP.model.display;

import animatefx.animation.FadeIn;
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

public class RandomDisplay extends Display {


    @Override
    public void setupGraph() {
        super.setupGraph();
    }

    @Override
    public void display() throws IOException {
        BorderPane root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
        ((AnchorPane)root.getLeft()).getChildren().get(0).setVisible(false);
        ((AnchorPane)root.getLeft()).getChildren().get(1).setVisible(false);
        ((AnchorPane)root.getLeft()).getChildren().get(5).setVisible(false);
        ((AnchorPane)root.getLeft()).getChildren().get(10).setVisible(false);
        ((AnchorPane)root.getLeft()).getChildren().get(11).setVisible(false);
        ((AnchorPane)root.getLeft()).getChildren().get(14).setVisible(false);
        ((AnchorPane)root.getLeft()).getChildren().get(15).setVisible(false);
        // ((AnchorPane)root.getLeft()).getChildren().get(6).setVisible(false);
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
    public void StepByStep(AnchorPane ViewBack, AnchorPane ViewTop) {
        if (getCount() < MainController.edges.size()){
            if (getCount() > 0){
                removeAnimationEdge(MainController.edges.get(getCount() - 1));
                String b[] = MainController.edges.get(getCount() - 1).getId().split("-");
                removeAnimationVertex(Integer.valueOf(b[0]));
                removeAnimationVertex(Integer.valueOf(b[1]));
            }
            AnimationEdge(MainController.edges.get(getCount()));
            String a[] = MainController.edges.get(getCount()).getId().split("-");
            AnimationVertext(Integer.valueOf(a[0]));
            AnimationVertext(Integer.valueOf(a[1]));
            ViewBack.getChildren().add(MainController.edges.get(getCount()));
            setCount(getCount() + 1);
        }
    }

    @Override
    public void run(AnchorPane viewback, AnchorPane viewtop) {
        if (getCount() > 0) {
            String a[] = MainController.edges.get(getCount() - 1).getId().split("-");
            removeAnimationVertex(Integer.valueOf(a[0]));
            removeAnimationVertex(Integer.valueOf(a[1]));
            removeAnimationEdge(MainController.edges.get(getCount() - 1));
        }
        this.animationJFX = new LineRemove(new Line(), Duration.millis(100));
        this.current = this.animationJFX;
        for (int i = getCount(); i < MainController.edges.size(); i++){
            Line line = MainController.edges.get(i);
            double endx = line.getEndX();
            double endy = line.getEndY();
            line.setEndX(line.getStartX());
            line.setEndY(line.getStartY());
            viewback.getChildren().add(line);
            AnimationJFX aasdsd = new LineAnimation(line,Duration.millis(300),endx,endy,Color.BLACK,3);
            current.setNextAnimation(aasdsd);
            current = aasdsd;
        }
        this.animationJFX.getNextAnimation().play();
    }

//    public void run(AnchorPane viewtop, AnchorPane viewback ){
//        for (count = 0; count < MainController.edges.size(); count++){
//            try {
//                StepByStep(viewtop,viewback);
//                Thread.sleep(1000);
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }
//        }
//    }
//
//
//    @Override
//    public void run(AnchorPane viewtop, AnchorPane viewback) {
//
//    }
}

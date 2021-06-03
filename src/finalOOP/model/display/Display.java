package finalOOP.model.display;

import finalOOP.model.algorithms.Algorithms;
import finalOOP.view.animation.AnimationJFX;
import finalOOP.view.controller.MainController;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

import java.io.IOException;
import java.util.List;

public abstract class Display {
    protected AnimationJFX animationJFX;
    protected AnimationJFX current;
    private Algorithms algorithms;
    private List<List<Integer>> graphEdges;
    protected int count;


    public int getCount() {
        return count;
    }

    public List<List<Integer>> getGraphEdges() {
        return graphEdges;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Algorithms getAlgorithms() {
        return algorithms;
    }

    public void setAlgorithms(Algorithms algorithms) {
        this.algorithms = algorithms;
    }

    public List<List<Integer>> getListEdges() {
        return graphEdges;
    }

    public void setGraphEdges(List<List<Integer>> listEdges) {
        this.graphEdges = listEdges;
    }

    public void setupGraph(){
        for (int i = 0; i < this.getAlgorithms().getGraphEdges().size(); i++) {
            for (int j = 0; j <this.getAlgorithms().getGraphEdges().get(i).size() ; j++) {
                addedge(i, Integer.valueOf(this.getAlgorithms().getGraphEdges().get(i).get(j).toString()));
            }
        }
    }
    public abstract void display() throws IOException;
    public abstract void addedge(int s,int e);

    public abstract void StepByStep(AnchorPane ViewTop, AnchorPane ViewBack);

    public void removeAnimationEdge(Line line){
        line.setStyle("-fx-stroke:  black;");
    }

    public void AnimationEdge(Line line){
        line.setStyle("-fx-stroke:  red;");
    }

    public void removeAnimationVertex(int i){
        MainController.vertex.get(i).setStyle("-fx-background-color:  yellow;-fx-background-radius: 100%;-fx-border-radius: 100%;-fx-border-width: 2;-fx-border-color:  gray;");
    }

    public void AnimationVertext(int i){
        Pane vertex = MainController.vertex.get(i);
        vertex.setStyle("-fx-background-color: gray;-fx-background-radius: 100%;-fx-border-radius: 100%;-fx-border-width: 2;-fx-border-color: red;");

    }

    public abstract void run(AnchorPane viewback, AnchorPane viewtop);
}


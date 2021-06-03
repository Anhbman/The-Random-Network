package finalOOP.model.context;

import finalOOP.model.display.Display;
import finalOOP.model.displayFactory.DisplayFactory;
import finalOOP.view.controller.MainController;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;

import java.util.List;

public class Context {
    private Display display;
    private List<List<Integer>> graphEdges;
    private List<Line> edges;



    public Display getDisplay() {
        return display;
    }

    public void setDisplay(DisplayFactory display) {
        this.display = display.makeDisplay();
    }

    public List<List<Integer>> getGraphEdges() {
        return graphEdges;
    }

    public void setGraphEdges(List<List<Integer>> graphEdges) {
        this.graphEdges = graphEdges;
    }

    public void setCount(int count){
        this.display.setCount(count);
    }

    public int getCount(){
        return this.display.getCount();
    }

    public void clear(){
        this.display.setCount(0);
    }

//    public void run(AnchorPane viewtop, AnchorPane viewback){
//        Thread t = new Thread(display);
//        t.start();
//    }

    public void runAuto(AnchorPane viewBack, AnchorPane viewTop){
        if (this.display.getCount() < MainController.edges.size()){
            display.StepByStep(viewBack,viewTop);
        }
    }
    public void runStepByStep(AnchorPane viewBack, AnchorPane viewTop){
            if (this.display.getCount() < MainController.edges.size()){
                display.StepByStep(viewBack,viewTop);
            }
    }
}
package finalOOP.view.controller;

import finalOOP.model.context.Context;
import finalOOP.model.algorithms.RandomNW;
import finalOOP.model.algorithms.ScaleFreeNW;
import finalOOP.model.algorithms.SmallWorldNW;
import finalOOP.model.display.RandomDisplay;
import finalOOP.model.display.ScaleFreeDisplay;
import finalOOP.model.display.SmallWorldDisplay;
import finalOOP.model.network.Network;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainController {
    public static Stage createGraph = null;
    public static List<Pane> vertex = new ArrayList<>();
    public static List<Line> edges = new ArrayList<>();
    public static List<Line> edges1 = new ArrayList<>();
    public static Network network = new Network();
    public static Context context = new Context();
    public static int totalNode;
    public static int neibor;
    @FXML
    TextField textNode;
    @FXML
    TextField textNeibor;
    @FXML
    TextField textBeta;
    @FXML
    AnchorPane View;
    @FXML
    AnchorPane Viewback;
    @FXML
    AnchorPane Viewtop;
    @FXML
    Button Setup;
    @FXML
    Button Clear;
    @FXML
    Button AddEgde;
    @FXML
    Button addVertex;
    @FXML
    Button Close;
    @FXML
    Button Run;
    @FXML
    Label title;


    int add = 0;

    int dinh = 0;
    int Count = 0;
    int current = 0;
    int add1 = 0;
    public static int all = 0;
    int rewire = 0;
    public MainController(){
    }


    void Error(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notification");
        alert.setContentText("Error Input!");
        alert.show();
    }

    boolean Check(){
        return textBeta.getText().compareTo("") != 0 && textNeibor.getText().compareTo("") != 0 && textNode.getText().compareTo("") != 0;
    }
    public void Submit(ActionEvent event) throws IOException {

        if (event.getSource() == Setup){
            themDinh();
            network = new Network();
            if (MainController.context.getDisplay() instanceof RandomDisplay){
                if (textNode.getText().compareTo("") == 0 || textBeta.getText().compareTo("") == 0  || Integer.valueOf(textNode.getText()) <= 1 || 0 > Double.valueOf(textBeta.getText()) || Double.valueOf(textBeta.getText()) >1){
                    Error();
                    return;
                }
                context.getDisplay().setAlgorithms(new RandomNW(network, Integer.valueOf(textNode.getText()), Double.valueOf(textBeta.getText())));
                context.getDisplay().getAlgorithms().buildNetwork();
                this.Viewtop.getChildren().addAll(vertex);
                context.getDisplay().setupGraph();
            }else if (MainController.context.getDisplay() instanceof SmallWorldDisplay){
                if ( !Check() || Integer.valueOf(textNode.getText()) <3 || Double.valueOf(textBeta.getText()) < 0 || Double.valueOf(textBeta.getText()) > 1 || Integer.valueOf(textNeibor.getText())%2 != 0
                        || Integer.valueOf(textNeibor.getText()) > Integer.valueOf(textNode.getText()) - 2 + (Integer.valueOf(textNode.getText())%2) || Integer.valueOf(textNeibor.getText()) < 2 || !Check()){
                    Error();
                    return;
                }
                context.getDisplay().setAlgorithms(new SmallWorldNW(network, Integer.valueOf(textNode.getText()), Double.valueOf(textBeta.getText()), Integer.valueOf(textNeibor.getText())/2));
                context.getDisplay().getAlgorithms().buildNetwork();
                this.Viewtop.getChildren().addAll(vertex);
                context.getDisplay().setupGraph();
                this.Viewback.getChildren().addAll(edges1);
            }else if (MainController.context.getDisplay() instanceof ScaleFreeDisplay){
                if ( !Check() || Integer.valueOf(textBeta.getText()) > Integer.valueOf(textNeibor.getText()) || Integer.valueOf(textNode.getText()) < Integer.valueOf(textNeibor.getText()) ){
                    Error();
                    return;
                }
                context.getDisplay().setAlgorithms(new ScaleFreeNW(network, Integer.valueOf(textNode.getText()),Integer.valueOf(textNeibor.getText()),Integer.valueOf(textBeta.getText())));
                context.getDisplay().getAlgorithms().buildNetwork();
                context.getDisplay().setupGraph();
                totalNode = Integer.valueOf(textNode.getText());
                neibor = Integer.valueOf(textNeibor.getText());
                for (int i =0; i<Integer.valueOf(textNeibor.getText());i++){
                    this.Viewtop.getChildren().add(vertex.get(i));
                }
                dinh =Integer.valueOf(textNeibor.getText());
                for (int j =0; j< edges.size(); j++){
                    String a[] = edges.get(j).getId().split("-");
                    if (Integer.valueOf(a[0]) < Integer.valueOf(textNeibor.getText())) {
                        this.Viewback.getChildren().add(edges.get(j));
                        continue;
                    }
                }
            }
        } else if (event.getSource() == Clear){
            vertex.clear();
            this.Viewtop.getChildren().clear();
            this.Viewback.getChildren().clear();
            edges.clear();
            edges1.clear();
            context.clear();
        }else if (event.getSource() == Run) {
            context.getDisplay().run(Viewback,Viewtop);
        }
    }



    public void  themDinh() throws IOException{

        double height = this.View.getHeight();
        double width = this.View.getWidth();
        double centerX = width / 2;
        double centerY = height / 2;
        double radius = height > width ? (width - 150) / 2 : (height - 150) / 2;
        int count = Integer.valueOf(textNode.getText());
        double deg = Math.PI * 2 / count;

        for (int i = 0; i < count; i++){
            AnchorPane vertex = FXMLLoader.load(getClass().getResource("Vertex.fxml"));
            double x = centerX + radius * Math.sin(deg * i);
            double y = centerY + radius * Math.cos(deg * i) - 20;
            vertex.setLayoutX(x);
            vertex.setLayoutY(y);
            ((Label) vertex.getChildren().get(0)).setText(String.valueOf(i));
            this.vertex.add(vertex);
            vertex.setId(String.valueOf(i));
        }
    }


    public void addVertex(){
        context.runStepByStep(Viewback,Viewtop);
    }

    @FXML
    void onMouseClick(MouseEvent event) {
        if (event.getSource() == Close){
            MainController.createGraph.hide();
            vertex.clear();
            this.Viewtop.getChildren().clear();
            this.Viewback.getChildren().clear();
            edges.clear();
            edges1.clear();
            context.clear();
        }
    }
    @FXML
    void onMouseDreged(MouseEvent event){
        if (Vertex.id == null)
            return;
        Label label = null;
        for (int k = 0; k < this.edges.size(); k++){
            String id[] = this.edges.get(k).getId().split("-");
            if (id[0].compareTo(Vertex.id)==0 || id[1].compareTo(Vertex.id) == 0){
                int vertex1 = Integer.valueOf(id[0]);
                int vertex2 = Integer.valueOf(id[1]);
                int d = Integer.valueOf(Vertex.id) == vertex1 ? vertex2 : vertex1;
                Line line = this.edges.get(k);
                line.setStartX(this.vertex.get(vertex1).getLayoutX() + 22.5);
                line.setStartY(this.vertex.get(vertex1).getLayoutY() + 22.5);
                line.setEndX(this.vertex.get(vertex2).getLayoutX() + 22.5);
                line.setEndY(this.vertex.get(vertex2).getLayoutY() + 22.5);
            }
        }
        this.vertex.get(Integer.valueOf(Vertex.id)).setLayoutX(event.getX());
        this.vertex.get(Integer.valueOf(Vertex.id)).setLayoutY(event.getY());
    }
}


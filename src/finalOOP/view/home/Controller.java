package finalOOP.view.home;

import finalOOP.model.displayFactory.RandomFactory;
import finalOOP.model.displayFactory.ScaleFreeFactory;
import finalOOP.model.displayFactory.SmallWorldFactory;
import finalOOP.view.controller.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    public static String title;
    public static Stage home = new Stage();

    @FXML
    Button button1;
    @FXML
    Button button2;
    @FXML
    Button button3;

    public void submit(ActionEvent event) throws IOException {
        MainController.createGraph = new Stage();
        if (event.getSource() == button1){
            Controller.title = "Random-Network";
                MainController.context.setDisplay(new RandomFactory());
        }else if (event.getSource() == button2){
            Controller.title = "SmallWorld-NetWork";
            MainController.context.setDisplay(new SmallWorldFactory());
        }else if(event.getSource() == button3){
            Controller.title = "ScaleFree-Network";
            MainController.context.setDisplay(new ScaleFreeFactory());
        }
        MainController.context.getDisplay().display();
    }
}


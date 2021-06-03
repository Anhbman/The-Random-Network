package finalOOP.model.displayFactory;

import finalOOP.model.algorithms.RandomNW;
import finalOOP.model.algorithms.ScaleFreeNW;
import finalOOP.model.display.Display;
import finalOOP.model.display.ScaleFreeDisplay;

public class ScaleFreeFactory implements DisplayFactory {
    @Override
    public Display makeDisplay() {
       ScaleFreeDisplay scaleFreeDisplay = new ScaleFreeDisplay();
       scaleFreeDisplay.setAlgorithms(new ScaleFreeNW());
       scaleFreeDisplay.setCount(0);
        return  scaleFreeDisplay;
    }
}

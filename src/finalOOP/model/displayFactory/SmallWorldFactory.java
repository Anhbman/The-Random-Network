package finalOOP.model.displayFactory;

import finalOOP.model.algorithms.ScaleFreeNW;
import finalOOP.model.display.Display;
import finalOOP.model.display.SmallWorldDisplay;

public class SmallWorldFactory implements DisplayFactory {
    @Override
    public Display makeDisplay() {
        SmallWorldDisplay smallWorldDisplay = new SmallWorldDisplay();
        smallWorldDisplay.setAlgorithms(new ScaleFreeNW());
        smallWorldDisplay.setCount(0);
        return smallWorldDisplay;
    }
}

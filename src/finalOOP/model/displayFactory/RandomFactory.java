package finalOOP.model.displayFactory;

import finalOOP.model.algorithms.RandomNW;
import finalOOP.model.display.Display;
import finalOOP.model.display.RandomDisplay;

public class RandomFactory implements DisplayFactory {
    @Override
    public Display makeDisplay() {
        RandomDisplay randomDisplay = new RandomDisplay();
        randomDisplay.setAlgorithms(new RandomNW());
        randomDisplay.setCount(0);
        return randomDisplay;
    }
}

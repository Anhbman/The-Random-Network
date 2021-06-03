package finalOOP.view.animation;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;


public class LineRemove extends AnimationJFX {
    public LineRemove(Node node, Duration duration) {
        super(node, duration);
        this.initTimeline();
        this.getTimeline().statusProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals(javafx.animation.Animation.Status.STOPPED)) {
                this.onFinished();
            }
        });
    }

    @Override
    void initTimeline() {
        this.setTimeline(
                new Timeline(
                        new KeyFrame[]{
                                new KeyFrame(
                                        this.getDuration(),
                                        new KeyValue[]{
                                               // new KeyValue(((Line) this.getNode()).setOpacity(0), this.color)
                                                new KeyValue(this.getNode().opacityProperty(),0)
                                               // new KeyValue(((Line) this.getNode()).setOpacity(0)),
                                        }
                                )
                        }
                ));
    }
}

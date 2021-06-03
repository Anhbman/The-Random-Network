package finalOOP.view.animation;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;


public class LineAnimation extends AnimationJFX {
    private double endX;
    private double endY;
    private Color color;
    private double width;
    public LineAnimation(Node node, Duration duration, double endX, double endY,Color color,double width) {
        super(node, duration);
        this.endX = endX;
        this.endY = endY;
        this.endX = endX;
        this.endY = endY;
        this.color = color;
        this.width = width;
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
                                                new KeyValue(((Line) this.getNode()).endXProperty(), endX, Interpolator.SPLINE(0.215D, 0.61D, 0.355D, 1.0D)),
                                                new KeyValue(((Line) this.getNode()).endYProperty(), endY, Interpolator.SPLINE(0.215D, 0.61D, 0.355D, 1.0D)),
                                                new KeyValue(((Line) this.getNode()).strokeProperty(), this.color),
                                                new KeyValue(((Line) this.getNode()).strokeWidthProperty(), this.width, Interpolator.SPLINE(0.215D, 0.61D, 0.355D, 1.0D))
                                        }
                                )
                        }
                ));
    }
}

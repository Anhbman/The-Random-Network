package finalOOP.view.animation;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.util.Duration;

import java.io.IOException;

public class VertexZoomColor extends AnimationJFX {
    public static int total = 0;
    private String style;

    public VertexZoomColor(Node node, Duration duration, String style) {
        super(node, duration);
        this.style = style;
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
                                                new KeyValue(this.getNode().opacityProperty(),1)
                                        })
                        }
                )
        );
    }
}

package finalOOP.view.animation;

import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.util.Duration;

public abstract class AnimationJFX {
    public static int tag = 1;
    public static AnimationJFX currentAnimation = null;
    private Timeline timeline;
    private boolean reset = false;
    private Node node;
    private AnimationJFX nextAnimation;
    private boolean hasNextAnimation = false;
    private Duration duration;

    public AnimationJFX(Node node, Duration duration) {
        this.duration = duration;
        this.node = node;
        this.initTimeline();
        this.timeline.statusProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals(javafx.animation.Animation.Status.STOPPED)) {
                this.onFinished();
            }
        });
    }

    public Duration getDuration() {
        return duration;
    }

    public AnimationJFX onFinished() {
        if (this.nextAnimation != null) {
            if (tag == 0) AnimationJFX.currentAnimation = this.nextAnimation;
            else {
                AnimationJFX.currentAnimation = this.nextAnimation;
                this.nextAnimation.play();
            }
        }
        return this;
    }


    public void play() {
        AnimationJFX.tag++;
        this.timeline.play();
    }

    abstract void initTimeline();

    public Timeline getTimeline() {
        return this.timeline;
    }

    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }




    public Node getNode() {
        return this.node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public AnimationJFX getNextAnimation() {
        return this.nextAnimation;
    }

    public void setNextAnimation(AnimationJFX nextAnimation) {
        this.hasNextAnimation = true;
        this.nextAnimation = nextAnimation;
    }


}

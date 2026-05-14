package com.example.spotongame;

import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.util.Random;

/**
 * Builds the animation (move + shrink) for a Spot based on level difficulty.
 */
public class SpotAnimator {

    private final double paneWidth;
    private final double paneHeight;
    private final Random random = new Random();

    public SpotAnimator(double paneWidth, double paneHeight) {
        this.paneWidth = paneWidth;
        this.paneHeight = paneHeight;
    }

    public ParallelTransition createAnimation(Spot spot, int level) {
        double startX = Spot.RADIUS;
        double startY = 70 + random.nextDouble() * (paneHeight - 140);
        double endX = paneWidth - Spot.RADIUS;
        double endY = 70 + random.nextDouble() * (paneHeight - 140);

        Path path = new Path();
        path.getElements().add(new MoveTo(startX, startY));
        path.getElements().add(new LineTo(endX, endY));

        double duration = Math.max(5.0, 20.0 - (level * 0.5));

        PathTransition pathTransition = new PathTransition(Duration.seconds(duration), path, spot);
        pathTransition.setOrientation(PathTransition.OrientationType.NONE);

        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(duration), spot);
        scaleTransition.setToX(0.25);
        scaleTransition.setToY(0.25);

        return new ParallelTransition(pathTransition, scaleTransition);
    }
}
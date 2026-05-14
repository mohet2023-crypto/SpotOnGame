package com.example.spotongame;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.util.Objects;
import java.util.Random;

public class Spot extends Circle {

    public static final double RADIUS = 35;
    private static final Random random = new Random();

    public Spot() {
        super(RADIUS);

        String imageName = random.nextBoolean() ? "red_spot.png" : "green_spot.png";
        Image image = new Image(Objects.requireNonNull(
                getClass().getResourceAsStream("/images/" + imageName)
        ));
        setFill(new ImagePattern(image));
    }
}
package com.example.spotongame;

import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.Objects;

public class GamePane extends Pane {

    private static final double PANE_W = 800;
    private static final double PANE_H = 600;
    private static final double LIFE_ICON_SIZE = 28;

    private final GameState state = new GameState();
    private final SoundManager sound = new SoundManager();
    private final SpotAnimator animator = new SpotAnimator(PANE_W, PANE_H);

    private Text scoreText, levelText;
    private HBox livesBox;
    private Image lifeImage;
    private Timeline spotSpawner;

    public GamePane() {
        setPrefSize(PANE_W, PANE_H);
        setMinSize(PANE_W, PANE_H);
        setMaxSize(PANE_W, PANE_H);

        Rectangle background = new Rectangle(PANE_W, PANE_H);
        background.setFill(Color.web("#1a1a2e"));
        getChildren().add(background);

        // Load the life icon image once
        lifeImage = new Image(Objects.requireNonNull(
                getClass().getResourceAsStream("/images/life.png")));

        setupLabels();
        setupClickHandler();
    }

    private void setupLabels() {
        scoreText = new Text("Score: 0");
        scoreText.setFill(Color.WHITE);
        scoreText.setFont(Font.font(20));
        scoreText.setLayoutX(10);
        scoreText.setLayoutY(30);

        // Lives shown as a row of icons
        livesBox = new HBox(5);
        livesBox.setLayoutX(310);
        livesBox.setLayoutY(10);

        levelText = new Text("Level: 1");
        levelText.setFill(Color.WHITE);
        levelText.setFont(Font.font(20));
        levelText.setLayoutX(660);
        levelText.setLayoutY(30);

        getChildren().addAll(scoreText, livesBox, levelText);
        updateLabels(); // initial draw
    }

    private void setupClickHandler() {
        setOnMouseClicked(e -> {
            if (state.isGameOver()) return;
            if (!(e.getTarget() instanceof Spot)) {
                state.registerMiss();
                sound.playMiss();
                updateLabels();
            }
        });
    }

    private void updateLabels() {
        scoreText.setText("Score: " + state.getScore());
        levelText.setText("Level: " + state.getLevel());

        // Rebuild life icons every update
        livesBox.getChildren().clear();
        for (int i = 0; i < state.getLives(); i++) {
            ImageView icon = new ImageView(lifeImage);
            icon.setFitWidth(LIFE_ICON_SIZE);
            icon.setFitHeight(LIFE_ICON_SIZE);
            icon.setPreserveRatio(true);
            livesBox.getChildren().add(icon);
        }
    }

    public void startGame() {
        scheduleSpots();
    }

    private void scheduleSpots() {
        if (spotSpawner != null) spotSpawner.stop();

        double spawnRate = Math.max(0.5, 1.2 - (state.getLevel() * 0.1));
        spotSpawner = new Timeline(new KeyFrame(Duration.seconds(spawnRate), e -> {
            if (!state.isGameOver()) spawnSpot();
        }));
        spotSpawner.setCycleCount(Timeline.INDEFINITE);
        spotSpawner.play();
    }

    private void spawnSpot() {
        Spot spot = new Spot();
        getChildren().add(spot);

        ParallelTransition animation = animator.createAnimation(spot, state.getLevel());

        animation.setOnFinished(e -> {
            if (getChildren().contains(spot)) {
                getChildren().remove(spot);
                if (!state.isGameOver()) {
                    sound.playDisappear();
                    boolean over = state.registerSpotEscaped();
                    updateLabels();
                    if (over) showGameOver();
                }
            }
        });

        spot.setOnMouseClicked(e -> {
            e.consume();
            if (state.isGameOver()) return;
            animation.stop();
            getChildren().remove(spot);
            sound.playHit();
            boolean leveledUp = state.registerHit();
            updateLabels();
            if (leveledUp) scheduleSpots();
        });

        animation.play();
    }

    private void showGameOver() {
        if (spotSpawner != null) spotSpawner.stop();
        getChildren().removeIf(node -> node instanceof Spot);
        sound.stopAll();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText("You ran out of lives!");
        alert.setContentText("Final Score: " + state.getScore()
                + "\nLevel Reached: " + state.getLevel());
        alert.showAndWait();
    }
}
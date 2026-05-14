package com.example.spotongame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SpotOnApp extends Application {

    @Override
    public void start(Stage stage) {
        GamePane gamePane = new GamePane();

        Scene scene = new Scene(gamePane, 800, 600);
        stage.setX(100);
        stage.setY(50);
        stage.setTitle("SpotOn Game");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.sizeToScene();
        stage.show();

        gamePane.startGame();
    }
}
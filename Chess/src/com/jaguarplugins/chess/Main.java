package com.jaguarplugins.chess;

import com.jaguarplugins.chess.states.MainState;
import com.jaguarplugins.chess.states.State;
import com.jaguarplugins.chess.util.Handler;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		
		launch();

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Canvas canvas = new Canvas(5000,5000);
		GraphicsContext g = canvas.getGraphicsContext2D();
		
		Handler handler = new Handler(primaryStage);
		State.setCurrentState(new MainState(handler));
		Game game = new Game(handler, g);
		
		primaryStage.setScene(new Scene(new Group(canvas)));
		primaryStage.setTitle("Chess Offline");
		primaryStage.getIcons().add(new Image("com/jaguarplugins/chess/res/logo.png"));
		primaryStage.setWidth(800);
		primaryStage.setHeight(800);
		primaryStage.minHeightProperty().bind(primaryStage.widthProperty());
		primaryStage.maxHeightProperty().bind(primaryStage.widthProperty());
		primaryStage.maximizedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue)
                primaryStage.setMaximized(false);
        });
		primaryStage.setOnCloseRequest(e -> {
			game.interrupt();
		});
		
		game.start();
		
		primaryStage.show();
		
	}

}
package com.jaguarplugins.chess;

import com.jaguarplugins.chess.states.MainState;
import com.jaguarplugins.chess.states.State;
import com.jaguarplugins.chess.style.Assets;
import com.jaguarplugins.chess.util.Handler;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		
		launch();

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
				
		Canvas canvas = new Canvas(800,800);
		GraphicsContext g = canvas.getGraphicsContext2D();
		
		Scene scene = new Scene(new Group(canvas));

		primaryStage.setScene(scene);
		primaryStage.setTitle("Chess Offline");
		primaryStage.getIcons().add(Assets.PAWN);
		primaryStage.setWidth(800);
		primaryStage.setHeight(800);
		primaryStage.setResizable(false);
		
		Handler handler = new Handler(primaryStage);
		State mainState = new MainState(handler);
		State.setCurrentState(mainState);
		Game game = new Game(handler, g);

		scene.setOnMousePressed(mainState);
		scene.setOnMouseDragged(mainState);
		scene.setOnMouseReleased(mainState);
		primaryStage.setOnCloseRequest(e -> {
			game.interrupt();
		});
		
		game.start();
		
		primaryStage.show();
		
	}

}
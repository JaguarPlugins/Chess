package com.jaguarplugins.chess.util;

import javafx.stage.Stage;

public class Handler {

	private Stage stage;

	public Handler(Stage stage) {
		super();
		this.stage = stage;
	}

//	GETTERS AND SETTERS
	
	public double getWidth() {
		return stage.getWidth() - 6;
	}

	public double getHeight() {
		return stage.getHeight() - 29;
	}
	
	public double getSquareWidth() {
		return getWidth()/8;
	}
	
	public double getSquareHeight() {
		return getHeight()/8;
	}
	
}

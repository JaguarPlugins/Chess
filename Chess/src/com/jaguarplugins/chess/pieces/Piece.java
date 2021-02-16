package com.jaguarplugins.chess.pieces;

import com.jaguarplugins.chess.util.Handler;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Piece {

	protected Handler handler;
	protected Image image;
	protected double x, y, xOffset, yOffset;
	
	public Piece(Handler handler, Image image, double x, double y) {
		this.handler = handler;
		this.image = image;
		this.x = x;
		this.y = y;
	}
	
	public void snap() {

		xOffset = ((int) (xOffset/(handler.getWidth()/8)))*(handler.getWidth()/8);
		yOffset = ((int) (yOffset/(handler.getHeight()/8)))*(handler.getHeight()/8);

		if (checkPath()) {
			x = xOffset;
			y = yOffset;
		} else {
			reset();
		}

	}
	
	public void reset() {
		xOffset = x;
		yOffset = y;
	}

//	ABSTRACT METHODS
	public abstract void render(GraphicsContext g);
	protected abstract boolean checkPath();

}

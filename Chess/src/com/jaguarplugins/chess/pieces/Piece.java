package com.jaguarplugins.chess.pieces;

import com.jaguarplugins.chess.util.Handler;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public abstract class Piece {

	protected Handler handler;
	protected Image image;
	protected double x, y, xOffset, yOffset;
	protected Rectangle r;
	protected int moves = 0;
	
	public Piece(Handler handler, Image image, double x, double y) {
		this.handler = handler;
		this.image = image;
		this.x = x*handler.getSquareWidth();
		this.y = y*handler.getSquareHeight();
		reset();
	}
	
	public void render(GraphicsContext g) {
		g.drawImage(image, x + xOffset, y + yOffset, handler.getSquareWidth(), handler.getSquareHeight());
	}
	
	public void snap() {

		xOffset = (Math.round(xOffset/(handler.getWidth()/8)))*(handler.getWidth()/8);
		yOffset = (Math.round(yOffset/(handler.getHeight()/8)))*(handler.getHeight()/8);
		
		if (x + xOffset >= 0 && x + xOffset < handler.getWidth() && y + yOffset >= 0 && y + yOffset < handler.getHeight()) {
			if (checkPath()) {
//				PIECE MOVED
				moves++;
				x += xOffset;
				y += yOffset;
			}
		}
		reset();

	}
	
	public void reset() {
		xOffset = 0;
		yOffset = 0;
		r = new Rectangle(x, y, handler.getSquareWidth(), handler.getSquareHeight());
	}

//	ABSTRACT METHODS
	protected abstract boolean checkPath();

//	GETTERS AND SETTERS
	public Rectangle getR() {
		return r;
	}

	public void setXOffset(double xOffset) {
		this.xOffset = xOffset;
	}

	public void setYOffset(double yOffset) {
		this.yOffset = yOffset;
	}

}

package com.jaguarplugins.chess.pieces;

import com.jaguarplugins.chess.util.Handler;

import javafx.scene.image.Image;

public class Knight extends Piece {

	public Knight(Handler handler, boolean white, Image image, double x, double y) {
		super(handler, white, image, x, y);
	}

	@Override
	protected boolean checkPath(Piece vicitm) {
		
		double xR = Math.abs(xOffset); // X offset relative to the player
		double yR = Math.abs(yOffset);
		
		if (xR == 1*handler.getSquareWidth() && yR == 2*handler.getSquareHeight()) {
			return true;
		}
		if (xR == 2*handler.getSquareWidth() && yR == 1*handler.getSquareHeight()) {
			return true;
		}
		return false;
		
	}

	@Override
	protected boolean checkCollisions(Piece[][] board, int xPos, int yPos, int newX, int newY) {
		return true;
	}

	@Override
	public String toString() {
		return "Knight";
	}

}

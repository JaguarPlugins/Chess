package com.jaguarplugins.chess.pieces;

import com.jaguarplugins.chess.util.Handler;

import javafx.scene.image.Image;

public class Knight extends Piece {

	public Knight(Handler handler, boolean white, Image image, double x, double y) {
		super(handler, white, image, x, y);
	}

	@Override
	protected boolean checkPath(boolean taking) {
		
		double xR = Math.abs(xOffset); // X offset relative to the player
		double yR = Math.abs(yOffset);
		
		if (xR == 2*handler.getSquareWidth() && yR == 3*handler.getSquareHeight()) {
			return true;
		}
		if (xR == 3*handler.getSquareWidth() && yR == 2*handler.getSquareHeight()) {
			return true;
		}
		return false;
		
	}

}

package com.jaguarplugins.chess.pieces;

import com.jaguarplugins.chess.util.Handler;

import javafx.scene.image.Image;

public class Queen extends Piece {

	public Queen(Handler handler, boolean white, Image image, double x, double y) {
		super(handler, white, image, x, y);
	}

	@Override
	protected boolean checkPath(boolean taking) {
		double xR = Math.abs(xOffset); // X offset relative to the player
		double yR = Math.abs(yOffset);
		
		if (yR/xR == handler.getSquareHeight()/handler.getSquareWidth()) {
			return true;
		}
		if (xR == 0.0 || yR == 0.0) {
			return true;
		}
		return false;
	}

}

package com.jaguarplugins.chess.pieces;

import com.jaguarplugins.chess.util.Handler;

import javafx.scene.image.Image;

public class Pawn extends Piece {

	public Pawn(Handler handler, boolean white, Image image, double x, double y) {
		super(handler, white, image, x, y);
	}

	@Override
	protected boolean checkPath(boolean taking) {
		
		double xR = xOffset; // X offset relative to the player
		double yR = yOffset;
		if (white) {
			yR = -yOffset;
		}
		
		if (taking) {
			if (yR == handler.getSquareHeight() && Math.abs(xR) == handler.getSquareWidth()) {
				return true;
			}
		} else {
			if (xR == 0.0) {
				if (yR == handler.getSquareHeight()) {
					return true;

				}
				if (moves < 1) {
					if (yR == 2*handler.getSquareHeight()) {
						return true;
					}
				}
			}
		}

		return false;
		
	}

}

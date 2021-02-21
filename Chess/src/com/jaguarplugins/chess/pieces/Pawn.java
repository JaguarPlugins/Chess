package com.jaguarplugins.chess.pieces;

import java.util.ArrayList;

import com.jaguarplugins.chess.util.Handler;

import javafx.scene.image.Image;

public class Pawn extends Piece {

	public Pawn(Handler handler, boolean white, Image image, double x, double y) {
		super(handler, white, image, x, y);
	}

	@Override
	protected boolean checkPath(Piece victim) {
		
		double xR = xOffset; // X offset relative to the player
		double yR = yOffset;
		if (white) {
			yR = -yOffset;
		}
		
		if (victim != null) {
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

	@Override
	protected boolean checkCollisions(Piece[][] board, int xPos, int yPos, int newX, int newY) {
		if (moves < 1) {
			ArrayList<Integer> ys = Piece.coordsAsArray(yPos, newY);
			for (int b = ys.get(0); b <= ys.get(1); b++) {
				if (board[xPos][b] != null && !board[xPos][b].equals(this)) {
					if (xPos != newX || b != newY) {
						return false;
					}
				}
			}
		}
		return true;
	}

	@Override
	public String toString() {
		return "Pawn";
	}

}
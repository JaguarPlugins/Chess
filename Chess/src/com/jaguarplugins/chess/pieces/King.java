package com.jaguarplugins.chess.pieces;

import com.jaguarplugins.chess.board.Square;
import com.jaguarplugins.chess.util.Handler;

import javafx.scene.image.Image;

public class King extends Piece {

	public King(Handler handler, boolean white, Image image, double x, double y) {
		super(handler, white, image, x, y);
	}

	@Override
	protected boolean checkPath(Piece vicitm) {
		
		double xR = Math.abs(xOffset); // X offset relative to the player
		double yR = Math.abs(yOffset);
		
		if (xR <= handler.getSquareWidth() && yR <= handler.getSquareHeight()) {
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
		return "King";
	}

	@Override
	public void showPossible(Piece[][] board, Square[][] squares, int xPos, int yPos) {
		// TODO Auto-generated method stub
		
	}

}
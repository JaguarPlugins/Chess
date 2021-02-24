package com.jaguarplugins.chess.pieces;

import com.jaguarplugins.chess.board.Square;
import com.jaguarplugins.chess.util.Handler;

import javafx.scene.image.Image;

public class Bishop extends Piece {

	public Bishop(Handler handler, boolean white, Image image, double x, double y) {
		super(handler, white, image, x, y);
	}

	@Override
	protected boolean checkPath(Piece vicitm) {
		
		double xR = Math.abs(xOffset); // X offset relative to the player
		double yR = Math.abs(yOffset);
		
		if (yR/xR == handler.getSquareHeight()/handler.getSquareWidth()) {
			return true;
		}
		return false;
		
	}

	@Override
	protected boolean checkCollisions(Piece[][] board, int xPos, int yPos, int newX, int newY) {
		int dx = (newX-xPos)/Math.abs(newX-xPos);
		int dy = (newY-yPos)/Math.abs(newY-yPos);
		while (true) {
			if (board[xPos][yPos] != null && !board[xPos][yPos].equals(this)) {
				if (xPos != newX || yPos != newY) {
					return false;
				}
			}
			xPos += dx; yPos += dy;
			if (xPos == newX) {
				return true;
			}
		}
	}
	
	@Override
	public String toString() {
		return "Bishop";
	}

	@Override
	public void showPossible(Piece[][] board, Square[][] squares, int xPos, int yPos) {
		// TODO Auto-generated method stub
		
	}

}

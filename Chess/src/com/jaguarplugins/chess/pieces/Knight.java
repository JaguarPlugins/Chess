package com.jaguarplugins.chess.pieces;

import com.jaguarplugins.chess.board.Square;
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

	@Override
	public void showPossible(Piece[][] board, Square[][] squares, int xPos, int yPos) {
		
		int total = 0;
		for (int i = 1; total <= 8; i = -i) {
			for (int j = 1; total <= 8; i = -i) {
				
				try {
					if (board[xPos - j*i][yPos + 2*j*i] != null && board[xPos - j*i][yPos + 2*j*i].isWhite() != white) {
						squares[xPos - j*i][yPos + 2*j*i].setPossible(true);
					}
					squares[xPos - j*i][yPos + 2*j*i].setPossible(true);
				} catch (IndexOutOfBoundsException e) {}
				
				try {
					if (board[xPos - 2*j*i][yPos + j*i] != null && board[xPos - 2*j*i][yPos + j*i].isWhite() != white) {
						squares[xPos - 2*j*i][yPos + j*i].setPossible(true);
					}
					squares[xPos - 2*j*i][yPos + j*i].setPossible(true);
				} catch (IndexOutOfBoundsException e) {}
				total += 2;
			}
		}
		
	}

}

package com.jaguarplugins.chess.pieces;

import java.util.ArrayList;

import com.jaguarplugins.chess.board.Square;
import com.jaguarplugins.chess.util.Handler;

import javafx.scene.image.Image;

public class Rook extends Piece {

	public Rook(Handler handler, boolean white, Image image, double x, double y) {
		super(handler, white, image, x, y);
	}

	@Override
	protected boolean checkPath(Piece vicitm) {
		
		double xR = Math.abs(xOffset); // X offset relative to the player
		double yR = Math.abs(yOffset);
		
		if (xR == 0.0 || yR == 0.0) {
			return true;
		}
		return false;
		
	}

	@Override
	protected boolean checkCollisions(Piece[][] board, int xPos, int yPos, int newX, int newY) {
		ArrayList<Integer> xs = Piece.coordsAsArray(xPos, newX);
		ArrayList<Integer> ys = Piece.coordsAsArray(yPos, newY);
		for (int a = xs.get(0); a <= xs.get(1); a++) {
			for (int b = ys.get(0); b <= ys.get(1); b++) {
				if (board[a][b] != null && !board[a][b].equals(this)) {
					if (a != newX || b != newY) {
						return false;
					}
				}
			}
		}
		return true;
	}

	@Override
	public String toString() {
		return "Rook";
	}

	@Override
	public void showPossible(Piece[][] board, Square[][] squares, int xPos, int yPos) {
		
		try {
			for (int a = xPos + 1; a < 8; a++) {
				if (board[a][yPos] != null) {
					if (board[a][yPos].isWhite() != white) {
						squares[a][yPos].setPossible(true);
					}
					break;
				}
				squares[a][yPos].setPossible(true);
			}
		} catch (IndexOutOfBoundsException e) {}
		
		try {
			for (int a = xPos - 1; a >= 0; a--) {
				if (board[a][yPos] != null) {
					if (board[a][yPos].isWhite() != white) {
						squares[a][yPos].setPossible(true);
					}
					break;
				}
				squares[a][yPos].setPossible(true);
			}
		} catch (IndexOutOfBoundsException e) {}
		
		try {
			for (int b = yPos - 1; b >= 0; b--) {
				if (board[xPos][b] != null) {
					if (board[xPos][b].isWhite() != white) {
						squares[xPos][b].setPossible(true);
					}
					break;
				}
				squares[xPos][b].setPossible(true);
			}
		} catch (IndexOutOfBoundsException e) {}
		
		try {
			for (int b = yPos + 1; b < 8; b++) {
				if (board[xPos][b] != null) {
					if (board[xPos][b].isWhite() != white) {
						squares[xPos][b].setPossible(true);
					}
					break;
				}
				squares[xPos][b].setPossible(true);
			}
		} catch (IndexOutOfBoundsException e) {}
		
	}

}

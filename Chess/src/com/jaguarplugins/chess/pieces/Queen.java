package com.jaguarplugins.chess.pieces;

import java.util.ArrayList;

import com.jaguarplugins.chess.board.Square;
import com.jaguarplugins.chess.util.Handler;

import javafx.scene.image.Image;

public class Queen extends Piece {

	public Queen(Handler handler, boolean white, Image image, double x, double y) {
		super(handler, white, image, x, y);
	}

	@Override
	protected boolean checkPath(Piece vicitm) {
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

	@Override
	protected boolean checkCollisions(Piece[][] board, int xPos, int yPos, int newX, int newY) {
		
		if (xOffset == 0 || yOffset == 0) {
			
//			When moving like a rook
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
			
		} else {
			
//			When moving like a bishop
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

	}

	@Override
	public String toString() {
		return "Queen";
	}

	@Override
	public void showPossible(Piece[][] board, Square[][] squares, int xPos, int yPos) {
		// TODO Auto-generated method stub
		
	}

}

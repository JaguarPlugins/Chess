package com.jaguarplugins.chess.pieces;

import java.util.ArrayList;

import com.jaguarplugins.chess.board.Square;
import com.jaguarplugins.chess.util.Handler;

import javafx.scene.image.Image;

public class Pawn extends Piece {

	private boolean canPass = false;
	private int xPass, yPass;
	private Blank blank;
	
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
						xPass = (int) (x/handler.getSquareWidth());
						if (white) {
							yPass = (int) (y/handler.getSquareHeight()) - 1;
						} else {
							yPass = (int) (y/handler.getSquareHeight()) + 1;
						}
						blank = new Blank(handler, white, xPass, yPass, this);
						canPass = true;
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
		if (white) {
			return "White Pawn";
		} else {
			return "Black Pawn";
		}
		
	}

	@Override
	public void showPossible(Piece[][] board, Square[][] squares, int xPos, int yPos) {
		
		int dy = 1;
		if (white) {
			dy = -1; 
		}
	
		if (board[xPos][yPos + dy] == null) {
			squares[xPos][yPos + dy].setPossible(true);
			if (moves < 1) {
				if (board[xPos][yPos + 2*dy] == null) {
					squares[xPos][yPos + 2*dy].setPossible(true);
				}
			}
		}
		
		try {
			if (board[xPos + 1][yPos + dy] != null && board[xPos + 1][yPos + dy].isWhite() != white) {
				squares[xPos + 1][yPos + dy].setPossible(true);
			}
		} catch (IndexOutOfBoundsException e) {System.out.println("HEHE");} // Checking piece outside of the board
		
		try {
		if (board[xPos - 1][yPos + dy] != null && board[xPos - 1][yPos + dy].isWhite() != white) {
			squares[xPos - 1][yPos + dy].setPossible(true);
		}
		} catch (IndexOutOfBoundsException e) {}
		
	}
	
	@Override
	protected void onMove(Piece[][] board) {
	
		if (canPass == true) {
			board[xPass][yPass] = blank;
			canPass = false;
		} else {
			if (blank != null && board[xPass][yPass] instanceof Blank) {
				board[xPass][yPass] = null;
				blank = null;
			}
		}
		
	}

}
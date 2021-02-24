package com.jaguarplugins.chess.pieces;

import com.jaguarplugins.chess.board.Square;
import com.jaguarplugins.chess.util.Handler;

public class Blank extends Piece {

	private Pawn victim;
	
	public Blank(Handler handler, boolean white, double x, double y, Pawn victim) {
		super(handler, white, null, x, y);
		this.victim = victim;
	}

	@Override
	protected boolean checkPath(Piece victim) {
		return false;
	}

	@Override
	protected boolean checkCollisions(Piece[][] board, int xPos, int yPos, int newX, int newY) {
		return false;
	}

	@Override
	public void showPossible(Piece[][] board, Square[][] squares, int xPos, int yPos) {}

	@Override
	public String toString() {
		if (white) {
			return "WHITE BLANK";
		} else {
			return "BLACK BLANK";
		}
	}
	
	@Override
	protected void onTake(Piece[][] board, Piece attacker) {
		if (attacker instanceof Pawn) {
			board[(int) (victim.getX()/handler.getSquareWidth())][(int) (victim.getY()/handler.getSquareHeight())] = null;
		}
	}
	
}

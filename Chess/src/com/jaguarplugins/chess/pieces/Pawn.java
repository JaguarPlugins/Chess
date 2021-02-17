package com.jaguarplugins.chess.pieces;

import com.jaguarplugins.chess.util.Handler;

import javafx.scene.image.Image;

public class Pawn extends Piece {

	public Pawn(Handler handler, Image image, double x, double y) {
		super(handler, image, x, y);
	}

	@Override
	protected boolean checkPath() {
		return true;
	}
	
	@Override
	public String toString() {
		return "pawn";
	}

}

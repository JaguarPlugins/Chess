package com.jaguarplugins.chess.style;

import javafx.scene.paint.Color;

public class ChessColor {

	public static Color getSquareColor(double x, double y) {
		boolean black;
		if (x % 2 == 0) {
			black = true;
		} else {
			black = false;
		}
		if (y % 2 == 0) {
			black = !black;
		}
		if (black) {
			return Color.BLACK;
		} else {
			return Color.WHEAT;
		}
	}
	
}

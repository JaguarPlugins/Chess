package com.jaguarplugins.chess.board;

import com.jaguarplugins.chess.style.ChessColor;
import com.jaguarplugins.chess.util.Handler;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Square {

	private Handler handler;
	private Color color;
	private double x, y;
	private boolean possible = false;
	
	public Square(Handler handler, int x, int y) {
		this.handler = handler;
		this.x = x*handler.getWidth()/8;
		this.y = y*handler.getHeight()/8;
		color = ChessColor.getSquareColor(x, y);
	}
	
	public void render(GraphicsContext g) {
		g.setFill(color);
		if (possible) {
			g.setFill(color.darker());			
		}
		g.fillRect(x, y, handler.getSquareWidth(), handler.getSquareHeight());
	}	
	
}

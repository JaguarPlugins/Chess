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
	private static double BORDER = 0.2;
	
	public Square(Handler handler, int x, int y) {
		this.handler = handler;
		this.x = x*handler.getWidth()/8;
		this.y = y*handler.getHeight()/8;
		color = ChessColor.getSquareColor(x, y);
	}
	
	public void render(GraphicsContext g) {
		g.setFill(color);
		g.fillRect(x, y, handler.getSquareWidth(), handler.getSquareHeight());
		if (possible) {
			g.setFill(color.darker().darker());
			g.fillOval(x + handler.getSquareWidth()*BORDER, y + handler.getSquareHeight()*BORDER, 
					handler.getSquareWidth() - 2*handler.getSquareWidth()*BORDER, handler.getSquareHeight() - 2*handler.getSquareHeight()*BORDER);			
		}
	}

//	GETTERS AND SETTERS
	public boolean isPossible() {
		return possible;
	}

	public void setPossible(boolean possible) {
		this.possible = possible;
	}	
	
}

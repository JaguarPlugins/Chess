package com.jaguarplugins.chess.states;

import com.jaguarplugins.chess.pieces.Piece;
import com.jaguarplugins.chess.style.ChessColor;
import com.jaguarplugins.chess.util.Handler;

import javafx.scene.canvas.GraphicsContext;

public class MainState extends State {

	private Piece[][] board = new Piece[8][8];;
	
	public MainState(Handler handler) {
		super(handler);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GraphicsContext g) {

//		BOARD
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				g.setFill(ChessColor.getSquareColor(x, y));
				g.fillRect(x*handler.getWidth()/8, y*handler.getHeight()/8, handler.getWidth()/8, handler.getHeight()/8);
			}
		}
		
//		PIECES
		for (Piece[] column : board) {
			for (Piece p : column) {
				if (p != null) {
					p.render(g);
				}
			}
		}
		
	}

}
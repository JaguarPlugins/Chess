package com.jaguarplugins.chess.states;

import com.jaguarplugins.chess.pieces.Bishop;
import com.jaguarplugins.chess.pieces.King;
import com.jaguarplugins.chess.pieces.Knight;
import com.jaguarplugins.chess.pieces.Pawn;
import com.jaguarplugins.chess.pieces.Piece;
import com.jaguarplugins.chess.pieces.Queen;
import com.jaguarplugins.chess.pieces.Rook;
import com.jaguarplugins.chess.style.Assets;
import com.jaguarplugins.chess.style.ChessColor;
import com.jaguarplugins.chess.util.Handler;

import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class MainState extends State {

	private Piece[][] board = new Piece[8][8];;
	private Piece heldPiece;
	private double baseX, baseY;
	private boolean currentTeam = true;
	
	public MainState(Handler handler) {
		
		super(handler);
		
		for (int i = 0; i <= 7; i++) {
			board[i][1] = new Pawn(handler, false, Assets.BLACK_PAWN, i, 1);
			board[i][6] = new Pawn(handler, true, Assets.WHITE_PAWN, i, 6);
		}
		for (int i = 0; i <= 7; i+=7) {
			board[i][0] = new Rook(handler, false, Assets.BLACK_ROOK, i, 0);
			board[i][7] = new Rook(handler, true, Assets.WHITE_ROOK, i, 7);
		}
		for (int i = 2; i <= 5; i+=3) {
			board[i][0] = new Bishop(handler, false, Assets.BLACK_BISHOP, i, 0);
			board[i][7] = new Bishop(handler, true, Assets.WHITE_BISHOP, i, 7);
		}
		for (int i = 1; i <= 6; i+=5) {
			board[i][0] = new Knight(handler, false, Assets.BLACK_KNIGHT, i, 0);
			board[i][7] = new Knight(handler, true, Assets.WHITE_KNIGHT, i, 7);
		}
		board[4][0] = new King(handler, false, Assets.BLACK_KING, 4, 0);
		board[4][7] = new King(handler, true, Assets.WHITE_KING, 4, 7);
		board[3][0] = new Queen(handler, false, Assets.BLACK_QUEEN, 3, 0);
		board[3][7] = new Queen(handler, true, Assets.WHITE_QUEEN, 3, 7);
		
	}

	@Override
	public void tick() {

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
		
//		COORDINATES
		g.setFill(Color.BLACK);
		g.setFont(new Font("calibri", 25));
		g.setTextAlign(TextAlignment.LEFT);
		g.setTextBaseline(VPos.TOP);
		for (int x = 0; x < 8; x++) {
			g.fillText(x + "", (x)*handler.getSquareWidth() + 2, 0);
		}
		for (int y = 1; y < 8; y++) {
			g.fillText(y + "", 2, y*handler.getSquareHeight());
		}
		
//		PIECES
		for (Piece[] column : board) {
			for (Piece p : column) {
				if (p != null && !p.equals(heldPiece)) {
					p.render(g);
				}
			}
		}
		if (heldPiece != null) {
			heldPiece.render(g);
		}
		
	}

//	EVENTHANDLER
	
	@Override
	public void handle(MouseEvent e) {
		
		if (State.getCurrentState() instanceof MainState) {
			
			if (e.getButton().equals(MouseButton.PRIMARY)) {
			
				if (e.getEventType().equals(MouseEvent.MOUSE_DRAGGED)) {
					
					if (heldPiece != null) {
						heldPiece.setXOffset(e.getSceneX() - baseX);
						heldPiece.setYOffset(e.getSceneY() - baseY);
					}
					
				} else if (e.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {
					
					if (heldPiece != null) {
						if (heldPiece.snap(board)) {
							currentTeam = !currentTeam;
						}
						heldPiece = null;
					}
					
				} else if (e.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
	
					for (Piece[] column : board) { for (Piece p : column) { if (p != null && p.isWhite() == currentTeam) {
						if (p.getR().intersects(e.getSceneX(), e.getSceneY(), 1, 1)) {
							heldPiece = p;
							baseX = e.getSceneX();
							baseY = e.getSceneY();
						}
					}}}
	
				}
			
			}
			
		}
		
	}

	public boolean isCurrentTeam() {
		return currentTeam;
	}

	public void setCurrentTeam(boolean currentTeam) {
		this.currentTeam = currentTeam;
	}

}
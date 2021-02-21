package com.jaguarplugins.chess.pieces;

import java.util.ArrayList;
import java.util.Collections;

import com.jaguarplugins.chess.util.Handler;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public abstract class Piece {

	protected Handler handler;
	protected Image image;
	protected double x, y, xOffset, yOffset;
	protected Rectangle r;
	protected int moves = 0;
	protected boolean white; // white at bottom, black at top
	
	public Piece(Handler handler, boolean white, Image image, double x, double y) {
		this.handler = handler;
		this.white = white;
		this.image = image;
		this.x = x*handler.getSquareWidth();
		this.y = y*handler.getSquareHeight();
		reset();
	}
	
	public void render(GraphicsContext g) {
		g.drawImage(image, x + xOffset, y + yOffset, handler.getSquareWidth(), handler.getSquareHeight());
	}
	
	public boolean snap(Piece[][] board) {

		xOffset = (Math.round(xOffset/(handler.getWidth()/8)))*(handler.getWidth()/8); // snaps x & y onto grid
		yOffset = (Math.round(yOffset/(handler.getHeight()/8)))*(handler.getHeight()/8);
		
		int xPos = (int) ((x + xOffset)/handler.getSquareWidth()); // new x coordinate
		int yPos = (int) ((y + yOffset)/handler.getSquareHeight()); // new y coordinate
		
		if (x + xOffset >= 0 && x + xOffset < handler.getWidth() && y + yOffset >= 0 && y + yOffset < handler.getHeight()) { // checks move is within board
			if (xOffset != 0.0 || yOffset != 0.0) { // checks piece has actually been moved
				if (board[xPos][yPos] == null || board[xPos][yPos].isWhite() != white) { // checks piece is moving onto blank or to take opposite piece
					if (checkPath(board[xPos][yPos])) {
						if (checkCollisions(board, (int) (x/handler.getSquareWidth()), (int) (y/handler.getSquareHeight()), xPos, yPos)) {
//							PIECE MOVED
							board[(int) (x/handler.getSquareWidth())][(int) (y/handler.getSquareHeight())] = null; // Removes old location
							board[xPos][yPos] = this; // Sets new location
							moves++;
							x += xOffset;
							y += yOffset;
							reset();
							return true;
						}
					}
				}
			}
		}
		reset();
		return false;

	}
	
	public void reset() {
		xOffset = 0;
		yOffset = 0;
		r = new Rectangle(x, y, handler.getSquareWidth(), handler.getSquareHeight());
	}

//	STATIC METHODS
	public static ArrayList<Integer> coordsAsArray(int a, int b) {
		
		ArrayList<Integer> ar = new ArrayList<Integer>();
		ar.add(a);
		ar.add(b);
		Collections.sort(ar);
		return ar;
		
	}
	
//	ABSTRACT METHODS
	protected abstract boolean checkPath(Piece victim);
	protected abstract boolean checkCollisions(Piece[][] board, int xPos, int yPos, int newX, int newY);
	public abstract String toString();
	
//	GETTERS AND SETTERS
	public Rectangle getR() {
		return r;
	}

	public void setXOffset(double xOffset) {
		this.xOffset = xOffset;
	}

	public void setYOffset(double yOffset) {
		this.yOffset = yOffset;
	}

	public boolean isWhite() {
		return white;
	}

}

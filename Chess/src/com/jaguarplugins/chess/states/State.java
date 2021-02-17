
package com.jaguarplugins.chess.states;

import com.jaguarplugins.chess.util.Handler;

import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public abstract class State implements EventHandler<MouseEvent>{

	private static State currentState;
	
	protected Handler handler;
	
	public State(Handler handler) {
		this.handler = handler;
	}

//	ABSTRACT METHODS
	public abstract void handle(MouseEvent e);
	public abstract void tick();	
	public abstract void render(GraphicsContext g);

//	GETTERS AND SETTERS
	
	public static State getCurrentState() {
		return currentState;
	}

	public static void setCurrentState(State currentState) {
		State.currentState = currentState;
	}
	
}


package com.jaguarplugins.chess.states;

import com.jaguarplugins.chess.util.Handler;

import javafx.scene.canvas.GraphicsContext;

public abstract class State {

	private static State currentState;
	
	protected Handler handler;
	
	public State(Handler handler) {
		this.handler = handler;
	}

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

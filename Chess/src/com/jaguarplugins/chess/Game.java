package com.jaguarplugins.chess;

import com.jaguarplugins.chess.util.Handler;

import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;

public class Game extends Thread {

	private boolean running;
	private GraphicsContext g;
	private Handler handler;
	
	public Game(Handler handler, GraphicsContext g) {
		
		super();
		this.handler = handler;
		this.g = g;
		running = true;
		
	}

	private void tick() {
		com.jaguarplugins.chess.states.State.getCurrentState().tick();
	}
	
	private void render() {
		Platform.runLater(() -> {
			g.clearRect(0, 0, handler.getWidth(), handler.getHeight());
			com.jaguarplugins.chess.states.State.getCurrentState().render(g);
		});
	}
	
	@Override
	public void run() {
		
		int fps = 60;
		double timePerTick = 1_000_000_000 / fps;
		double delta = 1;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		
		while(running) {
			
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta > 1) {
				if (running) {	
					tick();
					render();
				}
				delta --;
			}
			
			if(timer >= 1_000_000_000) {
				timer = 0;
			}
			
		}
		
	}
	
	@Override
	public void interrupt() {
		running = false;
	}
	
}

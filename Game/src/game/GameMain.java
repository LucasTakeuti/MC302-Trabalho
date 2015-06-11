package game;

import game.model.GameModel;

public class GameMain implements Runnable {
	
	public static final long serialVersionID = 1L;
	
	//variaveis do Game Loop
	private static final int FPS = 60;
	private static final int FrameDuration = 1000/FPS;
	private long nextFrame = System.currentTimeMillis() + FrameDuration;
	private long sleepTime = 0;
	
	private GameModel model;
	//private GameView view;
	//private GameController controller;
	
	private boolean running;
	private Thread t;
	
	public static void main(String[] args) {
		new GameMain();
	}
	
	public GameMain() {
		t = new Thread(this);
		t.start();
	}
	
	public void run() {
		
		running = true;
		
		while (running) {
			
			//update -> model
			//draw -> view
			
			sleepTime = nextFrame - System.currentTimeMillis();
			
			if (sleepTime > 0) {
				try {
					Thread.sleep(sleepTime);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			System.out.println("dormi por " + sleepTime + " milissegundos.");
			
			nextFrame = System.currentTimeMillis() + FrameDuration;
			
		}
	}
}

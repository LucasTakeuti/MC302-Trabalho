package game;

import game.controller.GameController;
import game.model.MapData;
import game.model.ShooterSpawner;
import game.view.GameView;

public class GameMain implements Runnable {
	
	public static final long serialVersionID = 1L;
	
	//variaveis do Game Loop
	private static final int FPS = 30;
	public static final double FrameDuration = 1000/(double)FPS;
	public static final double FrameDurationInSecs = (FrameDuration/1000);
	private long nextFrame = (long) (System.currentTimeMillis() + FrameDuration);
	private long sleepTime = 0;
	
	private MapData data;
	private GameView view;
	private GameController controller;
	
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
		
		MapData.getInstance();
		view = new GameView(MapData.getInstance());
		GameController.getInstance(MapData.getInstance(), view);
		
		GameController.getInstance().setInitialConditions();
		
		running = true;
		
		//game loop
		while (running) {
			
			GameController.getInstance().update();
			view.renderAsciiFrame();
			
			sleepTime = nextFrame - System.currentTimeMillis();
			
			if (sleepTime > 0) {
				try {
					Thread.sleep(sleepTime);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			nextFrame = (long) (System.currentTimeMillis() + FrameDuration);
		}
	}
}

package game.controller;

import java.util.Random;

import javax.crypto.spec.GCMParameterSpec;

import game.model.MapData;
import game.view.GameView;

public class TurnController {
	
	private static TurnController tc = null;
	
	private MapData data;
	private GameView view;
	
	private int currentTurn;
	
	private Random r = new Random();
	
	public static TurnController getInstance(MapData data, GameView view) {
		if (tc == null)
			tc = new TurnController(data, view);
		return tc;
	}
	
	public static TurnController getInstance() {
		return tc;
	}
	
	private TurnController(MapData data, GameView view) {
		this.data = data;
		this.view = view;
		
		setCurrentTurn(r.nextInt(view.amoutOfShooters) + 1);
	}
	
	//TODO: implementar timer
	public void updateTurn() {
		// shooter ja atirou E nao ha nada se movendo no mapa
		if (MapData.getInstance().getCurrentShooter().hasFinishedTurn() && !MapData.getInstance().hasMovingThings()) {
			do {
				setCurrentTurn((getCurrentTurn() % view.amoutOfShooters) + 1);
			} while (!MapData.getInstance().getCurrentShooter().isAlive() && MapData.getInstance().amountOfAliveShooters() > 1);
			MapData.getInstance().getCurrentShooter().newTurn();
		}
	}
	
	public int getCurrentTurn() {
		return currentTurn;
	}

	public void setCurrentTurn(int currentTurn) {
		this.currentTurn = currentTurn;
	}

}

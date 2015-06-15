package game.controller;

import java.util.Random;

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
	
	/*
	//TODO: implementar timer
	//proximo turno: acabou o tempo ou shooter ja atirou e nao ha nada se movendo no mapa
	private void nextTurn() {
		if (MapData.getInstance().getCurrentShooter().hasFinishedTurn() && !MapData.getInstance().hasMovingThings()) {
			setCurrentTurn(MapData.getInstance().nextShooterTurn());
			if (MapData.getInstance().amountOfAliveShooters() <= 1)
				setCurrentState(GameState.GAMEOVER);
			else
				MapData.getInstance().getCurrentShooter().newTurn();
		}
	}*/
	
	public void nextTurn() {
		// TODO Auto-generated method stub
		
	}

	public int getCurrentTurn() {
		return currentTurn;
	}

	public void setCurrentTurn(int currentTurn) {
		this.currentTurn = currentTurn;
	}

}

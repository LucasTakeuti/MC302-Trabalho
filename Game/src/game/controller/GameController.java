package game.controller;

import game.model.MapData;
import game.model.ShooterSpawner;
import game.view.GameView;

import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GameController {
	
	private MapData data;
	private GameView view;
	
	private static GameController gc = null;
	
	private int currentTurn;
	
	private GameState currentState;
	
	private ShootController shootCont;
	private PlayerController playerCont;
	
	private ShooterSpawner s;
	
	private Random r = new Random();
	
	public static GameController getInstance(MapData data, GameView view) {
		if (gc == null)
			gc = new GameController(data, view);
		return gc;
	}
	
	public static GameController getInstance() {
		return gc;
	}
	
	private GameController(MapData data, GameView view) {
		this.data = data;
		this.view = view;
		
		s = new ShooterSpawner(view.amountOfShooters);
		
		setCurrentTurn(r.nextInt(view.amountOfShooters) + 1);
		
		this.setCurrentState(GameState.MAINGAME);
		
		playerCont = new PlayerController(data, view);
		shootCont = new ShootController(data, view);
	}

	public void update() {
		switch(getCurrentState()) {
			case TITLE:
				//if(stateChangesCondition) THEN change game state
				break;
			case MAINGAME:
				data.updateMap();
				nextTurn();
				//if(stateChangesCondition) THEN change game state
				break;
			case PAUSE:
				//if(stateChangesCondition) THEN change game state
				break;
			case GAMEOVER:
				System.out.println("gg wp ;)");
				//if(stateChangesCondition) THEN change game state
				break;
		}
	}
	
	//proximo turno: acabou o tempo ou shooter ja atirou e nao ha nada se movendo no mapa
	private void nextTurn() {
		if (/*time's up! || */ (MapData.getInstance().getCurrentShooter().hasFinishedTurn() && !MapData.getInstance().hasMovingThings())) {
			setCurrentTurn(MapData.getInstance().nextShooterTurn());
			if (MapData.getInstance().amountOfAliveShooters() <= 1)
				setCurrentState(GameState.GAMEOVER);
			else
				MapData.getInstance().getCurrentShooter().newTurn();
		}
	}
	
	public void setInitialConditions() {
		MapData.getInstance().getCurrentShooter().newTurn();
	}
	
	public int getCurrentTurn() {
		return currentTurn;
	}

	public void setCurrentTurn(int currentTurn) {
		this.currentTurn = currentTurn;
	}

	public GameState getCurrentState() {
		return currentState;
	}

	public void setCurrentState(GameState currentState) {
		this.currentState = currentState;
	}

}

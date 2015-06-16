package game.controller;

import game.model.MapData;
import game.model.ShooterSpawner;
import game.view.GameView;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GameController {
	
	private MapData data;
	private GameView view;
	
	private static GameController gc = null;
	
	private GameState currentState;
	
	private ShootController shootCont;
	private PlayerController playerCont;
	private TurnController turnCont;
	private ResetController resetCont;
	
	private ShooterSpawner s;
	
	public static GameController getInstance(MapData data, GameView view) {
		if (gc == null)
			gc = new GameController(data, view);
		return gc;
	}
	
	public static GameController getInstance() {
		return gc;
	}
	
	public void resetController(MapData data, GameView view) {
		gc = new GameController(data, view);
	}
	
	private GameController(MapData data, GameView view) {
		this.data = data;
		this.view = view;
		
		s = new ShooterSpawner(view.amountOfShooters);
		
		this.setCurrentState(GameState.MAINGAME);
		
		playerCont = new PlayerController(data, view);
		shootCont = new ShootController(data, view);
		resetCont = new ResetController(data, view);
		turnCont = TurnController.getInstance(data, view);
	}

	public void update() {
		switch(getCurrentState()) {
			case TITLE:
				//if(stateChangesCondition) THEN change game state
				break;
			case MAINGAME:
				data.updateMap();
				turnCont.updateTurn();
				if (MapData.getInstance().amountOfAliveShooters() <= 1)
					GameController.getInstance().setCurrentState(GameState.GAMEOVER);
				//if(stateChangesCondition) THEN change game state
				break;
			case PAUSE:
				//if(stateChangesCondition) THEN change game state
				break;
			case GAMEOVER:
				data.updateMap();
				String message;
				if (MapData.getInstance().amountOfAliveShooters() == 0)
					message = "Empate!"; 
				else
					message = "Vitória do Player " + MapData.getInstance().getCurrentShooter().getID();
				int option = JOptionPane.showConfirmDialog(new JFrame(), "Play Again?", message, 0);
				if (option == JOptionPane.OK_OPTION)
					resetCont.resetGame();
				else
					System.exit(0);
				break;
		}
	}
	
	public void setInitialConditions() {
		MapData.getInstance().getCurrentShooter().newTurn();
	}
	
	public GameState getCurrentState() {
		return currentState;
	}

	public void setCurrentState(GameState currentState) {
		this.currentState = currentState;
	}

}
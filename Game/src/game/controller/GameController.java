package game.controller;

import game.model.MapData;
import game.view.GameView;

public class GameController {
	
	private MapData data;
	private GameView view;
	
	private GameState currentState;
	
	private ShootController shootCont;
	private PlayerController playerCont;
	
	public GameController(MapData data, GameView view) {
		this.data = data;
		this.view = view;
		
		this.currentState = GameState.TITLE;
		
		playerCont = new PlayerController(data, view);
		shootCont = new ShootController(data, view);
	}

	public void update() {
		switch(currentState) {
			case TITLE:
				data.updateMap();
				//if(stateChangesCondition) THEN change game state
				break;
			case MAINGAME:
				//if(stateChangesCondition) THEN change game state
				break;
			case PAUSE:
				//if(stateChangesCondition) THEN change game state
				break;
			case GAMEOVER:
				//if(stateChangesCondition) THEN change game state
				break;
		}
	}

}

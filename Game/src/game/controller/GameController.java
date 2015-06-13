package game.controller;

import game.model.MapData;
import game.view.GameView;

public class GameController {
	
	private MapData data;
	private GameView view;
	
	private ShootController shootCont;
	private PlayerController playerCont;
	
	public GameController(MapData data, GameView view) {
		this.data = data;
		this.view = view;
		
		playerCont = new PlayerController(); 
		shootCont = new ShootController(data, view);
	}

}

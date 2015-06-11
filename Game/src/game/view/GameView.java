package game.view;

import game.model.GameModel;

public class GameView {
	
	GameModel model;
	
	public GameView(GameModel model){
		this.model = model;
	}
	
	public void render() {
		for (int i = 0; i < model.getMapHeight(); i++)
			System.out.println(model.getMap()[i]);
	}

}

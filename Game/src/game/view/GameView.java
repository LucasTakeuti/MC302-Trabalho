package game.view;

import game.model.GameModel;

public class GameView {
	
	GameModel model;
	GameFrame frame;
	
	public GameView(GameModel model){
		this.model = model;
		this.frame = new GameFrame();
		frame.setVisible(true);
	}
	
	public void renderAscii() {
		
		for (int i = 0; i < model.getMapHeight(); i++)
			System.out.println(model.getMap()[i]);
		
	}
	
}

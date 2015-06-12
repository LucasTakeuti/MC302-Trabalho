package game.view;

import game.model.MapData;

public class GameView {
	
	MapData model;
	GameFrame frame;
	
	public GameView(MapData model){
		this.model = model;
		this.frame = new GameFrame();
		frame.setVisible(true);
	}
	
	public void renderAscii() {
		
		for (int i = 0; i < model.getMapHeight(); i++)
			System.out.println(model.getMap()[i]);
		
	}
	
}

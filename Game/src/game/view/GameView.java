package game.view;

import game.model.MapData;

public class GameView {
	
	MapData data;
	GameFrame frame;
	
	public GameView(MapData model){
		this.data = model;
		this.frame = new GameFrame();
		frame.setVisible(true);
	}
	
	public void renderAscii() {
		
		for (int i = 0; i < data.getMapHeight(); i++)
			System.out.println(data.getMap()[i]);
		
	}
	
}

package game.controller;

import game.model.MapData;
import game.view.GameView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerController {
	
	private MapData data;
	private GameView view;
	
	public PlayerController(MapData data, GameView view) {
		this.data = data;
		this.view = view;
		
		this.view.addJumpListener(new JumpListener());
	}
	
	class JumpListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ev) {
			//TODO: getCurrentShooter() implementation depends on Turns System
			MapData.getInstance().getShooter(1).jump();
		}
	}

}

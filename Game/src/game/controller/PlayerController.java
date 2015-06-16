package game.controller;

import game.model.MapData;
import game.view.GameView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

public class PlayerController {
	
	private MapData data;
	private GameView view;
	private InputManager input = new InputManager();
	
	public PlayerController(MapData data, GameView view) {
		this.data = data;
		this.view = view;
		this.view.addJumpListener(new JumpListener());
		this.view.addkeyboard(input);
		
	}
	
	class JumpListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ev) {
			MapData.getInstance().getCurrentShooter().jump();
		}
	}

}

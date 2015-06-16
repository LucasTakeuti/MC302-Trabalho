package game.controller;

import game.controller.PlayerController.JumpListener;
import game.model.MapData;
import game.view.GameView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResetController {
	
	private MapData data;
	private GameView view;
	
	public ResetController(MapData data, GameView view) {
		this.data = data;
		this.view = view;
		
		this.view.addResetListener(new ResetListener());
	}
	
	class ResetListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ev) {
			MapData.getInstance().resetData();
			GameController.getInstance().resetController(MapData.getInstance(), view);
			view.resetView();
		}
	}
}

package game.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import game.model.MapData;
import game.model.Physics;
import game.view.GameView;

public class ShootController {
	
	private MapData data;
	private GameView view;
	
	public ShootController(MapData data, GameView view) {
		this.data = data;
		this.view = view;
		
		this.view.addShootListener(new ShootListener());
	}
	
	class ShootListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ev) {
			
			double power;
			double angle;
			
			try {
				power = view.getPower();
				angle = view.getAngle()-90;
				
				if (power >= 0 && power <= 100)
					data.getCurrentShooter().basicShoot(Physics.getXComponent(power, angle), Physics.getYComponent(power, angle));
				else
					view.displayErrorMessage("Power [0 ~ 100]");				
			}
			catch (NumberFormatException er) {
				view.displayErrorMessage("Insira um número!!");
			}
		}
	}

}

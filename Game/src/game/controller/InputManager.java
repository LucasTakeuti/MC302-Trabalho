package game.controller;

import game.model.MapData;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputManager implements KeyListener {
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
        if (GameController.getInstance().getCurrentState() == GameState.MAINGAME){
			if (key == KeyEvent.VK_LEFT)
				MapData.getInstance().getCurrentShooter().setVelX(-4);
			if (key == KeyEvent.VK_RIGHT)
				MapData.getInstance().getCurrentShooter().setVelX(4);
			if (key == KeyEvent.VK_SPACE)
				MapData.getInstance().getCurrentShooter().jump();
        }
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int key = e.getKeyCode();
        
		if (GameController.getInstance().getCurrentState() == GameState.MAINGAME){  
			if (key == KeyEvent.VK_LEFT)
				MapData.getInstance().getCurrentShooter().setVelX(0);
			if (key == KeyEvent.VK_RIGHT)
				MapData.getInstance().getCurrentShooter().setVelX(0);
        }
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}



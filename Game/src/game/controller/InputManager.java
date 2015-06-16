package game.controller;

import game.model.MapData;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputManager implements KeyListener {
	


	@Override
	public void keyPressed(KeyEvent e) {
            // Edited this part due to pbl.
            int key = e.getKeyCode();
        if (GameController.getInstance().getCurrentState() == GameState.MAINGAME){
		if (key == KeyEvent.VK_LEFT)
			MapData.getInstance().getCurrentShooter().setVelX(-4);
		if (key == KeyEvent.VK_RIGHT)
			MapData.getInstance().getCurrentShooter().setVelX(4);
		if (key == KeyEvent.VK_UP);
			
		if (key == KeyEvent.VK_DOWN);
		
		if (key == KeyEvent.VK_SPACE)
			MapData.getInstance().getCurrentShooter().jump();
		if (key == KeyEvent.VK_ESCAPE);
        }
	}

	@Override
	public void keyReleased(KeyEvent e) {
            // Edited this part due to pbl.
            int key = e.getKeyCode();
        if (GameController.getInstance().getCurrentState() == GameState.MAINGAME){  
		if (key == KeyEvent.VK_LEFT)
			MapData.getInstance().getCurrentShooter().setVelX(0);
		if (key == KeyEvent.VK_RIGHT)
			MapData.getInstance().getCurrentShooter().setVelX(0);
		if (key == KeyEvent.VK_UP);
			
		if (key == KeyEvent.VK_DOWN);
			
		if (key == KeyEvent.VK_SPACE);
		System.out.println("keyPressed="+KeyEvent.getKeyText(e.getKeyCode()));
        }
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	
	
			
	  

	
}



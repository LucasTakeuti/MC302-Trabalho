package game.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import fagocity.controller.GameController;

public class InputManager implements KeyListener {
	
	public static boolean leftPressed = false;
	public static boolean rightPressed = false;
	public static boolean upPressed = false;
	public static boolean downPressed = false;
	public static boolean spacePressed = false;
	
	private static InputManager inputmanager = null;

	@Override
	public void keyPressed(KeyEvent e) {
            // Edited this part due to pbl.
            int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT)
			leftPressed = true;
		if (key == KeyEvent.VK_RIGHT)
			rightPressed = true;
		if (key == KeyEvent.VK_UP)
			upPressed = true;
		if (key == KeyEvent.VK_DOWN)
			downPressed = true;
		if (key == KeyEvent.VK_SPACE)
			spacePressed = true;
		if (key == KeyEvent.VK_ESCAPE);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
            // Edited this part due to pbl.
            int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT)
			leftPressed = false;
		if (key == KeyEvent.VK_RIGHT)
			rightPressed = false;
		if (key == KeyEvent.VK_UP)
			upPressed = false;
		if (key == KeyEvent.VK_DOWN)
			downPressed = false;
		if (key == KeyEvent.VK_SPACE)
			spacePressed = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	
	public void handleInput() {
			
		if (InputManager.rightPressed)
	
			if (InputManager.leftPressed)

		
	if (InputManager.upPressed)
		
		if (InputManager.downPressed)
				
	if (InputManager.spacePressed) {
		// Jump...or shoot...or...something.
	}
			
	   }

	public static InputManager getInstance() {
		if (inputmanager == null)
			inputmanager = new InputManager();
		return inputmanager;
	}
}



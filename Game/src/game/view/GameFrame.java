package game.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	GameFrame frame;
	GamePanel panel;
	
	public GameFrame() {
		super("Worms Clone");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(new Dimension(400, 400));
		setLocationRelativeTo(null);
		
		panel = new GamePanel();
        //add(panel);
	}

}

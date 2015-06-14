package game.view;

import java.awt.Dimension;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	GameFrame frame;
	
	public GameFrame(int h, int l) {
		super("Worms Clone");
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(new Dimension(h, l));
		setLocationRelativeTo(null);
	}
}

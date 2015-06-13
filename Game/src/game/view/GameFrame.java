package game.view;

import java.awt.Dimension;

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
	
	//metodo paint(Graphics g) e chamado no construtor de JFrames

}

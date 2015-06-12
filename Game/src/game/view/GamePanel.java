package game.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	public void paintComponent(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(0, 0, 400, 400);
        
        g.setColor(Color.WHITE);
        g.fillOval(30, 30, 320, 320);
        
        g.setColor(Color.BLACK);
        g.fillRect(170, 90, 40, 200);
        g.fillRect(90, 170, 200, 40);

        g.fillRect(190, 90, 100, 40);

        g.fillRect(90, 250, 100, 40);
        
        g.fillRect(90, 90, 40, 100);
        
        g.fillRect(250, 190, 40, 100);
    }
}


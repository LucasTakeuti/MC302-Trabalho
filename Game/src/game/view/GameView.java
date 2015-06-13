package game.view;

import game.model.MapData;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextArea;

public class GameView {
	
	MapData data;
	GameFrame frame;
	JTextArea text;
	
	public GameView(MapData data){
		this.data = data;
		
		this.frame = new GameFrame();
		frame.setVisible(true);
		
		text = new JTextArea();
		text.setColumns(data.getMapWidth());
		text.setRows(data.getMapHeight());
		text.setEditable(false);
		
		Font f = new Font("Courier", 3, 8); //monospace, negrito, tamanho 8
		
		text.setFont(f);
		text.setBackground(Color.BLACK);
		text.setForeground(new Color(255, 255, 255, 200));
		
		frame.add(text);
	}
	
	public void renderAsciiConsole() {
		
		for (int i = 0; i < data.getMapHeight(); i++)
			System.out.println(data.getMap()[i]);
		
	}
	
	public void renderAsciiFrame() {
		
		StringBuilder s = new StringBuilder();
		
		for (int i = 0; i < data.getMapHeight(); i++) {
			s.append(new String(data.getMap()[i]));
			s.append(System.getProperty("line.separator"));
		}
		
		String ss = s.toString();
		
		text.setText(ss);
		
		text.repaint();
		
	}
	
}

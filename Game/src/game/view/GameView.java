package game.view;

import game.model.MapData;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GameView {
	
	private MapData data;
	
	private GameFrame frame;
	
	private GamePanel panel;
	
	private JTextArea screenAscii;
	private JTextField powerField;
	private JTextField angleField;
	private JButton shootButton;
	
	public GameView(MapData data){
		this.data = data;
		
		this.frame = new GameFrame();
		frame.setVisible(true);
		
		//tests - nao apague//
		screenAscii = new JTextArea();
		screenAscii.setColumns(data.getMapWidth());
		screenAscii.setRows(data.getMapHeight());
		screenAscii.setEditable(false);
		
		Font f = new Font("Courier", 3, 10); //monospace, negrito, tamanho 8
		
		screenAscii.setFont(f);
		screenAscii.setBackground(Color.BLACK);
		screenAscii.setForeground(new Color(255, 255, 255, 200));
		screenAscii.setSize(data.getMapWidth(), data.getMapHeight());
		
		
		//tests - nao apague//
		panel = new GamePanel();
		
		powerField = new JTextField("22", 5);
		angleField = new JTextField("55", 5);
		
		shootButton = new JButton("Shoot!");
		
		panel.add(screenAscii);
		panel.add(powerField);
		panel.add(angleField);
		panel.add(shootButton);
		
		frame.add(panel);
		
		shootButton.requestFocus();
		
	}
	
	public double getPower() {
		return Double.parseDouble(powerField.getText());
	}
	
	public double getAngle() {
		return Double.parseDouble(angleField.getText());
	}
	
	public void addShootListener(ActionListener shoot) {
		shootButton.addActionListener(shoot);
	}
	
	public void displayErrorMessage(String errorMessage) {
		JOptionPane.showMessageDialog(frame, errorMessage);
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
		
		screenAscii.setText(ss);
		
		screenAscii.repaint();
		
	}
	
}

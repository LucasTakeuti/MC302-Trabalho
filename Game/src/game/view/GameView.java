package game.view;

import game.controller.TurnController;
import game.model.MapData;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GameView {
	
	private MapData data;
	
	private GameFrame frame;
	
	private GamePanel panel;
	
	// tests - nao apague
	private JTextArea screenAscii;
	private JTextField powerField;
	private JTextField angleField;
	private JButton shootButton;
	private JButton jumpButton;
	private JTextArea lifes;
	private JTextArea currentTurn;
	
	//TODO: Modificar a maneira como amoutOfShooters eh acessada pelo GameController
	public int amoutOfShooters;
	
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
		jumpButton = new JButton("Jump!");
		
		lifes = new JTextArea();
		currentTurn = new JTextArea();
		
		panel.add(screenAscii);
		panel.add(powerField);
		panel.add(angleField);
		panel.add(shootButton);
		panel.add(jumpButton);

		panel.add(lifes);
		panel.add(currentTurn);
		
		frame.add(panel);
		
		amoutOfShooters = Integer.valueOf(JOptionPane.showInputDialog(new JFrame(), "Amount of Shooters", "2"));
		
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
	
	public void addJumpListener(ActionListener jump) {
		jumpButton.addActionListener(jump);
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
		
		//tests
		lifes.setText("1: " + Double.toString(Math.round(data.getShooter(1).getLife())) + " 2: " + Double.toString(Math.round(data.getShooter(2).getLife())));
		currentTurn.setText("Current Turn: " + Integer.toString(TurnController.getInstance().getCurrentTurn()));
		
	}
	
}

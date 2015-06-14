package game.view;

import game.model.MapData;
import game.view.controles.Controle;
import game.view.desenhador.Desenhador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GameView {
	
	private MapData data;
	
	private GameFrame frame;
	
	private Desenhador panel;
	private Controle panel2;
	
	public GameView(MapData data){
		this.data = data;
		
		this.frame = new GameFrame(500, 350);
		frame.setVisible(true);
		frame.setLayout(new BorderLayout());
		
		//tests - nao apague//
		panel = new Desenhador(data);
		panel2 = new Controle();
		
		frame.add(panel, BorderLayout.WEST);
		frame.add(panel2, BorderLayout.EAST);
		
		panel2.shootButton.requestFocus();
	}
	
	public double getPower() {
		return panel2.getPower();
	}
	
	public double getAngle() {
		return panel2.getAngle();
	}
	
	public void addShootListener(ActionListener shoot) {
		panel2.shootButton.addActionListener(shoot);
	}
	
	public void displayErrorMessage(String errorMessage) {
		JOptionPane.showMessageDialog(frame, errorMessage);
	}
	
	public void render() {
		panel.desenhar();
	}
	
}

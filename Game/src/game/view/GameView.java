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
	
	private GameFrame frame;
	
	private Desenhador desenhador;
	private Controle controle;
	
	public GameView(MapData data){
		
		this.frame = new GameFrame(500, 350);
		frame.setVisible(true);
		frame.setLayout(new BorderLayout());
		
		desenhador = new Desenhador(data);
		controle = new Controle();
		
		frame.add(desenhador, BorderLayout.WEST);
		frame.add(controle, BorderLayout.EAST);
		
		controle.bttnFocus();
	}
	
	public double getPower() {
		return controle.getPower();
	}
	
	public double getAngle() {
		return controle.getAngle();
	}
	
	public void addShootListener(ActionListener shoot) {
		controle.addListener(shoot);
	}
	
	public void displayErrorMessage(String errorMessage) {
		JOptionPane.showMessageDialog(frame, errorMessage);
	}
	
	public void render() {
		desenhador.desenhar();
	}
	
}

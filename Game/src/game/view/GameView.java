package game.view;

import game.model.MapData;
import game.view.controles.Controle;
import game.view.desenhador.Desenhador;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GameView {
	
	private GameFrame frame;
	
	private Desenhador desenhador;
	private Controle controle;
	
	public int amoutOfShooters;
	
	public GameView(MapData data){
		
		this.frame = new GameFrame(400, 380);
		frame.setVisible(true);
		frame.setLayout(new BorderLayout());
		
		desenhador = new Desenhador(data);
		controle = new Controle();
		
		frame.add(desenhador, BorderLayout.NORTH);
		frame.add(controle, BorderLayout.SOUTH);
		
		controle.bttnFocus();
		
		amoutOfShooters = Integer.valueOf(JOptionPane.showInputDialog(new JFrame(), "Amount of Shooters", "2"));
	}
	
	public double getPower() {
		return controle.getPower();
	}
	
	public double getAngle() {
		return controle.getAngle();
	}
	
	public void addShootListener(ActionListener shoot) {
		controle.addShootListener(shoot);
	}

	public void addJumpListener(ActionListener jump) {
		controle.addJumpListener(jump);
	}
	
	public void displayErrorMessage(String errorMessage) {
		JOptionPane.showMessageDialog(frame, errorMessage);
	}
	
	public void render() {
		desenhador.desenhar();
	}
}

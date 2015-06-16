package game.view;

import game.controller.InputManager;
import game.controller.TurnController;
import game.model.MapData;
import game.view.controles.Controle;
import game.view.desenhador.Cor;
import game.view.desenhador.Desenhador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GameView {
	
	private GameFrame frame;
	private MapData data;
	
	private Desenhador desenhador;
	private Controle controle;
	
	public int amountOfShooters;
	
	public GameView(MapData data){
		this.data = data;
		
		int canvasHeight = data.getMapHeight() * 10;
		int canvasWidth = data.getMapWidth() * 10;
		
		
		this.frame = new GameFrame(canvasWidth, canvasHeight + 70);
		frame.setVisible(true);
		frame.setLayout(new BorderLayout());
		
		// Criando lista com as cores.
		ArrayList<Cor> cores = new ArrayList<Cor>();
		cores.add(new Cor("g", Color.green));
		cores.add(new Cor("~", Color.blue));
		cores.add(new Cor("1", Color.red));
		cores.add(new Cor("2", Color.orange));
		
		desenhador = new Desenhador(data, cores, canvasHeight, canvasWidth);
		controle = new Controle();
		
		frame.add(desenhador, BorderLayout.NORTH);
		frame.add(controle, BorderLayout.SOUTH);
		
		controle.bttnFocus();
		
		amountOfShooters = Integer.valueOf(JOptionPane.showInputDialog(new JFrame(), "Amount of Shooters", "2"));
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

	public void resetView() {
		this.data = MapData.getInstance();
	}
	
	public void addResetListener(ActionListener reset) {
		controle.addResetListener(reset);
	}
	
	public void displayErrorMessage(String errorMessage) {
		JOptionPane.showMessageDialog(frame, errorMessage);
	}
	
	public void addkeyboard(InputManager input){
		KeyListener listener = input;
		desenhador.addKeyListener(listener);
		desenhador.setFocusable(true);
		desenhador.requestFocus();
	}
	
	public void render() {
		StringBuilder vidas = new StringBuilder();
		
		desenhador.desenhar();
		
		for (int i = 1; i <= amountOfShooters; i++) {
			vidas.append(i);
			vidas.append(": ");
			vidas.append(Math.round(data.getShooter(i).getLife()));
			vidas.append(" ");
		}
		
		controle.setLifes(vidas.toString());
		controle.setCurrentTurn("Current Turn: " + Integer.toString(TurnController.getInstance().getCurrentTurn()));
	}
}

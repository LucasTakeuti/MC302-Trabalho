package game.view;

import game.controller.InputManager;
import game.controller.TurnController;
import game.model.MapData;
import game.view.controles.Controle;
import game.view.desenhador.Cor;
import game.view.desenhador.Desenhador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

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
	
	public void renderAsciiConsole() {
		for (int i = 0; i < data.getMapHeight(); i++)
			System.out.println(data.getMap()[i]);
	}
	
	public void renderAsciiFrame() {
		
		JTextArea screenAscii = new JTextArea();
		screenAscii.setColumns(data.getMapWidth());
		screenAscii.setRows(data.getMapHeight());
		screenAscii.setEditable(false);
		
		Font f = new Font("Courier", 3, 10); //monospace, negrito, tamanho 8
		
		screenAscii.setFont(f);
		screenAscii.setBackground(Color.BLACK);
		screenAscii.setForeground(new Color(255, 255, 255, 200));
		screenAscii.setSize(data.getMapWidth(), data.getMapHeight());
		
		frame.add(screenAscii);
		
		StringBuilder s = new StringBuilder();
		
		for (int i = 0; i < data.getMapHeight(); i++) {
			s.append(new String(data.getMap()[i]));
			s.append(System.getProperty("line.separator"));
		}
		
		String ss = s.toString();
		
		screenAscii.setText(ss);
		
		//tests
		//setText("1: " + Double.toString(Math.round(data.getShooter(1).getLife())) + " 2: " + Double.toString(Math.round(data.getShooter(2).getLife())));
		//currentTurn.setText("Current Turn: " + Integer.toString(TurnController.getInstance().getCurrentTurn()));
	}
}

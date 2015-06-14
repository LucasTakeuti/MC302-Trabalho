package game.view.desenhador;

import game.model.MapData;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Desenhador extends JPanel{
	private ArrayList<Cor> cores;
	private int h;
	private int l;
	//private BufferedImage quadro;
	private JTextArea screenAscii;
	private MapData mapa;
	
	public Desenhador(MapData data) {
		this.mapa = data;
		
		this.h = mapa.getMapHeight();
		this.l = mapa.getMapWidth();
		
		screenAscii = new JTextArea();
		screenAscii.setColumns(l);
		screenAscii.setRows(h);
		screenAscii.setEditable(false);
		
		Font f = new Font("Courier", 3, 10); //monospace, negrito, tamanho 8
		
		screenAscii.setFont(f);
		screenAscii.setBackground(Color.BLACK);
		screenAscii.setForeground(new Color(255, 255, 255, 200));
		screenAscii.setSize(l, h);
		
		add(screenAscii);
	}
	
	public Desenhador(MapData mapa, ArrayList<Cor> cores, int hTela, int lTela) {
		for (int i = 0; i < cores.size(); i++) {
			this.cores.add(cores.get(i));
		}
		this.h = mapa.getMapHeight();
		this.l = mapa.getMapWidth();
		
		
	}
	
	public void desenhar(){
		StringBuilder s = new StringBuilder();
		
		for (int i = 0; i < h; i++) {
			s.append(new String(mapa.getMap()[i]));
			s.append(System.getProperty("line.separator"));
		}
		
		String ss = s.toString();
		
		screenAscii.setText(ss);
		
		screenAscii.repaint();
	}
}

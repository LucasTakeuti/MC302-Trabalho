package game.view.desenhador;

import game.model.MapData;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Desenhador extends Canvas{
	private ArrayList<Cor> cores;
	private int h;
	private int l;
	private int hPixel;
	private int lPixel;
	private JTextArea screenAscii;
	private MapData mapa;
	
	public Desenhador(MapData data) {
		this.mapa = data;
		
		this.h = mapa.getMapHeight() * 10;
		this.l = mapa.getMapWidth() * 10;
		
		Dimension size = new Dimension(l, h);
		setPreferredSize(size);
		
		/*screenAscii = new JTextArea();
		screenAscii.setColumns(l);
		screenAscii.setRows(h);
		screenAscii.setEditable(false);
		
		Font f = new Font("Courier", 3, 10); //monospace, negrito, tamanho 8
		
		screenAscii.setFont(f);
		screenAscii.setBackground(Color.BLACK);
		screenAscii.setForeground(new Color(255, 255, 255, 200));
		screenAscii.setSize(l, h);*/
		
		cores = new ArrayList<Cor>();
		cores.add(new Cor("vermelho", Color.RED));
	}
	
	public Desenhador(MapData mapa, ArrayList<Cor> cores, int hTela, int lTela) {
		this.cores = cores;
		this.mapa = mapa;
		this.h = hTela;
		this.l = lTela;
		
		Dimension size = new Dimension(l, h);
		setPreferredSize(size);
		
		this.hPixel = (int)(hTela / mapa.getMapHeight());
		this.lPixel = (int)(lTela / mapa.getMapWidth());
	}
	
	public void desenhar() {
		boolean flag = false;
		Color cor = Color.black;
		
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		for (int x = 0; x < mapa.getMapWidth(); x++) {
			for (int y = 0; y < mapa.getMapHeight(); y++) {
				cor = Color.black;
				flag = false;
				for (int i = 0; i < cores.size() && (!flag); i++) {
					if (cores.get(i).getId().equals(Character.toString(mapa.getMap()[y][x]))){
						cor = cores.get(i).getCor();
						flag = true;
					}
				}
				g.setColor(cor);
				g.fillRect((x * lPixel), (y * hPixel), lPixel, hPixel);
			}
		}
		g.dispose();
		bs.show();
		
		/*StringBuilder s = new StringBuilder();
		
		for (int i = 0; i < h; i++) {
			s.append(new String(mapa.getMap()[i]));
			s.append(System.getProperty("line.separator"));
		}
		
		String ss = s.toString();
		
		screenAscii.setText(ss);
		
		screenAscii.repaint();*/
	}
}

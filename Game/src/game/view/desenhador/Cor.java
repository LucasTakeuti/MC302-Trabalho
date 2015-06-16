package game.view.desenhador;

import java.awt.Color;

public class Cor {
	
	private String id;
	private Color cor;
	
	public Cor(String id, Color cor) {
		this.id = id;
		this.cor = cor;
	}
	
	public String getId() {
		return id;
	}
	
	public Color getCor() {
		return cor;
	}
}
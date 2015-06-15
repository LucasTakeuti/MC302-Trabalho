package game.view.desenhador;

import java.awt.Color;

public class Cor {
	
	private String id;
	private int rgb;
	
	public Cor(String id, Color rgb) {
		this.id = id;
		this.rgb = rgb.getRGB();
	}
	
	public String getId() {
		return id;
	}
	
	public int getRgb() {
		return rgb;
	}
}
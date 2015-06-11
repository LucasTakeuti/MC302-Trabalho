package game.model;

import game.ReadFile;

public class GameModel {
	
	private ReadFile reader = new ReadFile("mapa.txt");
	private int[] mapDim = reader.fileLength();
	private char[][] map = new char[mapDim[0]][mapDim[1]];
	
	public GameModel() {
		map = reader.readFile();
	}
	
	public int getMapHeight(){
		return mapDim[0];
	}
	
	public int getMapWidth(){
		return mapDim[1];
	}
	
	public char[][] getMap(){
		return map;
	}
}

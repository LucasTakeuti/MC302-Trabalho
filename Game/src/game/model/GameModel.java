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
		return mapCopy();
	}
	
	private char[][] mapCopy(){
		
		char [][] copy = new char[mapDim[0]][mapDim[1]];
		
		for (int i = 0; i < mapDim[0]; i++) {
			for (int j = 0; j < mapDim[1]; j++) {
				copy[i][j] = map[i][j];
			}
		}
		return copy;
	}
}

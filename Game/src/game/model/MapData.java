package game.model;

import game.ReadFile;

import java.util.ArrayList;

public class MapData {
	
	private static MapData m = null;
	
	private ReadFile reader = new ReadFile("mapa.txt");
	private int[] mapDim = reader.fileLength();
	private char[][] map = new char[mapDim[0]][mapDim[1]];
	private char[][] terrain = new char[mapDim[0]][mapDim[1]];
	private ArrayList<Physicable> PhysicsList;
	private CollisionLogic collider;
	
	public static MapData getInstance() {
		if (m == null)
			m = new MapData();
		return m;
	}
	
	private MapData() {
		PhysicsList = new ArrayList<Physicable>();
		terrain = reader.readFile();
		map = mapCopy(terrain);
		collider = new CollisionLogic(terrain);
	}
	
	public void updateMap() {
		
		resetMap();
		
		for (int i = 0; i < PhysicsList.size(); i++)
			PhysicsList.get(i).update();
		
		collider.checkCollision(getPhysicsList());
		
		for (int i = 0; i < PhysicsList.size(); i++)
			putIntoMap(PhysicsList.get(i));
	}
	
	private void putIntoMap(Physicable obj) {
		
		if (obj.isVisible() && !(collider.hasHitBounds(obj))) {
			if (obj instanceof Projectile) {
				map[obj.getYfloor()][obj.getXfloor()] = 'p';
			}
			
			else if (obj instanceof Shooter) {
					map[obj.getYfloor()][obj.getXfloor()] = (char)(((Shooter) obj).getID() + '0');
			}
		}
	}
	
	public Shooter getShooter(int id) {
		System.out.println("id: " + id);
		for (int i = 0; i < PhysicsList.size(); i++) {
			if (PhysicsList.get(i) instanceof Shooter) {
				Shooter s = (Shooter) PhysicsList.get(i);
				if (id == s.getID())
					return s;
			}
		}
		return null;
	}
	
	public boolean isShooter(int i, int j) {
		System.out.println("i: " + i + " j: " + j);
		for (int k = 0; k < PhysicsList.size(); k++) {
			if (PhysicsList.get(k) instanceof Shooter) {
				Shooter s = (Shooter) PhysicsList.get(k);
				if (i == s.getYfloor() && j == s.getXfloor())
					return true;
			}
		}
		return false;
	}

	public void addToObservedList(Physicable p) {
		PhysicsList.add(p);
	}
	
	public void deleteFromObservedList(Physicable p) {
		PhysicsList.remove(p);
	}
	
	public ArrayList<Physicable> getPhysicsList() {
		return PhysicsList;
	}
	
	public int getMapHeight() {
		return mapDim[0];
	}
	
	public int getMapWidth() {
		return mapDim[1];
	}
	
	public char[][] getTerrain() {
		return terrain;
	}
	
	public char[][] getMap() {
		return mapCopy(map);
	}
	
	private void resetMap() {
		map = mapCopy(terrain);
	}
	
	private char[][] mapCopy(char[][] map) {
		
		char [][] copy = new char[mapDim[0]][mapDim[1]];
		
		for (int i = 0; i < mapDim[0]; i++) {
			for (int j = 0; j < mapDim[1]; j++) {
				copy[i][j] = map[i][j];
			}
		}
		return copy;
	}

}

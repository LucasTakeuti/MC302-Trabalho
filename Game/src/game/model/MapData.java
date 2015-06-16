package game.model;

import game.ReadFile;
import game.SaveGame;
import game.controller.TurnController;
import game.interfaces.IPhysicable;
import game.interfaces.IProjectile;

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
	
	public void resetData() {
		while (PhysicsList.size() > 0)
			PhysicsList.remove(PhysicsList.size()-1);
		terrain = reader.readFile();
		collider = new CollisionLogic(terrain);
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
	
	private void putIntoMap(IPhysicable obj) {
		
		if (obj.isVisible() && !(collider.hasHitBounds(obj))) {
			if (obj instanceof IProjectile) {
				map[obj.getYfloor()][obj.getXfloor()] = 'p';
			}
			
			else if (obj instanceof Shooter && ((Shooter) obj).isAlive()) {
				map[obj.getYfloor()][obj.getXfloor()] = (char)(((Shooter) obj).getID() + '0');
			}
		}
	}
	
	public Shooter getCurrentShooter() {
		if (getShooter(TurnController.getInstance().getCurrentTurn()) != null)
			return getShooter(TurnController.getInstance().getCurrentTurn());
		return new Shooter(0, 0);
	}
	
	public Shooter getShooter(int id) {
		for (int i = 0; i < PhysicsList.size(); i++) {
			if (PhysicsList.get(i) instanceof Shooter) {
				Shooter s = (Shooter) PhysicsList.get(i);
				if (id == s.getID())
					return s;
			}
		}
		return new Shooter(0, 0);
	}
	
	public int getShooterID(int row, int column) {
		for (int k = 0; k < PhysicsList.size(); k++) {
			if (PhysicsList.get(k) instanceof Shooter) {
				Shooter s = (Shooter) PhysicsList.get(k);
				if (row == s.getYfloor() && column == s.getXfloor())
					return s.getID();
			}
		}
		return -1;
	}
	
	public boolean isShooter(int row, int column) {
		for (int k = 0; k < PhysicsList.size(); k++) {
			if (PhysicsList.get(k) instanceof Shooter) {
				Shooter s = (Shooter) PhysicsList.get(k);
				if (row == s.getYfloor() && column == s.getXfloor())
					return true;
			}
		}
		return false;
	}
	
	public boolean hasMovingThings() {
		for (int k = 0; k < PhysicsList.size(); k++) {
			if (PhysicsList.get(k).isFalling())
				return true;
		}
		return false;
	}
	
	public int amountOfAliveShooters() {
		
		int amount = 0;
		
		for (int k = 0; k < PhysicsList.size(); k++)
			if (PhysicsList.get(k) instanceof Shooter && ((Shooter) PhysicsList.get(k)).isAlive())
				amount++;
		
		return amount;
	}

	public void addToObservedList(Physicable p) {
		PhysicsList.add(p);
	}
	
	public void deleteFromObservedList(IPhysicable p) {
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

package game.model;

import java.util.ArrayList;

public class CollisionLogic {
	
	private char[][] terrain;
	
	public CollisionLogic(char[][] terrain) {
		this.terrain = terrain;
	}
	
	public void checkCollision(ArrayList<Physicable> list) {
		
		for (int i = 0; i < list.size(); i++) {
			Physicable obj = list.get(i);
			if (hasCollision(obj)) {
				if (obj instanceof Projectile) {
					MapData.getInstance().deleteFromObservedList(obj);
					destroyTerrain(obj, ((Projectile) obj).getExplosionRadius());
				}
			}
		}
	}
	
	private boolean hasCollision(Physicable obj) {
		
		if (obj.getYfloor() >= MapData.getInstance().getMapHeight() || obj.getYfloor() <= 0 ||
			obj.getXfloor() >= MapData.getInstance().getMapWidth() || obj.getXfloor() <= 0 ||
			terrain[obj.getYfloor()][obj.getXfloor()] == 't')
			return true;
		else
			return false;
		
	}
	
	private void destroyTerrain(Physicable obj, int explosionRadius) {
		for (int i = Math.max(0, obj.getYfloor() - explosionRadius); i < Math.min(MapData.getInstance().getMapHeight(), obj.getYfloor() + explosionRadius); i++)
			for (int j = Math.max(0, obj.getXfloor() - explosionRadius); j < Math.min(MapData.getInstance().getMapWidth(), obj.getXfloor() + explosionRadius); j++)
				if (distanceTo(i, j, obj.getYfloor(), obj.getXfloor()) <= explosionRadius)
					terrain[i][j] = 'a';
	}
	
	private double distanceTo(int x1, int y1, int x2, int y2) {
		return (Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2)));
	}

}

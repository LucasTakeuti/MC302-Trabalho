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
		return (hasHitBounds(obj) || hasHitSolid(obj));
	}
	
	private boolean hasHitBounds(Physicable obj) {
		return (obj.getYfloor() >= MapData.getInstance().getMapHeight() || obj.getYfloor() <= 0 ||
				obj.getXfloor() >= MapData.getInstance().getMapWidth() || obj.getXfloor() <= 0);
	}
	
	private boolean hasHitSolid(Physicable obj) {
		return (Physics.solids.contains(terrain[obj.getYfloor()][obj.getXfloor()]));
	}
	
	private void destroyTerrain(Physicable obj, int explosionRadius) {
		for (int i = Math.max(0, obj.getYfloor() - explosionRadius); i < Math.min(MapData.getInstance().getMapHeight(), obj.getYfloor() + explosionRadius); i++)
			for (int j = Math.max(0, obj.getXfloor() - explosionRadius); j < Math.min(MapData.getInstance().getMapWidth(), obj.getXfloor() + explosionRadius); j++)
				if (distanceTo(i, j, obj.getYfloor(), obj.getXfloor()) <= explosionRadius)
					terrain[i][j] = Physics.fluids.get(Physics.DEFAULT_FLUID);
	}
	
	private double distanceTo(int x1, int y1, int x2, int y2) {
		return (Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2)));
	}

}

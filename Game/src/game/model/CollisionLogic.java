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
			
			if (obj instanceof Projectile) {
				if (hasHitSolid(obj)) {
					destroyTerrain(obj, ((Projectile) obj).getExplosionRadius());
					MapData.getInstance().deleteFromObservedList(obj);
				}
				if (hasHitFloorBounds(obj))
					MapData.getInstance().deleteFromObservedList(obj);
			}
			
			if (obj instanceof Shooter) {
				if (hasHitFloorBounds(obj)) {
					obj.setY(obj.getYfloor()-1);
					obj.setVelX(0);
					obj.setAccelY(0);
					obj.setVisible(false);
				}
				
				else if (hasHitRightBounds(obj)) {
					obj.setX(obj.getXfloor()-1);
					obj.setVelX(0);
					obj.setAccelX(0);
				}
				
				else if (hasHitLeftBounds(obj)) {
					obj.setX(0);
					obj.setVelX(0);
					obj.setAccelX(0);
				}
				
				else if (hasHitSolid(obj)) {
					obj.setY(obj.getYfloor()-1);
					obj.setVelX(0);
					obj.setAccelY(0);
				}
			}
		}
	}
	
	public boolean hasHitBounds(Physicable obj) {
		return hasHitFloorBounds(obj) || hasHitSkyBounds(obj) || hasHitRightBounds(obj) || hasHitLeftBounds(obj);
	}
	
	private boolean hasHitFloorBounds(Physicable obj) {
		return (obj.getYfloor() >= MapData.getInstance().getMapHeight());
	}
	
	private boolean hasHitSkyBounds(Physicable obj) {
		return (obj.getYfloor() < 0);
	}
	
	private boolean hasHitRightBounds(Physicable obj) {
		return (obj.getXfloor() >= MapData.getInstance().getMapWidth());
	}
	
	private boolean hasHitLeftBounds(Physicable obj) {
		return (obj.getXfloor() < 0);
	}	
	
	private boolean hasHitSolid(Physicable obj) {
		if (!hasHitBounds(obj))
			return (Physics.solids.contains(terrain[obj.getYfloor()][obj.getXfloor()]));
		else
			return false;
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

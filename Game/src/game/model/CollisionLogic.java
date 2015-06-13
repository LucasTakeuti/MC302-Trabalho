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
					doDamage(obj, ((Projectile) obj).getExplosionRadius());
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
				if (distanceofAToB(i, j, obj.getYfloor(), obj.getXfloor()) <= explosionRadius)
					terrain[i][j] = Physics.fluids.get(Physics.DEFAULT_FLUID);
	}
	
	private void doDamage(Physicable obj, int explosionRadius) {
		for (int i = Math.max(0, obj.getYfloor() - explosionRadius); i < Math.min(MapData.getInstance().getMapHeight(), obj.getYfloor() + explosionRadius); i++)
			for (int j = Math.max(0, obj.getXfloor() - explosionRadius); j < Math.min(MapData.getInstance().getMapWidth(), obj.getXfloor() + explosionRadius); j++)
				if (distanceofAToB(i, j, obj.getYfloor(), obj.getXfloor()) <= explosionRadius)
					if (MapData.getInstance().isShooter(i, j)) {
						System.out.println("char: " + MapData.getInstance().getMap()[j][i]);
						//MapData.getInstance().getShooter(Character.getNumericValue(MapData.getInstance().getMap()[i][j]).setLife(0);
					}
	}
	
	private double distanceofAToB(int xa, int ya, int xb, int yb) {
		return (Math.sqrt((xa-xb)*(xa-xb) + (ya-yb)*(ya-yb))); 
	}

}

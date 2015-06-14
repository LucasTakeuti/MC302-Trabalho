package game.model;

import java.util.ArrayList;

public class CollisionLogic {
	
	private char[][] terrain;
	private DamageHandler damage;
	
	public CollisionLogic(char[][] terrain) {
		this.terrain = terrain;
	}
	
	//TODO: Deletar projeteis lancados para fora do mapa de maneira irreversivel
	public void checkCollision(ArrayList<Physicable> list) {
		
		for (int i = 0; i < list.size(); i++) {
			Physicable obj = list.get(i);
			
			if (obj instanceof Projectile) {
				if (hasHitSolid(obj)) {
					destroyTerrain(obj, ((Projectile) obj).getExplosionRadius());
					damage = new DamageHandler(obj, ((Projectile) obj).getExplosionRadius());
					damage.doDamage();
					MapData.getInstance().deleteFromObservedList(obj);
				}
				if (hasHitFloorBounds(obj))
					MapData.getInstance().deleteFromObservedList(obj);
			}
			
			if (obj instanceof Shooter) {
				if (hasHitFloorBounds(obj)) {
					obj.setY(obj.getYfloor()-1);
					obj.setVelX(0);
					obj.setMoving(false);
					obj.setVisible(false);
					((Shooter) obj).setAlive(false);
				}
				
				else if (hasHitRightBounds(obj)) {
					obj.setX(obj.getXfloor()-1);
					obj.setVelX(0);
					obj.stopAccelX();
				}
				
				else if (hasHitLeftBounds(obj)) {
					obj.setX(0);
					obj.setVelX(0);
					obj.stopAccelX();
				}
				
				else if (hasHitSolid(obj)) {
					obj.setY(obj.getYfloor()-1);
					obj.setVelX(0);
					obj.setMoving(false);
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
	
	private boolean hasHitTerrain(Physicable obj) {
		return (Physics.solids.contains(terrain[obj.getYfloor()][obj.getXfloor()]));
	}
	
	private boolean hasHitPhysicable(Physicable obj) {
		for (int k = 0; k < MapData.getInstance().getPhysicsList().size(); k++) {
			Physicable p = MapData.getInstance().getPhysicsList().get(k);
			if (p != obj && p.getYfloor() == obj.getYfloor() && p.getXfloor() == obj.getXfloor()) {
				return true;
			}
		}
		return false;
	}
	
	private boolean hasHitSolid(Physicable obj) {
		if (!hasHitBounds(obj))
			if (hasHitTerrain(obj) || hasHitPhysicable(obj))
				return true;
		return false;
	}
	
	private void destroyTerrain(Physicable obj, int explosionRadius) {
		for (int i = Math.max(0, obj.getYfloor() - explosionRadius); i < Math.min(MapData.getInstance().getMapHeight(), obj.getYfloor() + explosionRadius); i++)
			for (int j = Math.max(0, obj.getXfloor() - explosionRadius); j < Math.min(MapData.getInstance().getMapWidth(), obj.getXfloor() + explosionRadius); j++)
				if (Physics.distanceofAToB(i, j, obj.getYfloor(), obj.getXfloor()) <= explosionRadius)
					terrain[i][j] = Physics.fluids.get(Physics.DEFAULT_FLUID);
	}

}

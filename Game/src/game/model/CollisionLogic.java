package game.model;

import game.interfaces.IPhysicable;
import game.interfaces.IProjectile;

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
			IPhysicable obj = list.get(i);
			
			if (obj instanceof IProjectile) {
				if (hasHitSolid(obj)) {
					destroyTerrain(obj, ((IProjectile) obj).getExplosionRadius());
					damage = new DamageHandler(obj, ((IProjectile) obj).getExplosionRadius());
					damage.doDamage();
					MapData.getInstance().deleteFromObservedList(obj);
				}
				//deleta o projetil se ele for muito longe
				else if (hasHitFloorBounds(obj) || ((hasHitRightBounds(obj) || hasHitLeftBounds(obj)) && (Math.floor(obj.getVelY()) == 0)) || 
					(Physics.distanceofAToB(obj.getXfloor(), obj.getYfloor(), MapData.getInstance().getCurrentShooter().getXfloor(), MapData.getInstance().getCurrentShooter().getYfloor()) > Physics.MAX_PROJECTILE_DISTANCE))
					MapData.getInstance().deleteFromObservedList(obj);
			}
			
			if (obj instanceof Shooter) {
				if (hasHitFloorBounds(obj)) {
					((Shooter) obj).setAlive(false);
					obj.setFalling(false);
					obj.setVisible(false);
					obj.setY(obj.getYfloor()-1);
					obj.setVelX(0);
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
					obj.setFalling(false);
					if (hasHitTerrain(obj))
						if (obj.getVelY() <= 0) {
							obj.setY(obj.getYfloor()+1);
							obj.setFalling(true);
						}
						else
							obj.setY(obj.getYfloor()-1);
					if (obj.getVelX() != 0 && ((hasHitLeftBounds(obj) && Physics.solids.contains(MapData.getInstance().getMap()[obj.getYfloor()][obj.getXfloor()-1])) || 
						(hasHitRightBounds(obj) && Physics.solids.contains(MapData.getInstance().getMap()[obj.getYfloor()][obj.getXfloor()+1]))))
						obj.setVelX(0);
				}				
			}
		}
	}
	
	public boolean hasHitBounds(IPhysicable obj) {
		return hasHitFloorBounds(obj) || hasHitSkyBounds(obj) || hasHitRightBounds(obj) || hasHitLeftBounds(obj);
	}
	
	private boolean hasHitFloorBounds(IPhysicable obj) {
		return (obj.getYfloor() >= MapData.getInstance().getMapHeight());
	}
	
	private boolean hasHitSkyBounds(IPhysicable obj) {
		return (obj.getYfloor() < 0);
	}
	
	private boolean hasHitRightBounds(IPhysicable obj) {
		return (obj.getXfloor() >= MapData.getInstance().getMapWidth());
	}
	
	private boolean hasHitLeftBounds(IPhysicable obj) {
		return (obj.getXfloor() < 0);
	}	
	
	private boolean hasHitTerrain(IPhysicable obj) {
		return (Physics.solids.contains(terrain[obj.getYfloor()][obj.getXfloor()]));
	}
	
	private boolean hasHitPhysicable(IPhysicable obj) {
		for (int k = 0; k < MapData.getInstance().getPhysicsList().size(); k++) {
			IPhysicable p = MapData.getInstance().getPhysicsList().get(k);
			if (p != obj && p.getYfloor() == obj.getYfloor() && p.getXfloor() == obj.getXfloor()) {
				return true;
			}
		}
		return false;
	}
	
	private boolean hasHitSolid(IPhysicable obj) {
		if (!hasHitBounds(obj))
			if (hasHitTerrain(obj) || hasHitPhysicable(obj))
				return true;
		return false;
	}
	
	private void destroyTerrain(IPhysicable obj, int explosionRadius) {
		for (int i = Math.max(0, obj.getYfloor() - explosionRadius); i < Math.min(MapData.getInstance().getMapHeight(), obj.getYfloor() + explosionRadius); i++)
			for (int j = Math.max(0, obj.getXfloor() - explosionRadius); j < Math.min(MapData.getInstance().getMapWidth(), obj.getXfloor() + explosionRadius); j++)
				if (Physics.distanceofAToB(i, j, obj.getYfloor(), obj.getXfloor()) <= explosionRadius)
					terrain[i][j] = Physics.fluids.get(Physics.DEFAULT_FLUID);
	}

}

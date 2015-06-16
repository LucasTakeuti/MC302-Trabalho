package game.model;

public class DamageHandler {
	
	Physicable explodable;
	int explosionRadius;
	
	public DamageHandler(Physicable obj, int explosionRadius) {
		this.explodable = obj;
		this.explosionRadius = explosionRadius;
	}

	public void doDamage() {
		for (int i = Math.max(0, explodable.getYfloor() - explosionRadius); i < Math.min(MapData.getInstance().getMapHeight(), explodable.getYfloor() + explosionRadius); i++)
			for (int j = Math.max(0, explodable.getXfloor() - explosionRadius); j < Math.min(MapData.getInstance().getMapWidth(), explodable.getXfloor() + explosionRadius); j++)
				if (Physics.distanceofAToB(i, j, explodable.getYfloor(), explodable.getXfloor()) <= explosionRadius)
					if (MapData.getInstance().isShooter(i, j)) {
						Shooter s = MapData.getInstance().getShooter(MapData.getInstance().getShooterID(i, j));
						s.setLife(Math.max(0, s.getLife() - damageAmount(Physics.distanceofAToB(j, i, explodable.getXfloor(), explodable.getYfloor()))));
						if (s.getLife() == 0)
							s.setAlive(false);
					}
	}
	
	private double damageAmount(double distance) {
		return Shooter.MAX_LIFE * (1 - (distance/explosionRadius));
	}

}

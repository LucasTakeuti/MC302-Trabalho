package game.model;

public class ProjectileFactory {
	
	Shooter origin;
	
	public ProjectileFactory(Shooter origin){
		this.origin = origin;
	}
	
	public Projectile createProjectile(String projectileType, double vx, double vy){
      if(projectileType.equalsIgnoreCase("Basic Projectile"))
         return new BasicProjectile(origin.getXfloor(), origin.getYfloor(), vx, vy);
      return null;
	}
	
}

package game.model;

public class BasicProjectile extends Projectile {
	
	//(coluna, linha)
	public BasicProjectile(int x, int y, int vx, int vy) {
		
		super(x,y);
		
		setVelX(vx);
		setVelY(vy);
		
		setAccelX(Physics.gravityX);
		setAccelY(Physics.gravityY);
		
		setMass(1);
		setExplosionRadius(5);
	}
	
	public BasicProjectile(int x, int y) {
		
		super(x,y);
		
		setVelX(6);
		setVelY(-12);
		
		setAccelX(Physics.gravityX);
		setAccelY(Physics.gravityY);
		
		setMass(1);
		setExplosionRadius(5);
	}
	
}

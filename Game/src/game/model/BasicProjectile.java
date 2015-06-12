package game.model;

import game.GameMain;

public class BasicProjectile extends Projectile implements Physicable {
	
	//(coluna, linha)
	public BasicProjectile(int x, int y) {
		
		super(x,y);
		
		setVelX(5);
		setVelY(-11);
		
		setAccelX(Physics.gX);
		setAccelY(Physics.gY);
		
		setMass(1);
		setExplosionRadius(5);
	}
	
}

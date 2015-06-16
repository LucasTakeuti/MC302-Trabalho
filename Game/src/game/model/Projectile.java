package game.model;

import game.interfaces.IPhysicable;
import game.interfaces.IProjectile;

public abstract class Projectile extends Physicable implements IPhysicable, IProjectile {
	
	//Campos
	private int explosionRadius;
	
	//Constructor (colunas, linhas)
	public Projectile(int x, int y) {
		super (x, y);
	}
	
	//Getters and Setters
	public int getExplosionRadius() {
		return explosionRadius;
	}
	public void setExplosionRadius(int explosionRadius) {
		this.explosionRadius = explosionRadius;
	}
		
}

package game.model;


public abstract class Projectile extends Physicable {
	
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

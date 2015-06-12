package game.model;

import game.GameMain;

public class BasicProjectile extends Projectile implements Physicable {
	
	//Campos
	private double x;
	private double y;
	
	private double VelX;
	private double VelY;
	
	private double AccelX;
	private double AccelY;
	
	private double mass;
	private int explosionRadius;
	
	//(coluna, linha)
	public BasicProjectile(int x, int y) {
		
		setX(x);
		setY(y);
		
		setVelX(5);
		setVelY(-11);
		
		setAccelX(Physics.gX);
		setAccelY(Physics.gY);
		
		setMass(1);
		setExplosionRadius(5);
		
		MapData.getInstance().addToObservedList(this);
	}
	
	//Methods
	@Override
	public void update() {
		//altera a posicao com base na velocidade e no tempo transcorrido
		setX(getX() + getVelX() * GameMain.FrameDurationInSecs); 
		setY(getY() + getVelY() * GameMain.FrameDurationInSecs); 
		
		//altera a velocidade com base na aceleracao e no tempo transcorrido
		setVelX(getVelX() + getAccelX() * GameMain.FrameDurationInSecs);
		setVelY(getVelY() + getAccelY() * GameMain.FrameDurationInSecs);
		
		if(checkCollision(MapData.getInstance().getTerrain())) {
			MapData.getInstance().deleteFromObservedList(this);
			destroyTerrain(MapData.getInstance().getTerrain(), getExplosionRadius());
		}
	}
	

	private void destroyTerrain(char[][] terrain, int explosionRadius) {
		for (int i = Math.max(0, getYfloor() - explosionRadius); i < Math.min(MapData.getInstance().getMapHeight(), getYfloor() + explosionRadius); i++)
			for (int j = Math.max(0, getXfloor() - explosionRadius); j < Math.min(MapData.getInstance().getMapWidth(), getXfloor() + explosionRadius); j++)
				if (distanceTo(i, j, getYfloor(), getXfloor()) <= explosionRadius)
					terrain[i][j] = 'a';
	}
	
	private double distanceTo(int x1, int y1, int x2, int y2) {
		return (Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2)));
	}

	@Override
	public void putIntoMap(char[][] map) {
		
		//if (y > 0 && y < 15 && x > 0 && x < 60)
			map[getYfloor()][getXfloor()] = 'p';
		
	}
	
	public boolean checkCollision(char[][] map) {
		
		if (map[getYfloor()][getXfloor()] == 't')
			return true;
		else
			return false;
		
	}
	
	//Getters and Setters
	public double getX() {
		return x;
	}
	public int getXfloor() {
		return (int) Math.floor(x);
	}
	public void setX(double x) {
		this.x = x;
	}
	
	public double getY() {
		return y;
	}
	public int getYfloor() {
		return (int) Math.floor(y);
	}
	public void setY(double y) {
		this.y = y;
	}
	
	public double getVelX() {
		return VelX;
	}
	public void setVelX(double xVel) {
		this.VelX = xVel;
	}
	
	public double getVelY() {
		return VelY;
	}
	public void setVelY(double yVel) {
		this.VelY = yVel;
	}
	
	public double getMass() {
		return mass;
	}
	public void setMass(double mass) {
		this.mass = mass;
	}
	
	public int getExplosionRadius() {
		return explosionRadius;
	}
	public void setExplosionRadius(int explosionRadius) {
		this.explosionRadius = explosionRadius;
	}

	public double getAccelX() {
		return AccelX;
	}

	public void setAccelX(double accelX) {
		AccelX = accelX;
	}

	public double getAccelY() {
		return AccelY;
	}

	public void setAccelY(double accelY) {
		AccelY = accelY;
	}

}

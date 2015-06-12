package game.model;

import game.GameMain;

public abstract class Projectile implements Physicable {
	
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
	public Projectile(int x, int y) {
		
		setX(x);
		setY(y);
		
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

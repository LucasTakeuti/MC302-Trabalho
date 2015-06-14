package game.model;

import game.GameMain;

public abstract class Physicable {
	
	//Campos
	private double x;
	private double y;
	
	private double VelX;
	private double VelY;
	
	private double AccelX;
	private double AccelY;
	
	private double mass;
	
	private boolean visible;
	private boolean moving;
	
	//Constructor (colunas, linhas)
	public Physicable(int x, int y) {
		
		setVisible(true);
		setMoving(true);
		
		setX(x);
		setY(y);
		
		setVelX(0);
		setVelY(0);

		setAccelX(Physics.gravityX);
		setAccelY(Physics.gravityY);
		
		MapData.getInstance().addToObservedList(this);
	}
	
	//Methods
	public void update() {
		setX(Physics.step(getX(), getVelX(), GameMain.FrameDurationInSecs));
		setY(Physics.step(getY(), getVelY(), GameMain.FrameDurationInSecs));

		setVelX(Physics.step(getVelX(), getAccelX(), GameMain.FrameDurationInSecs));
		setVelY(Physics.step(getVelY(), getAccelY(), GameMain.FrameDurationInSecs));
		
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

	public double getAccelX() {
		return AccelX;
	}

	public void setAccelX(double accelX) {
		AccelX = accelX;
	}
	
	public void stopAccelX() {
		AccelX = 0;
	}
	
	public void resetAccelX() {
		AccelX = Physics.gravityX;
	}

	public double getAccelY() {
		return AccelY;
	}

	public void setAccelY(double accelY) {
		AccelY = accelY;
	}
	
	public void stopAccelY() {
		AccelY = 0;
	}
	
	public void resetAccelY() {
		AccelY = Physics.gravityY;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public boolean isMoving() {
		return moving;
	}
	public void setMoving(boolean moving) {
		if (moving)
			resetAccelY();
		else
			stopAccelY();
		this.moving = moving;
	}

}

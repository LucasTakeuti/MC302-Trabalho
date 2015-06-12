package game.model;

public interface Physicable {
	
	public void update();
	
	public double getX();
	public int getXfloor();
	public void setX(double x);
	
	public double getY();
	public int getYfloor();
	public void setY(double y);
	
	public double getVelX();
	public void setVelX(double xVel);
	
	public double getVelY();
	public void setVelY(double yVel);
	
	public double getMass();
	public void setMass(double mass);
	
	public double getAccelX();
	public void setAccelX(double accelX);
	
	public double getAccelY();
	public void setAccelY(double accelY);

}

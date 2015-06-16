package game.interfaces;

public interface IPhysicable {

	//Methods
	public abstract void update();

	//Getters and Setters
	public abstract double getX();

	public abstract int getXfloor();

	public abstract void setX(double x);

	public abstract double getY();

	public abstract int getYfloor();

	public abstract void setY(double y);

	public abstract double getVelX();

	public abstract void setVelX(double xVel);

	public abstract double getVelY();

	public abstract void setVelY(double yVel);

	public abstract double getMass();

	public abstract void setMass(double mass);

	public abstract double getAccelX();

	public abstract void setAccelX(double accelX);

	public abstract void stopAccelX();

	public abstract void resetAccelX();

	public abstract double getAccelY();

	public abstract void setAccelY(double accelY);

	public abstract void stopAccelY();

	public abstract void resetAccelY();

	public abstract boolean isVisible();

	public abstract void setVisible(boolean visible);

	public abstract boolean isFalling();

	public abstract void setFalling(boolean moving);

}
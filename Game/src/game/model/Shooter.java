package game.model;

import java.util.ArrayList;

public class Shooter extends Physicable {
	
	//Campos
	public static final double MAX_LIFE = 100;
	
	private final int ID;
	private double life;
	private boolean invulnerable;
	private boolean doubleJump;
	private boolean active;
	private ArrayList<Physicable> shoots;
	
	//Constructor
	public Shooter(int x, int y) {
		super(x, y);
		setShoots(new ArrayList<Physicable>());
		setLife(MAX_LIFE);
		ID = nextID();
		setActive(false);
		setDoubleJump(false);
		setInvulnerable(false);
	}
	
	public void basicShoot(double vx, double vy) {
		
		ProjectileFactory projectileFactory = new ProjectileFactory(this);
		
		if (isAlive() && !isFalling() && !hasFinishedTurn()) {
			getShoots().add(projectileFactory.createProjectile("Basic Projectile", vx, vy));
			endTurn();
		}
	}
	
	public void jump() {
		if (isAlive() && (!isFalling() || hasDoubleJump()) && !hasFinishedTurn()) {
			setDoubleJump(!hasDoubleJump());
			setFalling(true);
			setVelY(Physics.jumpSpeed);
		}
	}
	
	//Getters and Setters
	public double getLife() {
		return life;
	}

	public void setLife(double life) {
		if (!isInvulnerable())
			this.life = life;
	}

	public int getID() {
		return ID;
	}
	
	public boolean isInvulnerable() {
		return invulnerable;
	}
	
	public void setInvulnerable(boolean invulnerable) {
		this.invulnerable = invulnerable;
	}	
	
	private int nextID() {
		
		int id = 0;
		
		for (int i = 0; i < MapData.getInstance().getPhysicsList().size(); i++)
			if (MapData.getInstance().getPhysicsList().get(i) instanceof Shooter)
				id++;
		return id;
	}

	public boolean isAlive() {
		return (getLife() > 0);
	}

	public void setAlive(boolean b) {
		if (b)
			setLife(getLife());
		else {
			setLife(0);
			endTurn();
		}
	}

	private boolean hasDoubleJump() {
		return doubleJump;
	}

	private void setDoubleJump(boolean doubleJump) {
		this.doubleJump = doubleJump;
	}

	public boolean hasFinishedTurn() {
		return !active;
	}
	
	public void newTurn() {
		setActive(true);
	}
	
	public void endTurn() {
		setActive(false);
	}
	
	private void setActive(boolean active) {
		this.active = active;
	}

	public ArrayList<Physicable> getShoots() {
		return shoots;
	}

	public void setShoots(ArrayList<Physicable> shoots) {
		this.shoots = shoots;
	}
	
}

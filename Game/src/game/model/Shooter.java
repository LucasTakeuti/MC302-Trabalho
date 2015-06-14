package game.model;

public class Shooter extends Physicable {
	
	//Campos
	public static final double MAX_LIFE = 100;
	
	private final int ID;
	private double life;
	private boolean invulnerable;
	private boolean doubleJump;
	private boolean active;
	
	//Constructor
	public Shooter(int x, int y) {
		super(x, y);
		setLife(MAX_LIFE);
		ID = nextID();
		setActive(false);
		setDoubleJump(false);
		setInvulnerable(false);
	}
	
	public void shoot(double vx, double vy) {
		if (isAlive() && !isMoving() && !hasFinishedTurn()) {
			BasicProjectile bp = new BasicProjectile(getXfloor(), getYfloor()-1, vx, vy);
			endTurn();
		}
	}
	
	public void jump() {
		if (isAlive() && (!isMoving() || hasDoubleJump())) {
			setDoubleJump(!hasDoubleJump());
			setMoving(true);
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
		setLife(b ? getLife() : 0);
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
	
}

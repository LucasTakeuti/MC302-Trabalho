package game.model;

public class Shooter extends Physicable {
	
	//Campos
	public static final double MAX_LIFE = 100;
	
	private final int ID;
	private double life;
	private boolean invulnerable;
	
	//Constructor
	public Shooter(int x, int y) {
		super(x, y);
		setLife(MAX_LIFE);
		ID = nextID();
		setThrown(true);
		setInvulnerable(false);
	}
	
	public void shoot(double vx, double vy) {
		if (!isThrown()) {
			BasicProjectile bp = new BasicProjectile(getXfloor(), getYfloor()-1, vx, vy);
		}
	}
	
	public void jump() {
		if (!isThrown()) {
			setThrown(true);
			setVelY(Physics.jumpSpeed);
		}
	}
	
	//Getters and Setters
	public double getLife() {
		return life;
	}

	public void setLife(double life) {
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
	
}

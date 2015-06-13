package game.model;

public class Shooter extends Physicable {
	
	//Campos
	public static final double MAX_LIFE = 100;
	
	private final int ID;
	private double life;
	private boolean thrown;
	private boolean invulnerable;
	
	//Constructor
	public Shooter(int x, int y) {
		super(x, y);
		setLife(MAX_LIFE);
		ID = nextID();
		setThrown(false);
		setInvulnerable(false);
	}
	
	public void shoot(double vx, double vy) {
		BasicProjectile bp = new BasicProjectile(getXfloor(), getYfloor()-1, vx, vy);
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
	
	public boolean isThrown() {
		return thrown;
	}

	public void setThrown(boolean thrown) {
		this.thrown = thrown;
	}
	
	private int nextID() {
		
		int id = 0;
		
		for (int i = 0; i < MapData.getInstance().getPhysicsList().size(); i++)
			if (MapData.getInstance().getPhysicsList().get(i) instanceof Shooter)
				id++;
		return id;
	}

	public boolean isInvulnerable() {
		return invulnerable;
	}

	public void setInvulnerable(boolean invulnerable) {
		this.invulnerable = invulnerable;
	}
	
}

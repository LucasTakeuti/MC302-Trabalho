package game.model;

public class Shooter extends Physicable {
	
	//Campos
	private final int ID;
	private double life;
	private boolean thrown;
	
	//Constructor
	public Shooter(int x, int y) {
		super(x, y);
		setLife(100);
		ID = nextID();
		setThrown(false);
	}
	
	public void shoot(int vx, int vy) {
		BasicProjectile bp = new BasicProjectile(getXfloor(), getYfloor(), vx, vy);
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
	
}

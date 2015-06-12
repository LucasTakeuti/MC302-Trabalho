package game.model;

public interface Physicable {
	
	public void update();

	public void putIntoMap(char[][] map);

	public boolean checkCollision(char[][] map);

}

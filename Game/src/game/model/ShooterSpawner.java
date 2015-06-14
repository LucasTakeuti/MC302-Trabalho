package game.model;

import java.util.Random;

public class ShooterSpawner {
	
	private Random r = new Random();
	
	public ShooterSpawner(int numberOfShooters) {
		for (int i = 0; i < numberOfShooters; i++)
			spawnShooter();
	}
	
	private void spawnShooter() {
		
		//seleciona uma coluna aleatoria para spawnar o shooter
		int spawnRow;
		int spawnColumn = r.nextInt(MapData.getInstance().getMapWidth()-1);
		
		while ((spawnRow = validateColumn(spawnColumn)) <= 0)
			spawnColumn = r.nextInt(MapData.getInstance().getMapWidth()-1);
		
		Shooter s = new Shooter(spawnColumn, spawnRow);
	}
	
	private int validateColumn(int spawnColumn) {
		
		int spawnRow = MapData.getInstance().getMapHeight()-2;
		
		//linha de spawn dentro do mapa e sobre algum terreno
		while(Physics.solids.contains(MapData.getInstance().getMap()[spawnRow][spawnColumn]) ||
			  Physics.fluids.contains(MapData.getInstance().getMap()[spawnRow + 1][spawnColumn]) ||
			  MapData.getInstance().isShooter(spawnRow, spawnColumn)) {
			spawnRow--;
			if (spawnRow < 0) break;
		}
		
		return spawnRow;
		
	}
	
}

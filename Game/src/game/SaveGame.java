package game;

import game.model.MapData;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveGame {
	
	ObjectInputStream decoder;
	MapData gameData = null;
	MapData recoveredGameData = null;
	
	public SaveGame() {
		gameData = MapData.getInstance();
	}
	
	/* Serializa */
	public void SerializeGame() {
		try {
			ObjectOutputStream encoder = new ObjectOutputStream(new FileOutputStream("save_game.bin") );
			encoder.writeObject(gameData);
			encoder.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/* Deserializa */
	public MapData DesserializeGame() {
		try {
			decoder = new ObjectInputStream(new FileInputStream("save_game.bin"));
			recoveredGameData = (MapData) decoder.readObject();
			decoder.close();
		}
		catch (Exception e) {
			e.printStackTrace();			
		}
		return recoveredGameData;
	}
}

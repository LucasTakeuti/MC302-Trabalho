package game;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ReadFile {
	
	private Scanner s;
	private File f;
	private String path;
	
	public ReadFile(String path) {
		this.path = path;
	}
	
	public char[][] readFile() {
		
		int[] dim = fileLength();
		
		char[][] mapa = new char[dim[0]][dim[1]];
		
		openFile();
		
		String a = null;
		int i = 0;
		while (s.hasNext()) {
			a = s.next();
			mapa[i] = a.toCharArray();
			i++;
		}
		
		closeFile();
		
		return mapa;
	}
	
	public int[] fileLength() {
		
		openFile();
		
		int[] dim = new int[2];
		
		String a = null;
		while (s.hasNext()) {
			dim[0]++;
			a = s.next();
		}
		dim[1] = a.length();
		
		closeFile();
		
		return dim;
	}
	
	private void openFile() {
		try {
			f = new File("src/resources/" + path);
			s = new Scanner(f);
		}
		catch (IOException e) {
			System.out.println("Could not find file");
		}
	}
	
	private void closeFile() {
		if (s != null) {
			s.close();
		}
	}

}

package game.model;

import java.util.Arrays;
import java.util.List;

public class Physics {

	public static final int gravityX = 0;
	public static final int gravityY = 10;
	
	public static final List<Character> solids = Arrays.asList('g', '1', '2');
	
	public static final List<Character> fluids = Arrays.asList('~');
	
	public static final List<Character> id = Arrays.asList('1', '2');
	
	public static final int DEFAULT_FLUID = 0;
	
	public static final int jumpSpeed = -8;
	
	public static double step (double initState, double initVector, double deltaTime) {
		return initState + initVector * deltaTime;
	}
	
	public static double getXComponent (double vector, double angle) {
		return vector * Math.cos(Math.toRadians(angle));
	}
	
	public static double getYComponent (double vector, double angle) {
		return vector * Math.sin(Math.toRadians(angle));
	}
	
	public static double distanceofAToB(int xa, int ya, int xb, int yb) {
		return (Math.sqrt((xa-xb)*(xa-xb) + (ya-yb)*(ya-yb))); 
	}
	
}

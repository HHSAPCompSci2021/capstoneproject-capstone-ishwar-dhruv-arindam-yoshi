package characters;
import processing.core.*;

public class Antagonist extends Grinch {
	
	private int health;
	private boolean isAlive;
	private double proximity;
	private static final String IMG_PATH = "grinch.png";
	
	/**
	 * Creates an object of the antagonist class
	 * @param marker the PApplet in which the antagonist is on 
	 * @param x the x coordinate of the antagonist
	 * @param y the y coordinate of the antagonist 
	 */
	public Antagonist(PApplet marker, double x, double y) {
		super(marker, x, y);
	}
	
}

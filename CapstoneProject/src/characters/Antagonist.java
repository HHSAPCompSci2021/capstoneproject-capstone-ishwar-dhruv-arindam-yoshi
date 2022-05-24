package characters;
import processing.core.*;

public class Antagonist extends Grinch {
	
	private int health;
	private boolean isAlive;
	private double proximity;
	private static final String IMG_PATH = "grinch.png";
	
	public Antagonist(PApplet marker, double x, double y) {
		super(marker, x, y);
	}
	
}

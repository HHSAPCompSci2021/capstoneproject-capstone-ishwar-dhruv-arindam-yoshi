package characters;
import processing.core.*;

public class Antagonist extends Grinch {
	
	private int health;
	private boolean isAlive;
	private double proximity;
	
	public Antagonist(int x, double z, int xCoord, int yCoord)
	{
		super(xCoord, yCoord);
		health = x;
		isAlive = health > 0;
		proximity = z;
	} 
	
	
	
	
}

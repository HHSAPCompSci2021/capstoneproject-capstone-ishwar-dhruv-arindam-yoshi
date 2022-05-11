package characters;
import processing.core.*;
import screen.ScreenObject;

public class Actor extends ScreenObject {
	
	// has an image, velocity vector, and image
	
	private PImage image;
	protected double vx, vy; 
	
	public Actor(PImage img, int x, int y, int w, int h) {
		super(x, y, w, h);
		image = img;
		vx = 0; vy = 0;
	}
	
	public void move()
	{
		
	}
	
	public void act()
	{
		
	}

}

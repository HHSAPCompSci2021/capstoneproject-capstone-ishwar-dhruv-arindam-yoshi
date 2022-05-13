package characters;
import processing.core.*;
import game.*;
import screen.ScreenObject;

public class Actor extends ScreenObject {
	
	// has an image, velocity vector, and image
	
	protected PImage image;
	protected double vx, vy; 
	
	public Actor(PImage img, double x, double y, double w, double h) {
		super(x, y, w, h);
		vx = 0; vy = 0;
		setImage(img);
	}
	
	public void setImage(PImage img)
	{
		image = img;
	}
	
	/**
	 * Gets the horizontal velocity of the Actor
	 * @return the horizontal velocity of the Actor
	 */
	public double getVx() {
		return vx;
	}
	
	/**
	 * Gets the vertical velocity of the Actor
	 * @return the vertical velocity of the Actor
	 */
	public double getVy() {
		return vy;
	}
	
	/**
	 * Sets the horizontal velocity of the Actor
	 * @param v the horizontal velocity of the Actor
	 */
	public void setVx(double v) {
		vx = v;
	}
	
	/**
	 * Sets the vertical velocity of the Actor
	 * @param v the vertical velocity of the Actor
	 */
	public void setVy(double v) {
		vy = v;
	}
	
	public void move()
	{
		
	}
	
	public void act(HauntedMaze maze)
	{
		
	}

}

package characters;
import processing.core.*;
import core.DrawingSurface;
import game.*;
import screen.ScreenObject;

public class Actor extends ScreenObject {
	
	// has an image, velocity vector, and image
	
	protected PImage image;
	protected double vx, vy; 
	
	public Actor(String imgPath, PApplet marker, double x, double y, double w, double h) {
		super(x, y, w, h);
		vx = 0; vy = 0;
		this.image = marker.loadImage(imgPath);
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
		wallImpact(maze);
		
		x += vx*DrawingSurface.DT;
		y += vy*DrawingSurface.DT;
	}
	
	protected void wallImpact(HauntedMaze maze)
	{
		boolean[] collisions = maze.settingData.isTouchingWall(maze, this);
		
		for (int i = 0; i < 4; i++)
			System.out.print(collisions[i] + " ");
		System.out.println();
		
		/*
		if (collisions[0])
			vx = (vx < 0) ? 0 : vx;
		if (collisions[1])
			vx = (vx > 0) ? 0 : vx;
		if (collisions[2])
			vy = (vy < 0) ? 0 : vy;
		if (collisions[3])
			vy = (vy > 0) ? 0 : vy;
		*/
	}

}

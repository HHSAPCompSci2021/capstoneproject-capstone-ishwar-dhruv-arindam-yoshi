package characters;
import processing.core.*;

import java.util.ArrayList;

import core.DrawingSurface;
import game.*;
import screen.ScreenObject;

/**
 * Represents an actor: a person on the maze
 *
 */
public class Actor extends ScreenObject {
	
	// has an image, velocity vector, and image
	
	/**
	 * the image that the Actor has, which is stored as a PImage object
	 */
	protected PImage image;
	/**
	 * the x-velocity of the Actor
	 */
	protected double vx;
	/**
	 * the y-velocity of the Actor
	 */
	protected double vy; 
	
	/**
	 * Constructs an actor
	 * @param imgPath the image of the actor which is shown on the PApplet
	 * @param marker the processing window which the actor is created upon. 
	 * @param x the x coordinate of the actor
	 * @param y the y coordinate of the actor
	 * @param w the width of the actor
	 * @param h the height of the actor
	 */
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
	
	/**
	 * Useless method
	 */
	public void move()
	{
		
	}
	/**
	 * Performs an act on the maze which changes the position of the actor
	 * @param maze the HauntedMaze which the actor is located upon. 
	 */
	public void act(HauntedMaze maze)
	{
		
		
//		wallImpact(maze);
		
		x += vx*DrawingSurface.DT;
		y += vy*DrawingSurface.DT;
	}
	
	/**
	 * Returns the bounding rectangle of the Actor.
	 * @return the bounding rectangle of the Actor
	 */
	public Rectangle getBoundingRectangle() {
		return new Rectangle(super.x, super.y, super.w, super.h); 
	}
	
	
	/**
	 * left = 0
	 * right = 1
	 * top = 2
	 * bottom = 3 
	 */

	/**
	 * Makes sure that the actor does not pass through walls.
	 * @param maze the maze in which the actor is contained
	 */
	protected void wallImpact(HauntedMaze maze)
	{
		int pushBackVel = 4; 
		ArrayList<Rectangle> walls = maze.settingData.wallsList; 
		boolean[] collisions = MazeData.isActorTouchingMaze(walls, this);
		
		int counter = 0; 
		for (int i=0;i<collisions.length;i++) {
			if (collisions[i]) counter++; 
		}
		if (counter > 1) {
			pushBackVel = 10; 
		}
			
		
		if (collisions[0]) {
			x-=pushBackVel;

		}
		if (collisions[1]) {
			x+=pushBackVel; 
		}
		
		if (collisions[2]) {
			y-=pushBackVel; 
		}
		
		if (collisions[3]) {
			y+=pushBackVel; 

		}
		
	}

}

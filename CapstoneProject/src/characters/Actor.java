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
		ArrayList<Rectangle> walls = maze.settingData.wallsList; 
		boolean[] collisions = MazeData.isActorTouchingMaze(walls, this); 
		
		/*
		if (this instanceof Officer)
			System.out.println(collisions[0] + " " + collisions[1] + " " + collisions[2] + " " + collisions[3]);
		*/ 
		
		if (collisions[0]) {
			vx = Math.max(0, vx);
			// System.out.println("LAST COLLISION WENT HERE: 0"); 
		}
		if (collisions[1]) {
			vx = Math.min(0, vx);
			// System.out.println("LAST COLLISION WENT HERE: 1"); 
		}
		if (collisions[2]) {
			vy = Math.max(0, vy); 
			// System.out.println("LAST COLLISION WENT HERE: 2"); 
		}
		if (collisions[3]) {
			vy = Math.min(0, vy); 
			// System.out.println("LAST COLLISION WENT HERE: 3"); 
		}
	}

	/**
	 * Determines if the actor passed is touching the wall, and if so, then in what direction.
	 * @param r the Rectangle object with which to check for intersection
	 * @return a boolean array of size 4, that represents whether an actor is touching a wall
	 * Position 0 of the array represents whether the actor is touching the wall from left. 
	 * Position 1 of the array represents whether the actor is touching the wall from the right.
	 * Position 2 of the array represents whether the actor is touching the wall from the top
	 * Position 3 of the array represents whether the actor is touching the wall from the bottom. 
	 */
	public boolean[] isTouchingWall(Rectangle r) {
		boolean[] info = new boolean[4]; 
		info[0] = info[1] = info[2] = info[3] = false; 
		
		Rectangle other = getBoundingRectangle();
		Rectangle intersect = Rectangle.intersects(r, other);
		
		if (intersect == null)
			return info;
		
		double midInterX = intersect.x1 + intersect.w/2;
		double midInterY = intersect.y1 + intersect.h/2;
		
		double midX = x + w/2;
		double midY = y + h/2;
		
		double scaledInterX = (midInterX - midX)/(w/2);
		double scaledInterY = (midInterY - midY)/(h/2);
		
		double[] dists = {1 + scaledInterX, 1 - scaledInterX,
							1 + scaledInterY, 1 - scaledInterY};
		
		/*
		if (this instanceof Officer)
		{
			for (int i = 0; i < 4; i++)
				System.out.print(dists[i] + " ");
			System.out.println();
		}
		*/
		
		int minIndex = 0;
		double min = Double.MAX_VALUE;
		for (int i = 0; i < 4; i++)
			if (dists[i] < min)
			{
				min = dists[i];
				minIndex = i;
			}
		
		info[minIndex] = true;
		return info;
	}

}

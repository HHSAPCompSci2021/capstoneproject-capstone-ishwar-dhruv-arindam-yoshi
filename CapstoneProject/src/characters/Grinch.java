package characters;
import processing.core.*;

import java.util.ArrayList;

import core.DrawingSurface;
import game.HauntedMaze;
import items.*;

/**
 * Represents a Grinch. 
 * @author dhruv
 *
 */
public class Grinch extends Actor {
	
	// has arraylist of traps to set in the maze (want-to-have)
	/**
	 * Represents the speed of the grinch
	 */
	public static final double SPEED = 25;
	static String imageForGrinch = "assets/grinch.png";  
	
	/**
	 * Creates an object of the grinch
	 * @param marker the grinch is on the marker
	 * @param x the x coordinate of the grinch
	 * @param y the y coordinate of the grinch
	 * @param noImage true if there is no image for this grinch
	 */
	public Grinch(PApplet marker, double x, double y, boolean noImage) {
		super(null, marker, x, y, 21, 31);
	}
	
	/**
	 * Constructs a grinch
	 * @param marker the grinch is on the marker
	 * @param x the x coordinate of the grinch
	 * @param y the y coordinate of the grinch
	 */
	public Grinch(PApplet marker, double x, double y)
	{
		super(imageForGrinch, marker, x, y, 21, 31);
	}
	
	@Override
	public void draw(PApplet marker)
	{
		marker.push();
		
		marker.fill(0);
		// marker.rect((float)x,  (float)y, (float)w, (float)h);
		marker.image(image, (float)x, (float)y, (float)w, (float)h);
		
		marker.pop();
	}
	
	/**
	 * This performs a grinch act which involves moving closer to the protagonist of the maze
	 * @param maze which represents the maze which the grid is on. 
	 */
	public void act(HauntedMaze maze)
	{
		double dist = Math.sqrt(Math.pow(( maze.protagonist.getX() - x), 2) + Math.pow((maze.protagonist.getY() - y), 2));
		
		vx = SPEED * (( maze.protagonist.getX() - x) / dist);
		vy = SPEED * ((maze.protagonist.getY() - y) / dist);
		super.act(maze);	
	}
	

//	public void isOfficerNearTrap(HauntedMaze h) {
//		for (Trap t : h.)
//		{
//			Officer o = maze.protagonist; 
//			double dist = Math.sqrt(Math.pow(o.getX()-x, 2) + Math.pow(o.getY()-y, 2));
//			if (dist < NEAR_DIST)
//				t.use(h);
//		}
//	}
	
}

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
	
	/**
	 * Constructs a grinch
	 * @param marker the grinch is on the marker
	 * @param x the x coordinate of the grinch
	 * @param y the y coordinate of the grinch
	 */
	public Grinch(PApplet marker, double x, double y)
	{
		super("assets/grinch.png", marker, x, y, 21, 31);
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
	 * Sets the trap of the grinch around the maze
	 * @param e the trap to set around the maze
	 */
	public void setTrap(Trap e)
	{
		
	}
	
	/**
	 * This performs a grinch act which involves moving closer to the protagonist of the maze
	 * @param maze which represents the maze which the grid is on. 
	 */
	public void act(HauntedMaze maze)
	{
		
		double dx = maze.protagonist.getX() - x;
		double dy = maze.protagonist.getY() - y;
		double dist = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
		
		vx = SPEED * (dx / dist);
		vy = SPEED * (dy / dist);
		
		// System.out.print("Grinch: ");
		super.act(maze);
	}
	

//	public void isOfficerNearTrap(HauntedMaze h) {
//		for (Trap t : grinchTraps)
//		{
//			Officer o = maze.protagonist; 
//			double dist = Math.sqrt(Math.pow(o.getX()-x, 2) + Math.pow(o.getY()-y, 2));
//			if (dist < NEAR_DIST)
//				t.use(h);
//		}
//	}
	
}

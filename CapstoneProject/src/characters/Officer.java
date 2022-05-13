package characters;

import java.util.*;
import game.*;

import core.DrawingSurface;
import processing.core.*;
import items.*;

public class Officer extends Actor {
	
	// TO-DO
	// collision with maze
	
	// has blueprints and health level (# of lives)
	
	public static final String IMG_PATH = "assets/badge.png";
	private static final double LETHAL_RAD = 50;
	public static final double PICK_DIST = 20;
	
	// image taken from
	// https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.istockphoto.com%2Fphotos%2Fpolice-badge-graphic&psig=AOvVaw3xpVBxMDAwHVxv8yK_jvxl&ust=1652402341678000&source=images&cd=vfe&ved=0CA0QjhxqFwoTCLi90tHc2PcCFQAAAAAdAAAAABAQ
	
	private int health;
	public ArrayList<Blueprint> blueprints;
	public GeigerCounter gtool;
	
	public static double AXIS_V = 90; 
	
	/**
	 * Creates a new Officer object
	 * @param x the x-coordinate of the officer
	 * @param y the y-coordinate of the officer
	 */
	public Officer(PImage img, int x, int y)
	{
		super(img, x, y, 30, 40);
		blueprints = new ArrayList<Blueprint>();
		gtool = new GeigerCounter(x, y);
	}
	
	/**
	 * Takes a blueprint on the officer's behalf
	 * @param e the blueprint
	 */
	public void takeBlueprint(Blueprint e)
	{
		blueprints.add(e);
		
	}
	
	/**
	 * Returns a blueprint that is within picking range of the officer
	 * @return a blueprint that can be picked up by the officer
	 */
	public Blueprint nearBlueprint(HauntedMaze maze)
	{
		for (Item e : maze.items)
		{
			if (e instanceof Blueprint)
			{
				double dist = Math.sqrt(Math.pow(x-e.getX(), 2) + Math.pow(y-e.getY(), 2));
				if (dist < PICK_DIST)
					return (Blueprint)e;
			}
		}
		return null;
	}
	
	/**
	 * Returns whether the Officer has all the blueprints in the maze
	 * @return true if the Officer has all the blueprints in the maze; false otherwise
	 */
	public boolean hasAllBlueprints()
	{
		return true;
	}
	
	/**
	 * Returns the Officer's health level
	 * @return the Officer's health level
	 */
	public int getHealth()
	{
		if (health > 0)
			return health;
		else return 0;
	}
	
	/**
	 * Changes the health of the officer by the amount given
	 * @param dh the amount by which to change the officer's health
	 * @return
	 */
	public void changeHealth(int dh)
	{
		health += dh;
	}
	
	public boolean isAlive() {
		return health > 0;
	}
	
	/**
	 * Returns whether the Officer has exited the maze with all the blueprints
	 * @return true if the Officer has exited the maze with all the blueprints; false otherwise
	 */
	public boolean isSuccessful()
	{
		return hasAllBlueprints() && (health > 0) && false;
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
	
	public void act(HauntedMaze maze)
	{
		gtool.use(maze);
		
		changeHealth((int) Math.min(-0.1*gtool.getReading() + 2, 0));
		
		if (gtool.getReading() > LETHAL_RAD)
			health = 0;
		
		x += vx*DrawingSurface.DT;
		y += vy*DrawingSurface.DT;
	}
}

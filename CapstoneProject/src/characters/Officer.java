package characters;

import java.util.*;

import core.DrawingSurface;
import processing.core.*;
import items.*;

public class Officer extends Actor {
	
	// TO-DO
	// collision with maze
	
	// has blueprints and health level (# of lives)
	
	public static final String IMG_PATH = "assets/badge.png";
	// image taken from
	// https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.istockphoto.com%2Fphotos%2Fpolice-badge-graphic&psig=AOvVaw3xpVBxMDAwHVxv8yK_jvxl&ust=1652402341678000&source=images&cd=vfe&ved=0CA0QjhxqFwoTCLi90tHc2PcCFQAAAAAdAAAAABAQ
	
	private int health;
	private ArrayList<Blueprint> blueprints;
	
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
	
	public void act()
	{
		x += vx*DrawingSurface.DT;
		y += vy*DrawingSurface.DT;
	}
}

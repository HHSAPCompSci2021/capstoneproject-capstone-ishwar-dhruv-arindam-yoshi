package characters;

import java.util.*;
import processing.core.*;
import items.*;

public class Officer extends Actor {
	
	// has blueprints and health level (# of lives)
	
	private static String imgPath = "";
	
	private int health;
	private ArrayList<Blueprint> blueprints;
	
	/**
	 * Creates a new Officer object
	 * @param x the x-coordinate of the officer
	 * @param y the y-coordinate of the officer
	 */
	public Officer(int x, int y)
	{
		super(null, x, y, 20, 20);
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
}

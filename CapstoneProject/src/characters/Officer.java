package characters;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;
import game.*;

import core.DrawingSurface;
import processing.core.*;
import items.*;

public class Officer extends Actor {
	
	// TO-DO
	// collision with maze
	
	// has blueprints and health level (# of lives)
	
	private static final double LETHAL_RAD = 50;
	private static final double PICK_DIST = 20;
	
	private boolean accelerator;
	private static final double ACCELERATOR_DAMAGE = 60;
	
	/**
	 * the angle of the officer with respect to the horizontal (in radians) 
	 */
	public double direction;
	private double health;
	
	/**
	 * the list of Blueprints that the Officer has
	 */
	public ArrayList<Blueprint> blueprints;
	/**
	 * the Geiger counter that the Officer has
	 */
	public GeigerCounter gtool;
	
	/**
	 * the collection of teleporters that the Officer has
	 */
	public ArrayList<Teleporter> teleporters;
		
	public double axisV; 
	
	public PathFinder p; 
	
	/**
	 * Creates a new Officer object
	 * @param x the x-coordinate of the officer
	 * @param y the y-coordinate of the officer
	 */
	public Officer(PApplet marker, double x, double y)
	{
		super("assets/badge.png", marker, x, y, 21, 31);
		// image taken from
		// https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.istockphoto.com%2Fphotos%2Fpolice-badge-graphic&psig=AOvVaw3xpVBxMDAwHVxv8yK_jvxl&ust=1652402341678000&source=images&cd=vfe&ved=0CA0QjhxqFwoTCLi90tHc2PcCFQAAAAAdAAAAABAQ
		blueprints = new ArrayList<Blueprint>();
		teleporters = new ArrayList<Teleporter>();
		
		gtool = new GeigerCounter(x, y);
		
		health = 100;
		
		accelerator = false;
		axisV = 100;
		direction = 0;
		
		// add teleporters
		teleporters.add(new Teleporter(marker, x, y));
		p = new PathFinder(0,0,0,0); 
	}
	
	/**
	 * Gives the officer a faster speed.
	 */
	public void accelerate() {
		accelerator = true;
		axisV = 150;
	}
	
	/**
	 * Stops giving the officer a faster speed.
	 */
	public void stopAccelerate() {
		accelerator = false;
		axisV = 100;
	}
	
	/**
	 * Gives the officer a new teleporter.
	 */
	
	public void addTeleporter(Teleporter t) {
		teleporters.add(t);
	}
	
	/**
	 * Takes a blueprint on the officer's behalf
	 * @param e the blueprint
	 */
	public void takeBlueprint(HauntedMaze maze)
	{
		Blueprint e = nearBlueprint(maze);
		if (e != null)
			blueprints.add(e);
		maze.items.remove(e);
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
				double dist = Math.sqrt(Math.pow((x + w/2)-e.getX(), 2) + Math.pow((y + h/2)-e.getY(), 2));
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
	public boolean hasAllBlueprints(HauntedMaze maze)
	{
		for (Item i : maze.items)
		{
			if (i instanceof Blueprint)
				return false;
		}
		return true;
	}
	
	/**
	 * Returns the Officer's health level
	 * @return the Officer's health level
	 */
	public double getHealth()
	{	
		DecimalFormat df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.CEILING);
		return Double.parseDouble(df.format((health > 0) ? health : 0)); 
	}
	
	/**
	 * Changes the health of the officer by the amount given
	 * @param dh the amount by which to change the officer's health
	 * @return
	 */
	public void changeHealth(double dh)
	{
		health += dh;
	}
	
	public boolean isAlive() {
		return health > 0;
	}
	
	/**
	 * Returns whether the Officer is outside the maze
	 * @return
	 */
	public boolean isOutsideMaze(HauntedMaze maze)
	{
		return 		(x < maze.getX()-w)
				|| 	(x > maze.getX()+maze.getW())
				|| 	(y < maze.getY()-h)
				||	(y > maze.getY()+maze.getH());
	}
	
	/**
	 * Returns whether the Officer has exited the maze with all the blueprints
	 * @return true if the Officer has exited the maze with all the blueprints; false otherwise
	 */
	public boolean isSuccessful(HauntedMaze maze)
	{
		// System.out.println(hasAllBlueprints(maze));
		return hasAllBlueprints(maze) && isAlive() && isOutsideMaze(maze);
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
	 * Adjusts the 2D velocity of the officer.
	 * @param codeX if codeX &lt; 0, the x-velocity of the officer will become negative;<br> if codeX &gt; 0, the x-velocity of the officer will become positive;<br> else, the x-velocity will be set to 0.
	 * @param codeY if codeY &lt; 0, the y-velocity of the officer will become negative;<br> if codeY &gt; 0, the y-velocity of the officer will become positive;<br> else, the y-velocity will be set to 0.
	 */
	public void adjustV(int codeX, int codeY)
	{
		if (codeX < 0)
			vx = -axisV;
		else if (codeX > 0)
			vx = axisV;
		else
			vx = 0;
		
		if (codeY < 0)
			vy = -axisV;
		else if (codeY > 0)
			vy = axisV;
		else
			vy = 0;
		
		if ((codeX != 0) && (codeY != 0))
		{
			vx *= 1/Math.sqrt(2);
			vy *= 1/Math.sqrt(2);
		}
	}
	
	public boolean useTeleporter(HauntedMaze maze) {
		if (teleporters.size() == 0)
			return false;
		
		Teleporter tUse = teleporters.remove(0);
		tUse.use(maze);
		return true;
	}
	
	public void usePathFinder(HauntedMaze maze) {
		p.use(maze);
	}
	
	public void drawPathFinder(PApplet marker) {
		p.draw(marker);
	}
	
	
	@Override
	public void act(HauntedMaze maze)
	{
		if (accelerator)
			health -= ACCELERATOR_DAMAGE * DrawingSurface.DT;
			
		gtool.use(maze);
		
		changeHealth(Math.min(-0.01*gtool.getReading() + 0.1, 0));
		
		if (gtool.getReading() > LETHAL_RAD)
			health = 0;

		// System.out.print("Officer: ");
		if (!accelerator) 
			super.wallImpact(maze);
		
		super.act(maze);
	}
}

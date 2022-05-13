package items;
import screen.ScreenObject;
import processing.core.*;
import game.*;

public class GeigerCounter extends Item<Object> {
	
	private static double PROP_CONST = 50*Math.pow(15, 2);
	
	// TO-DO
		// should return distance between Grinch and Officer
	
	private double radiationReading; // radioactivity measured in Bq
	
	// has damage intensity

	public GeigerCounter(double x, double y)
	{
		super(x, y, 50, 30);
	}
	
	/**
	 * Returns the radiation reading.
	 * @return the radiation reading (in Bq).
	 */
	public double getReading()
	{
		return radiationReading;
	}
	
	@Override
	public Object use(HauntedMaze maze)
	{
		double px = maze.protagonist.getX(); double py = maze.protagonist.getY();
		double gx = maze.villain.getX(); double gy = maze.villain.getY();
		
		double distsq = Math.pow(gx - px, 2) + Math.pow(gy - py, 2);
		
		radiationReading = PROP_CONST/distsq;
		
		return null; 
	}
	
	public void drawInfo(PApplet marker, double x, double y)
	{
		
	}
}

package items;
import screen.ScreenObject;
import game.HauntedMaze;
import processing.core.*;
import game.*;

public class GeigerCounter extends Item {
	
	private static double PROP_CONST = 50*Math.pow(25, 1);
	
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
	

	public void use(HauntedMaze maze)
	{
		double px = maze.protagonist.getX(); double py = maze.protagonist.getY();
		double gx = maze.villain.getX(); double gy = maze.villain.getY();
		
		double dist = Math.sqrt(Math.pow(gx - px, 2) + Math.pow(gy - py, 2));
		
		radiationReading = PROP_CONST/dist;
	}
	

	@Override
	public void drawInfo(PApplet marker, int x, int y) {
		// TODO Auto-generated method stub
		
	}
}

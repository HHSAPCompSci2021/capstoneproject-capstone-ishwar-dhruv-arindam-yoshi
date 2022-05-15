package characters;
import processing.core.*;

import java.util.ArrayList;

import game.HauntedMaze;
import items.*;

public class Grinch extends Actor {
	
	// has arraylist of traps to set in the maze (want-to-have)
	private ArrayList<Trap> grinchTraps; 
	public static final double NEAR_DIST = 15;
	
	public Grinch(double x, double y)
	{
		super(null, x, y, 30, 40);
//		grinchTraps.add(new Trap(0,0,0,0,1));
//		grinchTraps.add(new Trap(0,0,0,0,1));
//		grinchTraps.add(new Trap(0,0,0,0,1));
		
	}
	
	public Grinch(double x, double y, ArrayList<Trap> grinchTraps)
	{
		super(null, x, y, 30, 40);
		this.grinchTraps = grinchTraps; 
	}
	
	public void setTrap(Trap e)
	{
		
	}
	
	public void isOfficerNearTrap(HauntedMaze h) {
		for (Trap t : grinchTraps)
		{
			double dist = Math.sqrt(Math.pow(x-t.getX(), 2) + Math.pow(y-t.getY(), 2));
			if (dist < NEAR_DIST)
				t.use(h);
		}
	}
}

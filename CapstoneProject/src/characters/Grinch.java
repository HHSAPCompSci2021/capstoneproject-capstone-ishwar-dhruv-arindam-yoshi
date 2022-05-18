package characters;
import processing.core.*;

import java.util.ArrayList;

import game.HauntedMaze;
import items.*;

public class Grinch extends Actor {
	
	// has arraylist of traps to set in the maze (want-to-have)
	
	
	public Grinch(PApplet marker, double x, double y)
	{
		this(marker, x, y, new ArrayList<Trap>());
//		grinchTraps.add(new Trap(0,0,1));
//		grinchTraps.add(new Trap(0,0,1));
//		grinchTraps.add(new Trap(0,0,1));
//		
	}
	
	public Grinch(PApplet marker, double x, double y, ArrayList<Trap> grinchTraps)
	{
		super("assets/grinch.png", marker, x, y, 30, 40);
		this.grinchTraps = grinchTraps; 
	}
	
	public void setTrap(Trap e)
	{
		
	}
	
	/*
	public void isOfficerNearTrap(HauntedMaze h) {
		for (Trap t : grinchTraps)
		{
			Officer o = maze.protagonist; 
			double dist = Math.sqrt(Math.pow(o.getX()-x, 2) + Math.pow(o.getY()-y, 2));
			if (dist < NEAR_DIST)
				t.use(h);
		}
	}
	*/
}

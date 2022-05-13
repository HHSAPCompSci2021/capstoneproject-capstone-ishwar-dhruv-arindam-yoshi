package items;
import screen.ScreenObject;
import game.HauntedMaze;
import processing.core.*;

public class Trap extends Item {
	
	// has damage intensity

	public Trap(double x, double y, double width, double height)
	{
		super(x, y, width, height);
	}
	
	public void use(HauntedMaze h)
	{
	}
	
	public void drawInfo(PApplet marker, double x, double y)
	{
		
	}
}

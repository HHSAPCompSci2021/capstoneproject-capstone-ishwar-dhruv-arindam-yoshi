package items;
import screen.ScreenObject;
import processing.core.*;
import game.HauntedMaze;
public class Blueprint extends Item<Object> {
	
	// has identifier

	public Blueprint(double x, double y, double width, double height)
	{
		super(x, y, width, height);
	}
	
	public Object use(HauntedMaze maze)
	{
		return null;
	}
	
	public void drawInfo(PApplet marker, int x, int y)
	{
		
	}
}

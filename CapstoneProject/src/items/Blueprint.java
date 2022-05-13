package items;
import screen.ScreenObject;
import processing.core.*;
import game.HauntedMaze;
public class Blueprint extends Item<Object> {
	
	// has identifier

	public Blueprint(int x, int y, int width, int height)
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

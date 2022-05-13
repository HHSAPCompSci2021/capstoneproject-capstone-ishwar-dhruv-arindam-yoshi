package items;
import screen.ScreenObject;
import processing.core.*;
import game.HauntedMaze;
public class Blueprint extends Item<Object> {
	
	// has identifier

	public Blueprint(int x, int y, int width, int height, HauntedMaze maze)
	{
		super(x, y, width, height, maze);
	}
	
	public Object use()
	{
		return null;
	}
	
	public void drawInfo(PApplet marker, int x, int y)
	{
		
	}
}

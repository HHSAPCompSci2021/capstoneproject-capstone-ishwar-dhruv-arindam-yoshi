package items;
import screen.ScreenObject;
import processing.core.*;

public class Trap extends Item {
	
	// has damage intensity

	public Trap(int x, int y, int width, int height)
	{
		super(x, y, width, height);
	}
	
	public Object use()
	{
		return null;
	}
	
	public void drawInfo(PApplet marker, int x, int y)
	{
		
	}
}

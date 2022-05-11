package items;
import screen.ScreenObject;
import processing.core.*;

public class GeigerCounter extends Item {
	
	// has damage intensity

	public GeigerCounter(int x, int y, int width, int height)
	{
		super(x, y, width, height);
	}
	
	public Object use()
	{
		return null;
	}
	
	public int getRadiationLevel()
	{
		return 0;
	}
	
	public void drawInfo(PApplet marker, int x, int y)
	{
		
	}
}

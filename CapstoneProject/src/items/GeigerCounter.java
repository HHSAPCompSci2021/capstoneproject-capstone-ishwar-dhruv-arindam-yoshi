package items;
import screen.ScreenObject;
import processing.core.*;

public class GeigerCounter extends Item<Double> {
	
	// TO-DO
		// should return distance between Grinch and Officer
	
	// has damage intensity

	public GeigerCounter(int x, int y, int width, int height)
	{
		super(x, y, width, height);
	}
	
	@Override
	public Double use()
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

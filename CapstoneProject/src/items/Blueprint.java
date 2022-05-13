package items;
import screen.ScreenObject;
import processing.core.*;
import game.HauntedMaze;
public class Blueprint extends Item {
	
	// has identifier

	private String identifier;
	public Blueprint(double x, double y, String identify)
	{
		super(x, y, 15, 15);
		identifier = identify;
	}
	
	public void use(HauntedMaze maze)
	{
		
	}
	
	public void drawInfo(PApplet marker, int x, int y)
	{
		
		marker.push();
		marker.fill(137, 207, 240);

		marker.rect((float)x, (float)y, (float)getW(), (float)getH());
		
		String str = "BLUEPRINT" + identifier;
		float w = marker.textWidth(str);
		marker.text(str, (float) (x), (float) y);
		marker.pop();
	}
	
	
}

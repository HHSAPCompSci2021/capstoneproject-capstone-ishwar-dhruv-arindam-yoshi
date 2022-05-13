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
	
	public void use(HauntedMaze maze)
	{
		
	}
	
	public void drawInfo(PApplet marker, int x, int y)
	{
		
		marker.push();
		marker.fill(137, 207, 240);

		marker.rect((float)x, (float)y, (float)getW(), (float)getH());
		
		String str = "BLUEPRINT";
		float w = marker.textWidth(str);
		marker.text(str, (float) (x+getW()/2-w/2), (float) (y+getH()/2));
		marker.pop();
	}
	
	
}

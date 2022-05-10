package game;
import items.*;
import screen.*;
import processing.core.*;

/**
 * @author Arindam
 *
 */
public class InfoBar extends ScreenObject {
	
	// has access to main officer 
	
	public InfoBar()
	{
		super(0, 0, 100, 300);
	}
	
	public void draw(PApplet marker)
	{
		marker.push();
		
		x = marker.width-w - 50;
		y = 30;
		
		marker.rect((float)x, (float)y, (float)w, (float)h);
		marker.fill(0, 0, 0);
		marker.text("Info Bar", (float)(x + w/2), (float)(y + h/2));
		
		marker.pop();
	}
}

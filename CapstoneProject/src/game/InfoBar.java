package game;
import characters.Officer;
import items.*;
import screen.*;
import processing.core.*;

/**
 * @author Arindam
 *
 */
public class InfoBar extends ScreenObject {
	
	// has access to main officer 
	// will print important things about officer
	private Officer officer;
	
	public InfoBar(Officer o)
	{
		//x, y, width, height
		super(0, 0, 100, 300);
		officer = o;
	}
	
	public void draw(PApplet marker)
	{
		marker.push();
		
		x = marker.width-w - 100;
		y = 30;
		
		marker.rect((float)x, (float)y, (float)180, (float)70);
		marker.fill(0, 0, 0);
		marker.text("Info Bar", (float)(x + 3), (float)(y + 15));
		marker.text("Health: " + officer.getHealth(), (float)(x + 3), (float)(y + 30));
		marker.text("Blueprints taken:", (float)(x+3), (float)(y + 45));
		
		for (Blueprint blueprint : officer.blueprints)
			blueprint.drawInfo(marker, x+3, y+60);
		
		
		marker.pop();
	}
	
	
}

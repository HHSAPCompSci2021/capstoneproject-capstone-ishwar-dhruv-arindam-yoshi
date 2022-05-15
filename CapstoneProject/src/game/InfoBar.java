package game;
import characters.Officer;
import items.*;
import screen.*;
import processing.core.*;

/**
 * Represents the infobar with information about the officer and blueprints collected
 * @author Arindam
 *
 */
public class InfoBar extends ScreenObject {
	
	// has access to main officer 
	// will print important things about officer
	private Officer officer;
	
	/**
	 * Initializes an InfoBar object
	 * @param o is the officer playing
	 */
	public InfoBar(Officer o)
	{
		//x, y, width, height
		super(0, 0, 220, 200);
		officer = o;
	}
	
	/**
	 * draws the infobar on the top right corner of the game
	 * @param marker is the marker used to draw
	 */
	public void draw(PApplet marker)
	{
		marker.push();
		
		x = marker.width-w - 10;
		y = 30;
		
		marker.rect((float)x, (float)y, (float)w, (float)h);
		marker.fill(0, 0, 0);
		marker.text("Info Bar", (float)(x + 3), (float)(y + 15));
		marker.text("Health: " + officer.getHealth(), (float)(x + 3), (float)(y + 30));
		officer.gtool.drawInfo(marker, x + 3, y + 45);
		marker.text("Blueprints taken:", (float)(x+3), (float)(y + 120));
		
		for (Blueprint blueprint : officer.blueprints)
			blueprint.drawInfo(marker, x+3, y+60);
		
		
		marker.pop();
	}
	
	
}

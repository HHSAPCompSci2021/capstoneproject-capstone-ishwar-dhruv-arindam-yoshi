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
	private PImage fullHeart;
	private PImage halfHeart;
	private PImage emptyHeart;
	private PImage infobar;
	
	private static final String fullHeart_PATH = "assets/fullheart.png";
	
	private static final String infobar_PATH = "assets/infobar.png";

	
	private static final String halfHeart_PATH = "assets/halfheart.png";
	
	
	private static final String emptyHeart_PATH = "assets/emptyheart.png";
	
	
	private static final double[] COLOR_30 = {245, 86, 86};
	private static final double[] COLOR_5 = {255, 255, 255};
	
	/**
	 * Initializes an InfoBar object
	 * @param marker the PApplet object with which images are loaded
	 * @param o is the officer playing
	 */
	public InfoBar(PApplet marker, Officer o)
	{
		//x, y, width, height
		super(0, 0, 220, 250);
		officer = o;
		fullHeart = marker.loadImage(InfoBar.fullHeart_PATH);
		halfHeart = marker.loadImage(InfoBar.halfHeart_PATH);
		emptyHeart = marker.loadImage(InfoBar.emptyHeart_PATH);
		infobar = marker.loadImage(InfoBar.infobar_PATH);

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
		double ht = 0; // distance of what is being currently drawn to the top of infobar
		
		// marker.rect((float)x, (float)y, (float)w, (float)h);
		// marker.line((float)x, (float)y, (float)(x+w), (float)y);
		// marker.line((float)x, (float)y, (float)x, (float)(y+h));
		// marker.line((float)(x+w), (float)y, (float)(x+w), (float)(y+h));
		marker.fill(0, 0, 0);
		marker.image(infobar, (float)x-140, (float)y, 120, 120);

		ht += 50;
		marker.text("Health: " + officer.getHealth(), (float)(x + 3), (float)(y + ht));
		
		int numFullHearts = (int)Math.floor(officer.getHealth()/20);
		int numHalfHearts = (int)Math.floor(officer.getHealth()/10) - 2*(int)Math.floor(officer.getHealth()/20);
		
		ht += 10;
		for (int i = 0; i < 5; i++)
			if (i < numFullHearts)
				marker.image(fullHeart, (float)x + 3+17*i, (float)(y + ht), 15, 15);
			else if (i < numFullHearts + numHalfHearts)
				marker.image(halfHeart, (float)x + 3+17*i, (float)(y + ht), 15, 15);
			else
				marker.image(emptyHeart, (float)x + 3+17*i, (float)(y + ht), 15, 15);
		
		ht += 20;
		officer.gtool.drawInfo(marker, x + 3, y + ht);
		
		ht += 60;
		marker.text("Blueprints taken:", (float)(x+3), (float)(y + ht));
			
		ht += 20;
		for (Blueprint blueprint : officer.blueprints) {
			blueprint.drawInfo(marker, x+3, y+ht);
			ht += 20;
		}
		
		marker.text("Teleporters:", (float)(x+3), (float)(y + ht));
		
		ht += 10;
		
		for (int i = 0; i < officer.teleporters.size(); i++)
			officer.teleporters.get(i).drawInfo(marker, x + 3+17*i, y+ht);
				
		ht += 30;
		
		marker.noFill();
		marker.stroke(0, 0, 0);
		marker.rect((float)x, (float)y, (float)w, (float)ht);
		 
		marker.pop();
	}
	
	/**
	 * Returns the background color of the screen in an array of 3 doubles.
	 * @return an array of 3 doubles with RGB values for the background color of the screen
	 */
	public int[] getBackground()
	{
		double[] color = new double[3];
		
		GeigerCounter g = officer.gtool;
		
		for (int i = 0; i < 3; i++)
			color[i] = (1 - (30-g.getReading())/25)*COLOR_30[i]
					 + (1 - (g.getReading()-5)/25)*COLOR_5[i];
		
		int[] colorInt = new int[3];
		for (int i = 0; i < 3; i++)
			colorInt[i] = (int)(Math.min(255, Math.max(0, color[i])));
		
		return colorInt;
	}
	
}

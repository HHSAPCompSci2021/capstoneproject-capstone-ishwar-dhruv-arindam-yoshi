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
	
	/**
	 * Access path to the full heart image
	 */
	public static final String fullHeart_PATH = "assets/fullheart.png";
	
	/**
	 * Access path to the half heart image
	 */
	public static final String halfHeart_PATH = "assets/halfheart.png";
	
	/**
	 * Access path to the empty heart image
	 */
	public static final String emptyHeart_PATH = "assets/emptyheart.png";
	/**
	 * Initializes an InfoBar object
	 * @param o is the officer playing
	 */
	public InfoBar(PApplet marker, Officer o)
	{
		//x, y, width, height
		super(0, 0, 220, 200);
		officer = o;
		fullHeart = marker.loadImage(InfoBar.fullHeart_PATH);
		halfHeart = marker.loadImage(InfoBar.halfHeart_PATH);
		emptyHeart = marker.loadImage(InfoBar.emptyHeart_PATH);
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
		officer.gtool.drawInfo(marker, x + 3, y + 160);
		marker.text("Blueprints taken:", (float)(x+3), (float)(y + 120));
		
		if (officer.getHealth() == 100) {
			marker.image(fullHeart, (float)x + 3, (float)y + 200, 15, 15);
			marker.image(fullHeart, (float)x + 20, (float)y + 200, 15, 15);
			marker.image(fullHeart, (float)x + 37, (float)y + 200, 15, 15);
			marker.image(fullHeart, (float)x + 54, (float)y + 200, 15, 15);
			marker.image(fullHeart, (float)x + 71, (float)y + 200, 15, 15);
		}
		
		else if (officer.getHealth() >= 90) {
			marker.image(fullHeart, (float)x + 3, (float)y + 200, 15, 15);
			marker.image(fullHeart, (float)x + 20, (float)y + 200, 15, 15);
			marker.image(fullHeart, (float)x + 37, (float)y + 200, 15, 15);
			marker.image(fullHeart, (float)x + 54, (float)y + 200, 15, 15);
			marker.image(halfHeart, (float)x + 71, (float)y + 200, 15, 15);

		}
		
		else if (officer.getHealth() >= 80) {
			marker.image(fullHeart, (float)x + 3, (float)y + 200, 15, 15);
			marker.image(fullHeart, (float)x + 20, (float)y + 200, 15, 15);
			marker.image(fullHeart, (float)x + 37, (float)y + 200, 15, 15);
			marker.image(fullHeart, (float)x + 54, (float)y + 200, 15, 15);
			marker.image(emptyHeart, (float)x + 71, (float)y + 200, 15, 15);

		}
		
		else if (officer.getHealth() >= 70) {
			marker.image(fullHeart, (float)x + 3, (float)y + 200, 15, 15);
			marker.image(fullHeart, (float)x + 20, (float)y + 200, 15, 15);
			marker.image(fullHeart, (float)x + 37, (float)y + 200, 15, 15);
			marker.image(halfHeart, (float)x + 54, (float)y + 200, 15, 15);
			marker.image(emptyHeart, (float)x + 71, (float)y + 200, 15, 15);

		}
		
		else if (officer.getHealth() >= 60) {
			marker.image(fullHeart, (float)x + 3, (float)y + 200, 15, 15);
			marker.image(fullHeart, (float)x + 20, (float)y + 200, 15, 15);
			marker.image(fullHeart, (float)x + 37, (float)y + 200, 15, 15);
			marker.image(emptyHeart, (float)x + 54, (float)y + 200, 15, 15);
			marker.image(emptyHeart, (float)x + 71, (float)y + 200, 15, 15);

		}
		
		else if (officer.getHealth() >= 50) {
			marker.image(fullHeart, (float)x + 3, (float)y + 200, 15, 15);
			marker.image(fullHeart, (float)x + 20, (float)y + 200, 15, 15);
			marker.image(halfHeart, (float)x + 37, (float)y + 200, 15, 15);
			marker.image(emptyHeart, (float)x + 54, (float)y + 200, 15, 15);
			marker.image(emptyHeart, (float)x + 71, (float)y + 200, 15, 15);

		}
		
		else if (officer.getHealth() >= 40) {
			marker.image(fullHeart, (float)x + 3, (float)y + 200, 15, 15);
			marker.image(fullHeart, (float)x + 20, (float)y + 200, 15, 15);
			marker.image(emptyHeart, (float)x + 37, (float)y + 200, 15, 15);
			marker.image(emptyHeart, (float)x + 54, (float)y + 200, 15, 15);
			marker.image(emptyHeart, (float)x + 71, (float)y + 200, 15, 15);

		}
		
		else if (officer.getHealth() >= 30) {
			marker.image(fullHeart, (float)x + 3, (float)y + 200, 15, 15);
			marker.image(halfHeart, (float)x + 20, (float)y + 200, 15, 15);
			marker.image(emptyHeart, (float)x + 37, (float)y + 200, 15, 15);
			marker.image(emptyHeart, (float)x + 54, (float)y + 200, 15, 15);
			marker.image(emptyHeart, (float)x + 71, (float)y + 200, 15, 15);

		}
		
		else if (officer.getHealth() >= 20) {
			marker.image(fullHeart, (float)x + 3, (float)y + 200, 15, 15);
			marker.image(emptyHeart, (float)x + 20, (float)y + 200, 15, 15);
			marker.image(emptyHeart, (float)x + 37, (float)y + 200, 15, 15);
			marker.image(emptyHeart, (float)x + 54, (float)y + 200, 15, 15);
			marker.image(emptyHeart, (float)x + 71, (float)y + 200, 15, 15);

		}
		
		else if (officer.getHealth() >= 10) {
			marker.image(halfHeart, (float)x + 3, (float)y + 200, 15, 15);
			marker.image(emptyHeart, (float)x + 20, (float)y + 200, 15, 15);
			marker.image(emptyHeart, (float)x + 37, (float)y + 200, 15, 15);
			marker.image(emptyHeart, (float)x + 54, (float)y + 200, 15, 15);
			marker.image(emptyHeart, (float)x + 71, (float)y + 200, 15, 15);

		}
		
		else {
			marker.image(emptyHeart, (float)x + 3, (float)y + 200, 15, 15);
			marker.image(emptyHeart, (float)x + 20, (float)y + 200, 15, 15);
			marker.image(emptyHeart, (float)x + 37, (float)y + 200, 15, 15);
			marker.image(emptyHeart, (float)x + 54, (float)y + 200, 15, 15);
			marker.image(emptyHeart, (float)x + 71, (float)y + 200, 15, 15);

		}
			
		int i = 0;
		for (Blueprint blueprint : officer.blueprints) {
			blueprint.drawInfo(marker, x+3, y+60 + i);
			i += 20;
		}
		 
		marker.pop();
	}
	
	
}

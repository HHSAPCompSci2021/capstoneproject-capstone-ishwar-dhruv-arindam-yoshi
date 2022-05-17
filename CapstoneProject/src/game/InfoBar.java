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
	public static final String fullHeart_PATH = "fullheart.png";
	public static final String halfHeart_PATH = "halfheart.jpg";
	public static final String emptyHeart_PATH = "emptyheart.png";
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
		officer.gtool.drawInfo(marker, x + 3, y + 160);
		marker.text("Blueprints taken:", (float)(x+3), (float)(y + 120));
		PImage img1 = marker.loadImage(InfoBar.fullHeart_PATH);
		PImage img2 = marker.loadImage(InfoBar.halfHeart_PATH);
		PImage img3 = marker.loadImage(InfoBar.emptyHeart_PATH);
		
		if (officer.getHealth() == 100) {
			marker.image(img1, (float)x + 3, (float)y + 200, 15, 15);
			marker.image(img1, (float)x + 20, (float)y + 200, 15, 15);
			marker.image(img1, (float)x + 37, (float)y + 200, 15, 15);
			marker.image(img1, (float)x + 54, (float)y + 200, 15, 15);
			marker.image(img1, (float)x + 71, (float)y + 200, 15, 15);
		}
		
		else if (officer.getHealth() >= 90) {
			marker.image(img1, (float)x + 3, (float)y + 200, 15, 15);
			marker.image(img1, (float)x + 20, (float)y + 200, 15, 15);
			marker.image(img1, (float)x + 37, (float)y + 200, 15, 15);
			marker.image(img1, (float)x + 54, (float)y + 200, 15, 15);
			marker.image(img2, (float)x + 71, (float)y + 200, 15, 15);

		}
		
		else if (officer.getHealth() >= 80) {
			marker.image(img1, (float)x + 3, (float)y + 200, 15, 15);
			marker.image(img1, (float)x + 20, (float)y + 200, 15, 15);
			marker.image(img1, (float)x + 37, (float)y + 200, 15, 15);
			marker.image(img1, (float)x + 54, (float)y + 200, 15, 15);
			marker.image(img3, (float)x + 71, (float)y + 200, 15, 15);

		}
		
		else if (officer.getHealth() >= 70) {
			marker.image(img1, (float)x + 3, (float)y + 200, 15, 15);
			marker.image(img1, (float)x + 20, (float)y + 200, 15, 15);
			marker.image(img1, (float)x + 37, (float)y + 200, 15, 15);
			marker.image(img2, (float)x + 54, (float)y + 200, 15, 15);
			marker.image(img3, (float)x + 71, (float)y + 200, 15, 15);

		}
		
		else if (officer.getHealth() >= 60) {
			marker.image(img1, (float)x + 3, (float)y + 200, 15, 15);
			marker.image(img1, (float)x + 20, (float)y + 200, 15, 15);
			marker.image(img1, (float)x + 37, (float)y + 200, 15, 15);
			marker.image(img3, (float)x + 54, (float)y + 200, 15, 15);
			marker.image(img3, (float)x + 71, (float)y + 200, 15, 15);

		}
		
		else if (officer.getHealth() >= 50) {
			marker.image(img1, (float)x + 3, (float)y + 200, 15, 15);
			marker.image(img1, (float)x + 20, (float)y + 200, 15, 15);
			marker.image(img2, (float)x + 37, (float)y + 200, 15, 15);
			marker.image(img3, (float)x + 54, (float)y + 200, 15, 15);
			marker.image(img3, (float)x + 71, (float)y + 200, 15, 15);

		}
		
		else if (officer.getHealth() >= 40) {
			marker.image(img1, (float)x + 3, (float)y + 200, 15, 15);
			marker.image(img1, (float)x + 20, (float)y + 200, 15, 15);
			marker.image(img3, (float)x + 37, (float)y + 200, 15, 15);
			marker.image(img3, (float)x + 54, (float)y + 200, 15, 15);
			marker.image(img3, (float)x + 71, (float)y + 200, 15, 15);

		}
		
		else if (officer.getHealth() >= 30) {
			marker.image(img1, (float)x + 3, (float)y + 200, 15, 15);
			marker.image(img2, (float)x + 20, (float)y + 200, 15, 15);
			marker.image(img3, (float)x + 37, (float)y + 200, 15, 15);
			marker.image(img3, (float)x + 54, (float)y + 200, 15, 15);
			marker.image(img3, (float)x + 71, (float)y + 200, 15, 15);

		}
		
		else if (officer.getHealth() >= 20) {
			marker.image(img1, (float)x + 3, (float)y + 200, 15, 15);
			marker.image(img3, (float)x + 20, (float)y + 200, 15, 15);
			marker.image(img3, (float)x + 37, (float)y + 200, 15, 15);
			marker.image(img3, (float)x + 54, (float)y + 200, 15, 15);
			marker.image(img3, (float)x + 71, (float)y + 200, 15, 15);

		}
		
		else if (officer.getHealth() >= 10) {
			marker.image(img2, (float)x + 3, (float)y + 200, 15, 15);
			marker.image(img3, (float)x + 20, (float)y + 200, 15, 15);
			marker.image(img3, (float)x + 37, (float)y + 200, 15, 15);
			marker.image(img3, (float)x + 54, (float)y + 200, 15, 15);
			marker.image(img3, (float)x + 71, (float)y + 200, 15, 15);

		}
		
		else {
			marker.image(img3, (float)x + 3, (float)y + 200, 15, 15);
			marker.image(img3, (float)x + 20, (float)y + 200, 15, 15);
			marker.image(img3, (float)x + 37, (float)y + 200, 15, 15);
			marker.image(img3, (float)x + 54, (float)y + 200, 15, 15);
			marker.image(img3, (float)x + 71, (float)y + 200, 15, 15);

		}
			
		int i = 0;
		for (Blueprint blueprint : officer.blueprints) {
			blueprint.drawInfo(marker, x+3, y+60 + i);
			i += 20;
		}
		
		marker.pop();
	}
	
	
}

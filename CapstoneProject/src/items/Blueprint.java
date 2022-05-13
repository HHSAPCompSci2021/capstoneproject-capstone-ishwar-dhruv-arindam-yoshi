package items;
import screen.ScreenObject;

import processing.core.*;
import game.HauntedMaze;

/**
 * Represents the blueprint the officer needs to collect
 * @author akulkarni174
 *
 */
public class Blueprint extends Item {
	protected PImage pin;
	// has identifier

	/**
	 * path to image used for blueprints
	 */
	public static final String pin_PATH = "assets/pin.png";
	private String identifier;
	
	/**
	 * 
	 * @param x is an x coordinate for blueprint
	 * @param y is a y coordinate for blueprint
	 * @param identify is the identification used to access img
	 * @param img is the image used to show blueprint in the maze
	 */
	public Blueprint(double x, double y, String identify, PImage img)
	{
		super(x, y, 15, 15);
		identifier = identify;
		pin = img;
	}
	
	
	public void use(HauntedMaze maze)
	{
		
	}
	
	
	public void drawInfo(PApplet marker, double x, double y)
	{
		
		marker.push();
		marker.fill(137, 207, 240);

		marker.rect((float)x, (float)y, (float)getW(), (float)getH());
		
		String str = "BLUEPRINT " + identifier;
		float w = marker.textWidth(str);
		marker.text(str, (float) (x), (float) y);
		marker.pop();
	}
	
	/**
	 * Draws a blueprint pin in the info bar
	 * @param marker is what you draw on
	 */
	public void draw(PApplet marker) {
		marker.push();
		
		marker.fill(0);
		marker.image(pin, (float)x, (float)y, (float)w, (float)h);
		
		marker.pop();
	}
	
	
}

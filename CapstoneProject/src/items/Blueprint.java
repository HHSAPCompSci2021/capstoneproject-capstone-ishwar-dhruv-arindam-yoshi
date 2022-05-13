package items;
import screen.ScreenObject;
import processing.core.*;
import game.HauntedMaze;
public class Blueprint extends Item {
	protected PImage pin;
	// has identifier

	public static final String pin_PATH = "assets/pin.png";
	private String identifier;
	public Blueprint(double x, double y, String identify, PImage img)
	{
		super(x, y, 15, 15);
		identifier = identify;
		pin = img;
	}
	
	public void use(HauntedMaze maze)
	{
		
	}
	
	/**
	 * Draws a blue blueprint icon in the info bar
	 * @param marker is what you draw on
	 * @param x is x coordinate where it's drawn
	 * @param y is y coordinate where it's drawn
	 */
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

package items;
import screen.ScreenObject;
import processing.core.*;
import game.HauntedMaze;
public abstract class Item extends ScreenObject {
	
	/**
	 * 
	 * @param x is x coordinate of item
	 * @param y is y coordinate of item
	 * @param width is width of item displayed
	 * @param height is height of item displayed
	 */
	public Item(double x, double y, double width, double height) {
		super(x, y, width, height);
	}
	
	/**
	 * acts on the actions necessary for the eitem
	 * @param maze is the maze through which game is played
	 */
	public abstract void use(HauntedMaze maze);

	/**
	 * draws info about the item in the infobar
	 * @param marker is the thing used to draw
	 * @param x is x coordinate of drawing location
	 * @param y is y coordinate of drawing location
	 */
	public abstract void drawInfo(PApplet marker, double x, double y); 
	
	
}


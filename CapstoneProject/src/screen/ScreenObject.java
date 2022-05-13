package screen;
import processing.core.*;

import java.awt.geom.Rectangle2D;
/**
 * @author Ishwar S.
 */
public abstract class ScreenObject {
	
	// has an x and y value for position
	
	protected double x, y, w, h;
	
	// public ScreenObject()
	// {
	// 	x = 0; y = 0; w = 0; h = 0;
	// }
	
	public ScreenObject(double x, double y, double w, double h)
	{
		this.x = x; this.y = y;
		this.w = w; this.h = h;
	}
	
	/**
	 * Returns the x-value of this ScreenObject
	 * @return the x-value of this ScreenObject
	 */
	public double getX()
	{
		return x;
	}
	
	/**
	 * Returns the y-value of this ScreenObject
	 * @return the y-value of this ScreenObject
	 */
	public double getY()
	{
		return y;
	}
	
	/**
	 * Returns the width of this ScreenObject
	 * @return the width of this ScreenObject
	 */
	public double getW()
	{
		return w;
	}
	
	/**
	 * Returns the height of this ScreenObject
	 * @return the height of this ScreenObject
	 */
	public double getH()
	{
		return h;
	}
	
	/**
	 * Sets the position of this ScreenObject
	 * @param newX the new x-coordinate of this ScreenObject
	 * @param newY the new y-coordinate of this ScreenObject
	 */
	public void setPos(double newX, double newY)
	{
		this.x = newX; this.y = newY;
	}
	
	/**
	 * Shifts the current position of this ScreenObject
	 * @param shiftX the x-shift of the position
	 * @param shiftY the y-shift of the position
	 */
	public void movePos(double shiftX, double shiftY)
	{
		this.x += shiftX; this.y += shiftY;
	}
	
	/**
	 * Draws this ScreenObject on the screen.
	 * @param marker the PApplet object with which to draw this ScreenObject
	 */
	public void draw(PApplet marker)
	{
		
	}

}

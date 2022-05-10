package screen;
import processing.core.*;

import java.awt.geom.Rectangle2D;
/**
 * 
 * @author all
 *
 */
public abstract class ScreenObject {
	
	// has an x and y value for position
	
	protected double x, y, w, h;
	
	public ScreenObject()
	{
		x = 0; y = 0; w = 0; h = 0;
	}
	
	public ScreenObject(double x, double y, double w, double h)
	{
		this.x = x; this.y = y;
		this.w = w; this.h = h;
	}
	
	public void setPos(double newX, double newY)
	{
		this.x = newX; this.y = newY;
	}
	
	public void movePos(double shiftX, double shiftY)
	{
		this.x += shiftX; this.y += shiftY;
	}
	
	public void draw(PApplet marker)
	{
		
	}

}

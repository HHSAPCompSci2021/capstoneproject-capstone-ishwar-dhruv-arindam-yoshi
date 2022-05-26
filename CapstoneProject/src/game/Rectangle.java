package game;

import java.awt.geom.Line2D;

import characters.Actor;


/**
 * This class represents a rectangle
 * @author Dhruv Lohani
 *
 */
public class Rectangle {
	/**
	 * x1 represents the upper left X coordinate of the rectangle
	 * y1 represents the upper left Y coordinate of the rectangle
	 * x2 represents the lower right X coordinate of the rectangle
	 * y2 represents the lower right Y coordinate of the rectangle
	 * w represents the width of the rectangle
	 * h represents the height of the rectangle
	 */
	public double x1, y1, x2, y2, w, h; 
	
	/**
	 * Contructs a rectangle object where the fields are all 0. 
	 */
	public Rectangle() {
		x1 = y1 = x2 = y2 = w = h = 0; 
	}
	
	/**
	 * Constructs a rectangle object where the fields are the values passed.
	 * @param x1 the upper x coordinate of the rectangle
	 * @param y1 the upper y coordinate of the rectangle
	 * @param w the width of the rectangle
	 * @param h the height of the rectangle 
	 */
	public Rectangle(double x1, double y1, double w, double h) {
		this.x1 = x1; 
		this.y1 = y1; 
		this.w = w; 
		this.h = h;
		x2 = x1 + w; 
		y2 = y1 + h; 
	}
	
	/**
	 * Returns x coordinate of the rectangle
	 * @return the upper x coordinate of the rectangle
	 */
	public double getX() {
		return x1; 
	}
	
	/**
	 * Returns y coordinate of the rectangle
	 * @return the upper y coordinate of the rectangle
	 */
	public double getY() {
		return y1; 
	}
	
	/**
	 * Returns width of the rectangle
	 * @return the width of the rectangle
	 */
	public double getW() {
		return w; 
	}
	
	/**
	 * Returns height of the rectangle
	 * @return the height of the rectangle
	 */
	public double getH() {
		return h; 
	}
	
	
	/**
	 * This method finds the intersection region of two rectangles. 
	 * @param a the first rectangle which the method determines intersection for. 
	 * @param b the second rectangle which the method determines intersection for. 
	 * @return the Rectangle object representing the intersection; null if this Rectangle does not exist
	 */
	public static Rectangle intersects(Rectangle a, Rectangle b) {
		double minX = Math.max(a.x1, b.x1);
		double minY = Math.max(a.y1, b.y1);
		double maxX = Math.min(a.x2, b.x2);
		double maxY = Math.min(a.y2, b.y2);
		
		if ((minX <= maxX) && (minY <= maxY))
			return new Rectangle(minX, minY, maxX-minX, maxY-minY);
		return null;
	}
	
	/**
	 * Returns whether the item contains the given x-y coordinates
	 * @param xOfItem the x-coordinate of the point for testing
	 * @param yOfItem the y-coordinate of the point for testing
	 * @return
	 */
	public boolean contains(double xOfItem, double yOfItem) {
		if (xOfItem< (Math.max(x1, x2) + 20) 
		&&  xOfItem> (Math.min(x1, x2) - 20)
		&&  yOfItem< (Math.max(y1, y2) + 25)
		&&  yOfItem> (Math.min(y1, y2)) - 25) return true; 
		return false; 
	}
	

	
	
}

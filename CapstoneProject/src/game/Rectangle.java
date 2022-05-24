package game;

import java.awt.geom.Line2D;

import characters.Actor;

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
	 * Determines if the actor passed is touching the wall, and if so, then in what direction.
	 *  
	 * @param a the actor to which this wall might touch. 
	 * @return a boolean array of size 5, that represents whether an actor is touching a wall
	 * Position 0 of the array represents whether the wall is touching the actor
	 * Position 1 of the array represents whether the actor is touching the wall from above. 
	 * Position 2 of the array represents whether the actor is touching the wall from the right.
	 * Position 3 of the array represents whether the actor is touching the wall from the bottom
	 * Position 4 of the array represents whether the actor is touching the wall from the left. 
	 */
	public boolean[] isTouchingActor(Actor a) {
		boolean[] info = new boolean[5]; 
		info[0] = info[1] = info[2] = info[3] = info[4] = false; 
		
		Rectangle other = a.getBoundingRectangle(); 
		if (Rectangle.intersects(this, other)) {
			info[0] = true; 
			double[] thisX = {Math.min(this.x1, this.x2), Math.max(this.x1, this.x2)}; 
			double[] otherX = {Math.min(other.x1, other.x2), Math.max(other.x1,other.x2)}; 
			double[] thisY = {Math.min(this.y1, this.y2), Math.max(this.y1, this.y2)}; 
			double[] otherY = {Math.min(other.y1, other.y2), Math.max(other.y1, other.y2)}; 
			int[][] vals = {
							{0,0,1,0}, 
							{1,0,1,1},
							{0,1,1,1},
							{0,0,0,1}
						   };
			for (int i=0;i<4;i++) {
				Line2D line1 = new Line2D.Float((int)thisX[vals[i][0]], (int)thisY[vals[i][1]], 
						                        (int)thisX[vals[i][2]], (int)thisY[vals[i][3]]);
				for (int j=0;j<4;j++) {
					Line2D line2 = new Line2D.Float((int)otherX[vals[j][0]], (int)otherY[vals[j][1]], 
							                        (int)otherX[vals[j][2]], (int)otherY[vals[j][3]]);
					if (line1.intersectsLine(line2)) {
						info[i+1] = true; 
//						return info;
					}
				}
			}
		}
		int counter = 0; 
		for (int i=1;i<5;i++) {
			if (info[i]) counter++; 
		}
		if (counter > 1) {
			if (info[1] && info[3]) {
				info[1] = false; 
				info[3] = true; 
			}
			if (info[2] && info[4]) {
				info[2] = true; 
				info[4] = false; 
			}
			
//			
		}
		return info; 
	}

	
	/**
	 * This method determines whether two rectangles intersect. 
	 * @param a the first rectangle which the method determines intersection for. 
	 * @param b the second rectangle which the method determines intersection for. 
	 * @return
	 */
	public static boolean intersects(Rectangle a, Rectangle b) {
		double minX = Math.max(a.x1, b.x1);
		double minY = Math.max(a.y1, b.y1);
		double maxX = Math.min(a.x2, b.x2);
		double maxY = Math.min(a.y2, b.y2);
		
		if ((minX <= maxX) && (minY <= maxY))
			return true;
		return false;
	}
	
	public boolean contains(double xOfItem, double yOfItem) {
		if (xOfItem< (Math.max(x1, x2) + 20) 
		&&  xOfItem> (Math.min(x1, x2) - 20)
		&&  yOfItem< (Math.max(y1, y2) + 25)
		&&  yOfItem> (Math.min(y1, y2)) - 25) return true; 
		return false; 
	}
	

	
	
}

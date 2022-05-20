package game;

import java.awt.geom.Line2D;

import characters.Actor;

public class Rectangle {
	public double x1, y1, x2, y2, w, h; 
	
	
	public Rectangle() {
		x1 = y1 = x2 = y2 = w = h = 0; 
	}
	
	public Rectangle(double x1, double y1, double w, double h) {
		this.x1 = x1; 
		this.y1 = y1; 
		this.w = w; 
		this.h = h;
		x2 = x1 + w; 
		y2 = y1 + h; 
	}
	
	public double getX() {
		return x1; 
	}
	
	public double getY() {
		return y1; 
	}
	
	public double getW() {
		return w; 
	}
	
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
						break;
					}
				}
			}
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

	
	
}

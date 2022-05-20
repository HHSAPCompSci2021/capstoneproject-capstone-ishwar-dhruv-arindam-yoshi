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
	 * @param a
	 * @return a boolean array of size 5, that represents whether an actor is touching a wall
	 * Position 0 of the array represents whether the wall is touching the actor
	 * Position 1 of the array represents whether the actor is touching the wall from above. 
	 * Position 2 of the array represents whether the actor is touching the wall from the right.
	 * Position 3 of the array represents whether the actor is touching the wall from the bottom
	 * Position 4 of the array represents whether the actor is touching the wall from the left. 
	 */
	public boolean[] isTouchingActor(Actor a) {
		boolean[] info = new boolean[5]; 
		
		Rectangle other = a.getBoundingRectangle(); 
		if (Rectangle.intersects(this, other)) {
			info[0] = true; 
			double[] thisX = {this.x1, this.x2}; 
			double[] otherX = {other.x1, other.x2}; 
			double[] thisY = {this.y1, this.y2}; 
			double[] otherY = {other.y1, other.y2}; 
			int[][] vals = {
							{0,0,1,0}, 
							{1,0,1,1},
							{1,1,0,1},
							{0,1,0,1}
						   };
			for (int i=0;i<4;i++) {
				Line2D line1 = new Line2D.Float((int)thisX[vals[i][0]], (int)thisY[vals[i][1]], 
						                        (int)thisX[vals[i][2]], (int)thisY[vals[i][3]]);
				for (int j=0;j<4;j++) {
					Line2D line2 = new Line2D.Float((int)otherX[vals[i][0]], (int)otherY[vals[i][1]], 
							                        (int)otherX[vals[i][2]], (int)otherY[vals[i][3]]);
					if (line1.intersectsLine(line2)) {
						info[i+1] = true; 
					}
				}
			}
		}
		return info; 
	}

	public static boolean intersects(Rectangle a, Rectangle b) {
		if (b.getX() >= Math.min(a.getX(), a.getX() + a.getW()) && 
        		b.getX() <= Math.max(a.getX(), a.getX() + a.getW()) && 
        		b.getY() >= Math.min(a.getY(), a.getY() + a.getW()) && 
        		b.getY() <= Math.max(a.getY(), a.getY() + a.getH())) 
        			return true;
		return false; 
	}

	
	
}

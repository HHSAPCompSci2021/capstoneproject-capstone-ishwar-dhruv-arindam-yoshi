package game;
import processing.core.*;

public class MazeData {
	
	// has set of lines / walls and items
	
	public MazeData()
	{
		
	}
	
	public void drawWalls(PApplet marker)
	{
		
	}
	
	/**
	 * 
	 * @param marker the PApplet object which is used to draw
	 * @param x
	 * @param y
	 */
	public void drawIcon(PApplet marker, double x, double y, double s)
	{
		marker.push();
		
		marker.fill(255, 255, 255);
		marker.stroke(0, 0, 0);
		marker.rect((float)(x - s/2), (float)(y - s/2), (float)s, (float)s);
		
		marker.pop();
	}
}

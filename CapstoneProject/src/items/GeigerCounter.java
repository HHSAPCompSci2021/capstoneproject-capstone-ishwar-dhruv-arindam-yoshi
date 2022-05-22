package items;
import screen.ScreenObject;
import game.HauntedMaze;
import processing.core.*;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import game.*;

/**
 * This class represents a Geiger counter that detects the radiation level for the Officer.
 * @author isuriyaprakash686 and Dhruv Lohani
 *
 */
public class GeigerCounter extends Item {
	
	private static double PROP_CONST = 50*Math.pow(25, 1);
	
	// TO-DO
		// should return distance between Grinch and Officer
	
	private double radiationReading; // radioactivity measured in Bq
	
	// has damage intensity

	public GeigerCounter(double x, double y)
	{
		super(x, y, 205, 40);
	}
	
	/**
	 * Returns the radiation reading.
	 * @return the radiation reading (in Bq).
	 */
	public double getReading()
	{
		DecimalFormat df = new DecimalFormat("#.####");
		df.setRoundingMode(RoundingMode.CEILING);
		return Double.parseDouble(df.format(radiationReading)); 
	}
	

	public void use(HauntedMaze maze)
	{
		double px = maze.protagonist.getX(); double py = maze.protagonist.getY();
		double gx = maze.villain.getX(); double gy = maze.villain.getY();
		
		double dist = Math.sqrt(Math.pow(gx - px, 2) + Math.pow(gy - py, 2));
		
		radiationReading = PROP_CONST/dist;
	}
	

	@Override
	public void drawInfo(PApplet marker, double x, double y) {
		// TODO Auto-generated method stub
		marker.push();
		
		marker.stroke(0);
		marker.noFill();
		
		marker.rect((float)x, (float)y, (float)w, (float)h);
		
		marker.text("Radiation reading:", (float)(x+3), (float)(y+15));
		String readingStr = "" + getReading() + " Bq";
		if (getReading() >= 2) {
			marker.textSize((float) 15);
			marker.fill(124,252,0); 
		}else if (getReading() >= 1.5) {
			marker.textSize((float) 17);
			marker.fill(255,140,0); 
		}else {
			marker.textSize((float) 19);
			marker.fill(255,0,0); 
		}
		marker.text(readingStr, (float)(x+3), (float)(y+35));
		
		marker.pop();
	}
}

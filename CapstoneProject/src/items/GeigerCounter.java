package items;
import screen.ScreenObject;
import game.HauntedMaze;
import music.AudioPlayer;
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
	private double prevRadiationReading;
	private AudioPlayer soundEffects1; 
	
	// has damage intensity

	/**
	 * Creates an object of the GeigerCounter class
	 * @param x the x coordinate of where the object should be created
	 * @param y the y coordinate of where the object should be created
	 */
	public GeigerCounter(double x, double y)
	{
		super(x, y, 205, 40);
		soundEffects1 = new AudioPlayer(".//assets//heartbeat.wav", false); 
		radiationReading = prevRadiationReading = 0;
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
	
	/**
	 * Plays the sound effect for this item
	 */
	public void playSound() {
		if ((radiationReading > 12) && (prevRadiationReading <= 12)) {
			if (!soundEffects1.hasMusicStarted) {
				System.out.println("here");
				soundEffects1.play();
				soundEffects1.hasMusicStarted = true;
			}
		}
		else if ((radiationReading < 12) && (prevRadiationReading >= 12)) {
			soundEffects1.pause();
			soundEffects1.reset(); 
		}
	}
	
	@Override
	public void use(HauntedMaze maze)
	{
		double px = maze.protagonist.getX(); double py = maze.protagonist.getY();
		double gx = maze.villain.getX(); double gy = maze.villain.getY();
		
		double dist = Math.sqrt(Math.pow(gx - px, 2) + Math.pow(gy - py, 2));
		
		prevRadiationReading = radiationReading;
		radiationReading = PROP_CONST/dist;
		playSound();
	}
	

	@Override
	public void drawInfo(PApplet marker, double x, double y) {
		// TODO Auto-generated method stub
		marker.push();
		
		marker.stroke(0);
		// marker.noFill();
		marker.fill(50, 50, 50);
		
		marker.rect((float)x, (float)y, (float)w, (float)h);
		
		marker.fill(255);
		
		marker.text("Radiation reading:", (float)(x+3), (float)(y+15));
		String readingStr = "" + getReading() + " Bq";
		if (getReading() <= 12) {
			marker.textSize((float) 15);
			marker.fill(124,252,0); 
		}else if (getReading() <= 20) {
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

package screen;
import java.util.*;
import java.awt.Point;
import java.awt.Rectangle;

import core.DrawingSurface;
import processing.core.*;
import game.*;

/**
 * Represents the 
 * @author Ishwar S.
 *
 */
public class OptionScreen extends Screen {
	
	// has arraylist of maze data
	
	private ArrayList<MazeData> mazeList;
	private PFont titleFont;
	private String header = "Choose a maze to play in!";

	/**
	 * Constructs a new OptionScreen using 
	 * @param surface
	 */
	public OptionScreen(DrawingSurface surface) {
		super(800, 600);
		this.surface = surface;
		mazeList = new ArrayList<MazeData>();
	}
	
	public OptionScreen(DrawingSurface surface, ArrayList<MazeData> list)
	{
		super(800, 600);
		mazeList = list;
	}
	
	public void setup()
	{
		titleFont = surface.createFont("Georgia", 32);
	}
	
	public void addMaze(MazeData e)
	{
		mazeList.add(e);
	}
	
	public void draw()
	{
		surface.background(255, 255, 255);
		
		surface.fill(0);
		surface.textFont(titleFont);
		surface.text(header, (surface.width-surface.textWidth(header))/2, 100);
		
		for (int i = 0; i < mazeList.size(); i++)
		{
			int r = i / 5;
			int c = i % 5;
			
			
		}
	}
}
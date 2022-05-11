package screen;
import java.util.*;
import java.awt.*;
import java.awt.geom.Point2D;

import core.DrawingSurface;
import processing.core.*;
import game.*;

/**
 * Represents the screen in which the user selects pre-game options (such as which maze to play in)
 * @author Ishwar S.
 *
 */
public class OptionScreen extends Screen {
	
	// has arraylist of maze data
	
	private ArrayList<MazeData> mazeList;
	private PFont titleFont;
	private String header = "Select a maze to play in!";
	
	private final int headerHeight = 200, optWidth = 500;

	/**
	 * Constructs a new OptionScreen using a DrawingSurface object
	 * @param surface the DrawingSurface object with which the program is displaying output and receiving user input
	 */
	public OptionScreen(DrawingSurface surface) {
		super(800, 600);
		this.surface = surface;
		mazeList = new ArrayList<MazeData>();
	}
	
	/**
	 * Constructs a new OptionScreen using a DrawingSurface object and a list of MazeData objects
	 * @param surface the DrawingSurface object with which the program is displaying output and receiving user input
	 * @param list the list of MazeData objects
	 */
	public OptionScreen(DrawingSurface surface, ArrayList<MazeData> list)
	{
		super(800, 600);
		mazeList = list;
	}
	
	public void setup()
	{
		titleFont = surface.createFont("Georgia", 32);
		for (int i = 0; i < 8; i++)
			addMaze(new MazeData());
	}
	
	/**
	 * Adds a MazeData object to the maze list
	 * @param e the MazeData object to add
	 */
	public void addMaze(MazeData e)
	{
		mazeList.add(e);
	}
	
	public void draw()
	{
		surface.background(255, 255, 255);
		
		surface.fill(0);
		surface.textFont(titleFont);
		surface.text(header, (surface.width-surface.textWidth(header))/2, headerHeight);
		
		for (int i = 0; i < mazeList.size(); i++)
		{
			Point2D pos = indexToPos(i);
			mazeList.get(i).drawIcon(surface, pos.getX(), pos.getY(), 60);
		}
	}
	
	/**
	 * Converts index of a MazeData object to its coordinates on the screen
	 * @param i the index of the MazeData object
	 * @return the coordinates where it will be drawn
	 */
	public Point2D.Double indexToPos(int i)
	{
		if ((i >= mazeList.size()) || (i < 0))
			throw new IndexOutOfBoundsException("Index given is not valid.");
		
		int r = i / 5;
		int c = i % 5;
		
		double x = surface.width/2.0 + 80*(c-2);
		double y = headerHeight+100 + 80*r;
		
		return new Point2D.Double(x, y);
	}
	
	/**
	 * Gets the index of the MazeData object that was clicked
	 * @param pos the coordinates of the mouse click
	 * @return the coordinates where it will be drawn
	 */
	public int clickToIndex(Point2D.Double pos)
	{
		return 0;
	}
}
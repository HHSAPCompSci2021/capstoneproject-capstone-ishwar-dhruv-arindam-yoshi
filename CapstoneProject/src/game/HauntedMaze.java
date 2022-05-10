package game;
import java.util.ArrayList;

import characters.Grinch;
import characters.Officer;
import items.*;
import screen.*;
import processing.core.*;


/**
 * has characters, items, maze walls
 * @author dhruv
 *
 */
public class HauntedMaze extends ScreenObject {
	
	// has mazedata, actors, and items
	
	private Officer protagonist;
	private Grinch villain;
	private MazeData data;
	private ArrayList<Item> items;
	
	public HauntedMaze()
	{
		super(0, 0, 200, 200);
		protagonist = new Officer();
		villain = new Grinch();
		data = new MazeData();
		items = new ArrayList<Item>();
	}
	
	public void draw(PApplet marker)
	{
		marker.push();
		
		x = (marker.width-w)/2.0;
		y = (marker.height-h)/2.0;
		// mazeData.drawWalls(marker); 
		
		marker.rect((float)x, (float)y, (float)w, (float)h);
		marker.fill(0, 0, 0);
		marker.text("Haunted Maze", (float)(x + w/2), (float)(y + h/2));
		
		marker.pop();
	}
	
	public void addItem(Item e)
	{
		items.add(e);
	}

}

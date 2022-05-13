package game;
import java.util.ArrayList;

import characters.Grinch;
import characters.Officer;
import items.*;
import screen.*;
import processing.core.*;
import java.awt.Point;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import processing.core.PApplet;

/**
 * has characters, items, maze walls
 * @author dhruv
 *
 */

class gridP {

	int hrow, vrow, visited, n, s, e, w;
	public int num;

	public gridP(int x, int y) {
		this.hrow = x;
		this.vrow = y;
		n = s = e = w = 1;
		visited = 0;
	}

	ArrayList<gridP> adjlist = new ArrayList<gridP>();

}


public class MazeData {
	
	// has mazedata, actors, and items

	private int maze[][], size; 
	
	public MazeData()
	{
		maze = new int[25][25]; 
		
	}
	
	public MazeData(int dimensions) {
		this.size = dimensions; 
		maze = new int[dimensions][dimensions]; 
		this.generateNewMaze(); 
		
	}
	
	public MazeData(int size, String filename) {
		this.size = size; 
		maze = new int[size][size]; 
		this.readData(filename, maze); 	
	}
	
	
	public void readData(String filename, int grid[][]) {
		File dataFile = new File(filename);

		if (dataFile.exists()) {
			int count = 0;

			FileReader reader = null;
			Scanner in = null;
			try {
					reader = new FileReader(dataFile);
					in = new Scanner(reader);
					
					while (in.hasNext()) {
						String line = in.nextLine();
						for(int i = 0; i < line.length(); i++)
							if (count < grid.length && i < grid[count].length)
								grid[count][i] = line.charAt(i);

						count++;
					}

			} catch (IOException ex) {
				throw new IllegalArgumentException("Data file " + filename + " cannot be read.");
			} finally {
				if (in != null)
					in.close();
			}
		} else {
			throw new IllegalArgumentException("Data file " + filename + " does not exist.");
		}
	}
	
	
	
	public void draw(PApplet marker, float x, float y, float width, float height) {
		
	}
	
	
	
	public void generateNewMaze() {
		
	}
	
	
	
	public void clickToIndex(Point p) {
		
	}
	

	
	
	
	
	
	
	
	
	

}



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
public class MazeData {
	
	// has mazedata, actors, and items
	




	private int grid[][]; 
	
	public MazeData()
	{
		grid = new int[20][20]; 
		
	}
	
	/**
	 * Construct an empty 2D array with dimensions width and height, then fill it with data from the file filename.
	 * 
	 * @param width The width of the grid.
	 * @param height The height of the grid.
	 * @param filename The text file to read from.
	 */
	public MazeData(int width, int height, String filename) {
		grid = new int[width][height]; 
		this.readData(filename, grid); 	
	}
	
	
	
	
	public void generateMaze() {
		
	}

	/**
	 * (Graphical UI)
	 * Determines which element of the grid matches with a particular pixel coordinate.
	 * This supports interaction with the grid using mouse clicks in the window.
	 * 
	 * @param p A Point object containing a graphical pixel coordinate.
	 * @param x The x pixel coordinate of the upper left corner of the grid drawing. 
	 * @param y The y pixel coordinate of the upper left corner of the grid drawing.
	 * @param width The pixel width of the grid drawing.
	 * @param height The pixel height of the grid drawing.
	 * @return A Point object representing a coordinate within the grid, or null if the pixel coordinate
	 * falls completely outside of the grid.
	 */
	public Point clickToIndex(Point p, float x, float y, float width, float height) {
		Point gridP = new Point(); 
		
		float rw = width / grid[0].length;
		float rh = height / grid.length; 
		
		if (p.x > x && p.x < x+width && p.y > y && p.y < y+height) {
			for (int i=0;i<grid[0].length;i++) {
				if (p.x>=i*rh && p.x<=(i+1)*rh) {
					gridP.y = i;
				}
			}
			
			for (int j=0;j<grid.length;j++) {
				if (p.y>=j*rw && p.y<=(j+1)*rw) {
					gridP.x = j;
				}
			}
			
		}
		return gridP; 
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
		marker.fill(255);
		
		float rw = width / grid[0].length;
		float rh = height / grid.length;  
		
		for (int i=0;i<grid.length;i++) {
			for (int j=0;j<grid[i].length;j++) {
				float rx = x + j * rw; 
				float ry = y + i * rh; 
				
				marker.rect(rx, ry, rw, rh);
				
			}
		}
	}
	
	/**
	 * This method nicely prints the grid to the commandline.
	 */
	public String toString() {
		String out = ""; 
		for (int i=0;i<grid.length;i++){
			for (int j=0;j<grid[i].length;j++) {
				if (grid[i][j] == '*') {
					out += "* ";
				}else {
					out += "- ";
				}
			}
			out = out + "\n"; 
			
		}
		return out; 
	}

}



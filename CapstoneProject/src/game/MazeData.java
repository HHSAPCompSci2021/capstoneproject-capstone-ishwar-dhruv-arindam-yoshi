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
	



	private Grinch villain;
	private MazeData data;
	private ArrayList<Item> items;
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
	
	public void draw(PApplet marker) {
		marker.push(); 
		
		
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
	
	
	public void addItem(Item e)
	{
		items.add(e);
	}

}



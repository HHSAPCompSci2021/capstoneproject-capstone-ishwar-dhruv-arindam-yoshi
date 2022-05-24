package game;
import java.util.ArrayList;

/**
 * This class represents a point on the maze. 
 * @author dhruv
 */
public class gridP {

	/**
	 * hrow represents the X coordinate of the point
	 * vrow represents the Y coordinate of the point
	 * Visited Represents whether the cell has been visited
	 * n represents if the cell is a north facing cell. 
	 * s represents if the cell is a north facing cell. 
	 * e represents if the cell is a north facing cell. 
	 * w represents if the cell is a north facing cell. 
	 * num represents the count of this cel
	 * adjlist typically represents the neighbors of the cell, but can be used for other purposes. 
	 */
	public int hrow, vrow, visited, n, s, e, w;
	public int num;
	public ArrayList<gridP> adjlist = new ArrayList<gridP>();

	/**
	 * Creates an object of gridP
	 * @param x the x coordinate of gridP
	 * @param y the y coordinate of gridP
	 */
	public gridP(int x, int y) {
		this.hrow = x;
		this.vrow = y;
		n = s = e = w = 1;
		visited = 0;
	}

	
}
package game;
import java.util.ArrayList;

/**
 * This class represents a point on the maze. 
 * @author dhruv
 */
public class gridP {

	public int hrow, vrow, visited, n, s, e, w;
	public int num;
	public ArrayList<gridP> adjlist = new ArrayList<gridP>();

	public gridP(int x, int y) {
		this.hrow = x;
		this.vrow = y;
		n = s = e = w = 1;
		visited = 0;
	}

	
}
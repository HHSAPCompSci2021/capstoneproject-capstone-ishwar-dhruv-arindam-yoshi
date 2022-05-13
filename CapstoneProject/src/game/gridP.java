package game;
import java.util.ArrayList;

public class gridP {

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
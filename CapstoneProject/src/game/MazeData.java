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
	private gridP[][] myBoard ;
	private int size; 
	private ArrayList<gridP> cList;
	
	
	public MazeData() {
		myBoard = new gridP[10][10]; 
		cList = new ArrayList<gridP>();; 
		size = 10; 
	}
	
	
	public MazeData(int size) {
		myBoard = new gridP[size][size]; 
		cList = new ArrayList<gridP>();; 
		this.size = size; 
	}
	
	public void assignLocations() {
		int num = 0;
		for (int j = 0; j <= 9; j++) {
			for (int i = 0; i < 10; i++) {
				gridP newCell = new gridP(i, j);
				myBoard[i][j] = newCell;
			}
		}
		for (int j = 0; j <= 9; j++) {
			for (int i = 0; i < 10; i++) {
				myBoard[i][j].num = num;
				num++;
			}
		}

		for (int j = 0; j <= 9; j++) 
			for (int i = 0; i < 10; i++) {
				gridP cc = myBoard[i][j]; 
				if (cc.vrow != 0)  cc.adjlist.add(myBoard[cc.hrow][cc.vrow - 1]);
				
				if (cc.vrow != 9) cc.adjlist.add(myBoard[cc.hrow][cc.vrow + 1]);
				
				if (cc.hrow != 0) cc.adjlist.add(myBoard[cc.hrow - 1][cc.vrow]);
				
				if (cc.hrow != 9) cc.adjlist.add(myBoard[cc.hrow + 1][cc.vrow]);	
			}
		generateMaze();
	}
	
	public void generateMaze() {
		
	}
}



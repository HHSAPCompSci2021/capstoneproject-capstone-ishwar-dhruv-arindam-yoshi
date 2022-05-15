package game;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

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
	private gridP[][] myBoard ;
	private int size; 
	private ArrayList<gridP> cList;
	
	/**
	 * contructs the maze. 
	 */
	public MazeData() {
		myBoard = new gridP[10][10]; 
		cList = new ArrayList<gridP>();; 
		size = 10; 
	}
	
	/**
	 * Creates a MazeData object with parameter size which is the size of the maze
	 * @param size size which is the size of the maze
	 */
	public MazeData(int size) {
		myBoard = new gridP[size][size]; 
		cList = new ArrayList<gridP>();; 
		this.size = size; 
	}
	
	/**
	 * Generates a random maze, and populates the maze in the myBoard field. 
	 */
	public void generateMaze() {
		this.assignLocations(); 
		gridP currentCell = myBoard[0][0];
		currentCell.visited = 1;
		int visitedCells = 1;

		while (!(visitedCells >= 100)) {
			if (prune(currentCell) != 0) {
				gridP neighbor = null;
				Random generator = new Random();
				int random = generator.nextInt(prune(currentCell));
				neighbor = currentCell.adjlist.get(random);
				
				if (currentCell.num + size == neighbor.num) currentCell.s = neighbor.n = 0;
				else if (currentCell.num + 1 == neighbor.num) currentCell.e = neighbor.w = 0;
				else if (currentCell.num - 1 == neighbor.num) currentCell.w = neighbor.e = 0 ;
				else if (currentCell.num - size == neighbor.num) currentCell.n = neighbor.s = 0;
				
				currentCell.adjlist.remove(neighbor);
				neighbor.adjlist.remove(currentCell);
				
				cList.add(currentCell);
				currentCell = neighbor;
				currentCell.visited = 1;
				visitedCells+=1;
			}else {
				if (!cList.isEmpty()) {
					gridP newCell = cList.remove(cList.size() - 1);
					currentCell = newCell;
				}
			}
		}
	}
	
	private void assignLocations() {
		int num = 0;
		for (int j = 0; j <= size-1; j++) {
			for (int i = 0; i < size; i++) {
				gridP newCell = new gridP(i, j);
				myBoard[i][j] = newCell;
			}
		}
		for (int j = 0; j <= size-1; j++) {
			for (int i = 0; i < size; i++) {
				myBoard[i][j].num = num;
				num++;
			}
		}

		for (int j = 0; j <= size-1; j++) 
			for (int i = 0; i < size; i++) {
				gridP cc = myBoard[i][j]; 
				if (cc.vrow != 0)  cc.adjlist.add(myBoard[cc.hrow][cc.vrow - 1]);
				
				if (cc.vrow != 9) cc.adjlist.add(myBoard[cc.hrow][cc.vrow + 1]);
				
				if (cc.hrow != 0) cc.adjlist.add(myBoard[cc.hrow - 1][cc.vrow]);
				
				if (cc.hrow != 9) cc.adjlist.add(myBoard[cc.hrow + 1][cc.vrow]);	
			}
		generateMaze();
	}
	
	
	private int prune(gridP currentCell) {
		Iterator<gridP> it = currentCell.adjlist.iterator();
		while (it.hasNext()) {
			gridP cell = it.next();
			if (cell.visited == 1) it.remove();
			
		}
		int neighbors = 0;
		for (int x = 0; x < currentCell.adjlist.size(); x++) 
			if (currentCell.adjlist.get(x).visited == 0) neighbors++;
		return neighbors;
	}
	
	
	/**
	 * Draws the maze on the PApplet
	 * @param marker the marker where the PApplet is draw
	 * @param x the upper x coord of the maze
	 * @param y the upper y coord of the maze
	 * @param w the width of the maze
	 * @param h the height of the maze
	 */
	public void draw(PApplet marker, float x, float y, float w, float h) {
		
	}
	
	public String[][] toStringArr() {
		String[][] out = new String[size + size + 1][1]; 
		int counter = 0; 
		for (int i = 0; i < size; i++) {
			String s = ""; 
			for (int j = 0; j < size; j++) 
				if (myBoard[j][i].n == 1) s += "+---"; 
				else  s += "+   "; 
			
			s += "+"; 
			out[counter++][0] = s; 
			s = ""; 
			for (int j = 0; j < size; j++) 
				if (myBoard[j][i].w == 1) s += "|   "; 
				else s += "    ";
			s += "|"; 
			out[counter++][0] = s; 
			s = ""; 
		}
		String newS = ""; 
		for (int j = 0; j < size; j++) 
			newS += "+---";
		newS += "+"; 
		out[counter++][0] = newS; 
		return out; 
	}
}



package game;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import characters.Actor;
import characters.Grinch;
import characters.Officer;
import items.*;
import screen.*;
import processing.core.*;
import java.awt.Point;
import java.awt.geom.Point2D;
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
	private static final int CORNERS = 0;
	private gridP[][] myBoard ;
	private int size; 
	private ArrayList<gridP> cList;
	
	public ArrayList<Rectangle> wallsList; 
	
	/**
	 * contructs the maze. 
	 */
	public MazeData() {
		myBoard = new gridP[10][10]; 
		cList = new ArrayList<gridP>();; 
		size = 10; 
		wallsList = new ArrayList<Rectangle>(); 
	}
	
	/**
	 * Creates a MazeData object with parameter size which is the size of the maze
	 * @param size size which is the size of the maze
	 */
	public MazeData(int size) {
		myBoard = new gridP[size][size]; 
		cList = new ArrayList<gridP>();; 
		this.size = size; 
		wallsList = new ArrayList<Rectangle>(); 
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
		marker.push();
//		marker.rectMode(CORNERS); 
		
		marker.fill(0, 0, 0);
		marker.stroke(0, 0, 0);
		marker.strokeWeight(2);
		
		String[][] mazeGrid = this.toStringArr(); 
		int lengthMaze = 4 * size + 1; 
		int heightMaze = 2 * size + 1;
		double xLen = w/(lengthMaze-1); 
		double yLen = h/(heightMaze-1); 
		
		for (int i=0;i<heightMaze;i++) {
			char[] currRow = mazeGrid[i][0].toCharArray(); 
			double yCoord = y + i * yLen; 
			if (i % 2 == 0) {
				for (int j=0;j<currRow.length-1;j+=4) {
					double xCoord = x + j * xLen;
					double xCoordSec = x + (j+4) * xLen;
					if (currRow[j] == '+' && currRow[j+1] == '-') {
						marker.rect((float) xCoord, (float)yCoord-1, (float) xCoordSec- (float) xCoord, 2);
						wallsList.add(new Rectangle(xCoord, yCoord-1, xCoordSec- xCoord, 2)); 
						
//						marker.line((float)xCoord, (float)yCoord, (float)xCoordSec, (float)yCoord);
					}
//					marker.circle((float)xCoord, (float)yCoord, 5);
//					marker.circle((float)xCoordSec, (float)yCoord, 5);
					// marker.circle((float)xCoord, (float)yCoord, 5);
					// marker.circle((float)xCoordSec, (float)yCoord, 5);
				}
			}else {
				for (int j=0;j<currRow.length;j++) {
					if (currRow[j] == '|') {
						double xCoord = x + j * xLen;
						double yCoordFirst = yCoord - yLen; 
						double yCoordSecond = yCoord + yLen; 
						marker.rect((float) xCoord-1, (float)yCoordFirst,2, (float) yCoordSecond - (float) yCoordFirst);
						wallsList.add(new Rectangle(xCoord-1, yCoordFirst, 2, yCoordSecond - yCoordFirst)); 
//						marker.line((float)xCoord, (float)yCoordFirst, (float)xCoord, (float)yCoordSecond);
					}
				}
			}	
		}
		marker.pop();
	}
	
	
	/**
	 * This method converts the maze into a array of strings in order to 
	 * successfully display on console. 
	 * 
	 * @return A string array representing the maze with the characters "+" representing a corner, a "-" representing a wall
	 * and a " " representing an empty location. 
	 */
	public String[][] toStringArr() {
		String[][] out = new String[21][1]; 
		int counter = 0; 
		for (int i = 0; i < 10; i++) {
			String s = ""; 
			for (int j = 0; j < 10; j++) 
				if (myBoard[j][i].n == 1) s += "+---"; 
				else  s += "+   "; 
			
			s += "+"; 
			out[counter++][0] = s; 
			s = ""; 
			for (int j = 0; j < 10; j++) 
				if (myBoard[j][i].w == 1) s += "|   "; 
				else s += "    ";
			s += "|"; 
			out[counter++][0] = s; 
			s = ""; 
		}
		String newS = ""; 
		for (int j = 0; j < 10; j++) {
			newS += "+---"; 
		}
		newS += "+"; 
		out[counter++][0] = newS; 
		return out; 

	}

	
	/**
	 * left = 0
	 * right = 1
	 * top = 2
	 * bottom = 3 
	 */
	
	/**
	 * Position 1 of the array represents whether the actor is touching the wall from above. 
	 * Position 2 of the array represents whether the actor is touching the wall from the right.
	 * Position 3 of the array represents whether the actor is touching the wall from the bottom
	 * Position 4 of the array represents whether the actor is touching the wall from the left. 
	 */
	
	
	public static boolean[] isActorTouchingMaze(ArrayList<Rectangle> walls, Actor a) {
		ArrayList<Rectangle> wallsTouchingActor = new ArrayList<Rectangle>(); 
		ArrayList<boolean[]> direcOfWall = new ArrayList<boolean[]>();
		
		for (Rectangle w : walls) {
			boolean[] isTouching = w.isTouchingActor(a); 
			if (isTouching[0]) {
				wallsTouchingActor.add(w); 
				direcOfWall.add(isTouching); 
			}
		}

		if (wallsTouchingActor.size() == 1) {
			if (direcOfWall.get(0)[1]) {
				return new boolean[]{false, false, true, false}; 
			}else if (direcOfWall.get(0)[2]) {
				return new boolean[]{false, true, false, false}; 
			}else if (direcOfWall.get(0)[3]) {
				return new boolean[]{false, false, false, true}; 
			}else if (direcOfWall.get(0)[4]) {
				return new boolean[]{true, false, false, false}; 
			}
		}else if (wallsTouchingActor.size() == 2) {
			if (equals(wallsTouchingActor.get(0).h, wallsTouchingActor.get(1).h)) {
				if (direcOfWall.get(0)[1]) {
					return new boolean[]{false, false, true, false}; 
				}else if (direcOfWall.get(0)[3]) {
					return new boolean[]{false, false, false, true}; 
				}
			}else if (equals(wallsTouchingActor.get(0).w, wallsTouchingActor.get(1).w)) {
				if (direcOfWall.get(0)[2]) {
					return new boolean[]{false, true, false, false}; 
				}else if (direcOfWall.get(0)[4]) {
					return new boolean[]{true, false, false, false};
				}
			}
		}
		return new boolean[]{false, false, false, false};
		
	}
	
	private static boolean equals(double a, double b) {
		double epsilon = 0.000001d;
		if (Math.abs(a - b) < epsilon)
			return true; 
		return false; 
	}
}



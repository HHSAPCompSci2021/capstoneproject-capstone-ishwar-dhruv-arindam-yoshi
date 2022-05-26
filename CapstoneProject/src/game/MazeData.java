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
 * This class represents a maze, with many common methods that you could do with a maze. 
 * @author Dhruv Lohani
 *
 */
public class MazeData {
	private static final int CORNERS = 0;
	private gridP[][] myBoard ;
	/**
	 * Represents the size of the maze
	 */
	public int size; 
	private ArrayList<gridP> cList;
	/**
	 * Represents the walls list in the maze. 
	 */
	public ArrayList<Rectangle> wallsList; 
	private boolean firstTime = true; 
	
	/**
	 * Creates a MazeData object. 
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
	 * Returns the number of rows/columns in the maze.
	 * @return the number of rows/columsn in the maze
	 */
	public int getSize() {
		return size;
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
//		toIntArray(); 
		
		String[][] mazeGrid = this.toStringArr(); 
		
		int lengthMaze = 4 * size + 1; 
		int heightMaze = 2 * size + 1;
		double xLen = w/(lengthMaze-1); 
		double yLen = h/(heightMaze-1); 
		char[][] entireGrid = new char[21][41]; 
		
		for (int i=0;i<heightMaze;i++) {
			char[] currRow = mazeGrid[i][0].toCharArray(); 
			entireGrid[i] = currRow; 
			double yCoord = y + i * yLen; 
			if (i % 2 == 0) {
				double startX = x; 
				double endX = startX;
				for (int j=0;j<currRow.length-1;j+=4) { 
					double xCoord = x + j * xLen;
					double xCoordSec = x + (j+4) * xLen;
					if (currRow[j] == '+' && currRow[j+1] == '-') {
						endX = x + (j+4) * xLen; 
					}else {
						if (!equals(startX, endX)) {
							if (firstTime) {
								wallsList.add(new Rectangle(startX, yCoord-1, endX- startX, 2)); 
							}
							else {
								marker.rect((float) startX, (float)yCoord-1, (float) endX - (float) startX, 2);
							}
						}
						startX = endX = xCoordSec; 
					}
				}
				if (!equals(startX, endX)) {
					if (firstTime) {
						wallsList.add(new Rectangle(startX, yCoord-1, endX- startX, 2)); 
					}
					else {
						marker.rect((float) startX, (float)yCoord-1, (float) endX - (float) startX, 2);
					}
					startX = endX; 
				}
			}	
		}
		
		for (int i=0;i<lengthMaze;i++) {
			double startY = y; 
			double endY = startY;
			double xCoord = x + i * xLen; 
			for (int j=0;j<heightMaze-1;j+=2) {
				double yCoord = y + j * yLen;
				double yCoordSec = y + (j+2) * yLen;
				if (entireGrid[j][i] == '+' && entireGrid[j+1][i]=='|') {
					endY = y + (j+2) * yLen; 
				}else {
					if (!equals(startY, endY)) {
						
						if (firstTime) {
							wallsList.add(new Rectangle(xCoord-1, startY, 2, endY - startY));
						}
						else {
							marker.rect((float) xCoord-1, (float)startY, 2, (float) endY - (float) startY);
						}
					}
					startY = endY = yCoordSec;
				}
			}
			if (!equals(startY, endY)) {
				if (firstTime) {
					wallsList.add(new Rectangle(xCoord-1, startY, 2, endY - startY));
				}
				else {
					marker.rect((float) xCoord-1, (float)startY, 2, (float) endY - (float) startY);
				}
			}
			startY = endY;
		}
		
		
		
		firstTime = false; 
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
	 * Converts a grid pixel coordinate to a point on the grid of the maze
	 * @param x1 the grid pixel x1 coordinate
	 * @param y1 the grid pixel y1 coordinate
	 * @param x the upper left x coordinate of the maze
	 * @param y the upper left y coordinate of the maze
	 * @param w the width of the maze
	 * @param h the height of the maze
	 * @param grid the grid which represents the character by character grid of the maze
	 * @param isActor used to determine if the object is an actor
	 * @param vx the velocity on the x of the actor, if it is an actor
	 * @param vy the velocity on the y of the actor, if it is an actor
	 * @return
	 */
	public static Point convertToPoint(float x1, float y1, float x, float y, float w, float h, char[][] grid, boolean isActor, double vx, double vy) {
		try{
			 Point p = new Point(0,0);
		   	 int lengthMaze = 4 * 10 + 1;
		   	 int heightMaze = 2 * 10 + 1;
		   	 double xLen = w/(lengthMaze);
		   	 double yLen = h/(heightMaze);
		   	 
//		   	 p.x = (int) ((((x1-x) * lengthMaze)/(w)) );
//		   	 p.y = (int) ((((y1-y) * heightMaze)/(h)) );
//		   	 
		   	 for (int i=0;i<40;i++) {
		   		 double xCoord = x + i * xLen;
		   		 double xCoordSec = xCoord + xLen;
		   		 if (x1 >= xCoord && x1 <= xCoordSec) {
		   			 p.x = i;
		   			 break;
		   		 }
		   	 }
		   	 
		   	 for (int i=0;i<20;i++) {
		   		 double yCoord = y + i * yLen;
		   		 double yCoordSec = yCoord + yLen;
		   		 if (y1 >= yCoord && y1 <= yCoordSec) {
		   			 p.y = i;
		   			 break;
		   		 }
		   	 }
		   	 
		   	 if (!isActor) {
		   		 return p; 
		   	 }
		   	 
		   	 if (grid[p.y][p.x] != ' ') {
			   	if (vx == 0) p.x = p.x; 
			   	else p.x = (vx > 0) ? p.x-1 : p.x+1; 
			   	
			   	if (vy == 0) p.y = p.y; 
			   	else p.y = (vy > 0) ? p.y-1 : p.y+1; 
		   		
		   		
		   	 }
		   	 
//		   	 if (grid[p.y][p.x] != ' ') {
//		   		 if (y > 0 && grid[p.y-1][p.x] == ' ') {
//		   			 p.y =  (int) (p.y-1);
//		   		 }else if (y < 21 && grid[p.y+1][p.x] == ' ') {
//		   			 p.y = (int) (p.y+1);
//		   		 }else if (x > 0 && grid[p.y][p.x-1] == ' ') {
//		   			 p.x = (int) (p.x-1);
//		   		 }else if (x < 41 && grid[p.y][p.x+1] == ' ') {
//		   			 p.x = (int) (p.x+1);
//		   		 }
//		   		 
//		   	 }
		   	 return p;
		}catch (Exception e) {
			return new Point(0,0); 
		}
	    }

	
	//1 represents wall
	//0 represents space
	public int[][] toIntArray(){
		String[][] mazeGrid = this.toStringArr();
		int[][] intGrid = new int[12][12]; 
		int lengthMaze = 4 * size + 1; 
		int heightMaze = 2 * size + 1;
		int iCounter = 0; 
		for (int i=0;i<heightMaze;i+=2) {
			char[] currRow = mazeGrid[i][0].toCharArray(); 
			int jCounter = 0; 
			for (int j=0;j<currRow.length-1;j+=4) { 
				System.out.println(iCounter + " " + jCounter); 
				if (i%2 == 0) {
					if (currRow[j] == '+' && currRow[j+1] == '-') {
						intGrid[iCounter][jCounter] = 1; 
						jCounter++; 
					}else {
						intGrid[iCounter][jCounter] = 0;
						jCounter++; 
					}
				}else {
					if (currRow[j] == '|') {
						intGrid[iCounter][jCounter] = 1; 
						jCounter++; 
					}else {
						intGrid[iCounter][jCounter] = 0;
						jCounter++; 
					}
				}
			}
		}
		
		for (int i=0;i<11;i++) {
			for (int j=0;j<11;j++) {
				System.out.println(intGrid[i][j]);
			}
		}
		return intGrid; 
	}
	
	
	/**
	 * 
	 * @param walls which represents the walls in the maze
	 * @param a which represents actor(or specifically protaganist in the maze
	 * @return a boolean array of length 4 which is:
	 * Position 0 of the array represents whether the actor is touching the wall from left. 
	 * Position 1 of the array represents whether the actor is touching the wall from the right.
	 * Position 2 of the array represents whether the actor is touching the wall from the top
	 * Position 3 of the array represents whether the actor is touching the wall from the bottom. 
	 */
	public static boolean[] isActorTouchingMaze(ArrayList<Rectangle> walls, Actor a) {
//		System.out.println("been here"); 
//		System.out.println(walls.size()); 
		// ArrayList<Rectangle> wallsTouchingActor = new ArrayList<Rectangle>(); 
		ArrayList<boolean[]> direcOfWall = new ArrayList<boolean[]>();
		
		boolean[] answer = {false, false, false, false};		
//		System.out.println("ACTOR " + a.getX() + " " + a.getY()); 
		
		for (Rectangle w : walls) {
			boolean[] isTouching = a.isTouchingWall(w); 
			for (int i = 0; i < 4; i++)
				answer[i] |= isTouching[i];
		}
		
		return answer;
		
	}
	
	private static boolean[] getPos(Rectangle wall1, Rectangle wall2) {
		System.out.println("Been here");
		double x1 = Math.min(wall1.x1, wall1.x2); 
		double y1 = Math.min(wall1.y1, wall1.y2); 
		
		double x2 = Math.min(wall2.x1, wall2.x2); 
		double y2 = Math.min(wall2.y1, wall2.y2); 
		
		System.out.println("X" + x1 + " " + x2); 
		System.out.println("Y" + y1 + " " + y2); 

		if (x1 > x2 && y1 > y2) {
			return new boolean[]{false, true, true, false}; 
		}else if (equals(x1, x2) && y1 > y2) {
			return new boolean[] {true, false, false, true}; 
		}else if (x1 < x2 && y1 < y2) {
			return new boolean[] {false, true, false, true}; 
		}else if (equals(x1, x2) && y1 < y2) {
			return new boolean[] {true, false, true, false}; 
		}
		return new boolean[] {false, false, false, false}; 
	}
	
	private static boolean equals(double a, double b) {
		double epsilon = 0.000001d;
		if (Math.abs(a - b) < epsilon)
			return true; 
		return false; 
	}
}
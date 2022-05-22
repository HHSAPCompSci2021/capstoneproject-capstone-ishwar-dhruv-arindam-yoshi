package game;
import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import characters.*;
import items.*;
import music.AudioPlayer;
import screen.*;
import processing.core.*;
import core.*;

/**
 * Represents the environment in which the main objects of the game are housed (Officer, Grinch, Traps, Blueprints, etc.)
 * @author Ishwar S.
 *
 */
public class HauntedMaze extends ScreenObject {
	
	// TO-DO
		// should set Blueprints to collect
		// should set Traps in the beginning
	
	// has mazedata, actors, and items
	
	private PApplet marker; // used for image loading
	/**
	 * the Officer in the game
	 */
	public Officer protagonist;
	/**
	 * the Grinch in the game
	 */
	public Grinch villain;
	/**
	 * the data that represents the walls in the maze
	 */
	public MazeData settingData;
	/**
	 * the list of items that are in the haunted maze
	 */
	public ArrayList<Item> items;
	
	private AudioPlayer audio; 
	
	private static final double LIGHT_ANGLE = Math.PI*90/180;
	private static final double LIGHT_DIST = 100;
	private static final int LIGHT_RES = 4;
	
	private static final int[] SHADE_COLOR = {0, 0, 0, 255};
	private static final int[] LIGHT_COLOR = {252, 252, 38, 100};
	
	private Random rand; 
	
	public HauntedMaze(PApplet marker)
	{
		super(200, 150, 500, 500);
		this.marker = marker;
		
		protagonist = new Officer(marker, x+70, y+60);
		villain = new Grinch(marker, x+260, y+200);
		settingData = new MazeData();
		settingData.generateMaze(marker, (float)x, (float)y, (float)w, (float)h);
		items = new ArrayList<Item>(); 
		rand = new Random();
		audio = new AudioPlayer(".//assets//DeadForestMusic.wav"); 
				
		rand = new Random();
	}
	
	
	public void setup()
	{
		
		// add Blueprints randomly
		String[] temp = new String[] {"A", "B", "C"}; 
		for (int i = 0; i < 3; i++) {
			double[] randLocs = getRandLocs(); 
			addItem(new Blueprint(marker, randLocs[0], randLocs[1], temp[i]));
		}
		
	}
	
	private double[] getRandLocs() {
		ArrayList<Rectangle> walls = settingData.wallsList; 
		System.out.println("WALLSIZE + " + walls.size()); 
		while(true) {
			double randomX = x+50 + (w - x+50) * rand.nextDouble();
			double randomY = y+50 + (h - y+50) * rand.nextDouble();
			boolean works = true; 
			for (int i=0;i<walls.size();i++) {
				if (walls.get(i).contains(randomX, randomY)) {
					works = false;
				}
			}
			if (works) {
				return new double[] {randomX, randomY}; 
			}
		}
	}
	
	public void draw(PApplet marker)
	{	
		marker.push();
		
		marker.fill(255, 255, 255);
		marker.rect((float)x, (float)y, (float)w, (float)h);
//		marker.fill(0, 0, 0);
		// marker.text("Haunted Maze", (float)(x + w/2), (float)(y + h/2));
		
		settingData.draw(marker, (float)x, (float)y, (float)w, (float)h);

		villain.draw(marker);
		for (Item i : items)
			if (i instanceof Trap)
				i.draw(marker);
		drawLighting();
		
		protagonist.draw(marker);
		for (Item i : items)
			if (i instanceof Blueprint)
				i.draw(marker);
		
		for (Item i : items)
			if (i instanceof Teleporter)
				i.draw(marker);
		marker.pop();
	}
	
	/**
	 * Draws the lighting in the HauntedMaze (from the officer's flashlight).
	 * @param marker
	 */
	public void drawLighting()
	{
		//marker.push();
		
		double px = protagonist.getX() + protagonist.getW()/2;
		double py = protagonist.getY() + protagonist.getH()/2;
		
		
		double[][] pointArr = new double[LIGHT_RES+1][2];
		
		for (int i = 0; i < LIGHT_RES; i++)
		{
			double angle = protagonist.direction - LIGHT_ANGLE/2 + LIGHT_ANGLE*i/LIGHT_RES;
			double travelDist = LIGHT_DIST;
			double X1 = px + Math.cos(angle)*travelDist; double Y1 = py + Math.sin(angle)*travelDist;
			double X2 = px + Math.cos(angle + LIGHT_ANGLE/LIGHT_RES)*travelDist; double Y2 = py + Math.sin(angle + LIGHT_ANGLE/LIGHT_RES)*travelDist;
			pointArr[i][0] = X1; pointArr[i][1] = Y1;
			pointArr[i+1][0] = X2; pointArr[i+1][1] = Y2;
		}
		
		double minX = Double.MAX_VALUE; double maxX = Double.MIN_VALUE;
		double minY = Double.MAX_VALUE; double maxY = Double.MIN_VALUE;
		
		for (double[] pt : pointArr)
		{
			if (pt[0] < minX)
				minX = pt[0];
			if (pt[0] > maxX)
				maxX = pt[0];
			if (pt[1] < minY)
				minY = pt[1];
			if (pt[1] > maxY)
				maxY = pt[1];
		}
		maxX = Math.max(px, maxX); minX = Math.min(px, minX);
		maxY = Math.max(py, maxY); minY = Math.min(py, minY);
		
		double[][] boxArr = new double[LIGHT_RES+1][2];
		double[] corner = new double[2];
		
		marker.fill(SHADE_COLOR[0], SHADE_COLOR[1], SHADE_COLOR[2], SHADE_COLOR[3]);
		marker.stroke(SHADE_COLOR[0], SHADE_COLOR[1], SHADE_COLOR[2], SHADE_COLOR[3]);
		for (int i = 0; i < LIGHT_RES+1; i++)
		{
			boxArr[i] = findEndPt(px, py, pointArr[i], minX, maxX, minY, maxY);
			
			if (i > 0)
			{
				boolean ifEither = true;
				if (DoubleX.equal(boxArr[i-1][0], maxX) && DoubleX.equal(boxArr[i][1], maxY))
					{corner[0] = maxX; corner[1] = maxY;}
				else if (DoubleX.equal(boxArr[i-1][1], maxY) && DoubleX.equal(boxArr[i][0], minX))
					{corner[0] = minX; corner[1] = maxY;}
				else if (DoubleX.equal(boxArr[i-1][0], minX) && DoubleX.equal(boxArr[i][1], minY))
					{corner[0] = minX; corner[1] = minY;}
				else if (DoubleX.equal(boxArr[i-1][1], minY) && DoubleX.equal(boxArr[i][0], maxX))
					{corner[0] = maxX; corner[1] = minY;}
				else
					ifEither = false;
				if (ifEither)
				{
					marker.triangle((float)corner[0], (float)corner[1],
							(float)boxArr[i-1][0], (float)boxArr[i-1][1],
							(float)boxArr[i][0], (float)boxArr[i][1]);
				}
			}
			
		}
		
		for (int i = 0; i < LIGHT_RES; i++)
		{
			marker.fill(LIGHT_COLOR[0], LIGHT_COLOR[1], LIGHT_COLOR[2], LIGHT_COLOR[3]);
			marker.noStroke();
			marker.triangle((float)px, (float)py,
							(float)pointArr[i][0], (float)pointArr[i][1],
							(float)pointArr[i+1][0], (float)pointArr[i+1][1]);
			
			marker.fill(SHADE_COLOR[0], SHADE_COLOR[1], SHADE_COLOR[2], SHADE_COLOR[3]);
			marker.stroke(SHADE_COLOR[0], SHADE_COLOR[1], SHADE_COLOR[2], SHADE_COLOR[3]);
			marker.quad((float)boxArr[i][0], (float)boxArr[i][1],
					(float)pointArr[i][0], (float)pointArr[i][1],
					(float)pointArr[i+1][0], (float)pointArr[i+1][1],
					(float)boxArr[i+1][0], (float)boxArr[i+1][1]);
		}
		
		double leftX = 0, leftY = 0;
		double rightX = 0, rightY = 0;
		
		if (DoubleX.equal(minX, px))
		{
			if (DoubleX.equal(minY, py))
			{
				leftX = maxX;
				rightX = minX;
			}
			else if (DoubleX.equal(maxY, py))
			{
				leftX = minX;
				rightX = maxX;
			}
			else
			{
				leftX = minX;
				rightX = minX;
			}
			leftY = minY;
			rightY = maxY;
		}
		else if (DoubleX.equal(maxX, px))
		{
			if (DoubleX.equal(minY, py))
			{
				leftX = maxX;
				rightX = minX;
			}
			else if (DoubleX.equal(maxY, py))
			{
				leftX = minX;
				rightX = maxX;
			}
			else
			{
				leftX = maxX;
				rightX = maxX;
			}
			leftY = maxY;
			rightY = minY;
		}
		else
		{
			if (DoubleX.equal(minY, py))
			{
				leftX = maxX; leftY = minY;
				rightX = minX; rightY = minY;
			}
			else if (DoubleX.equal(maxY, py))
			{
				leftX = minX; leftY = maxY;
				rightX = maxX; rightY = maxY;
			}
		}
		marker.triangle((float)px, (float)py, (float)leftX, (float)leftY, (float)boxArr[0][0], (float)boxArr[0][1]);
		marker.triangle((float)px, (float)py, (float)rightX, (float)rightY, (float)boxArr[LIGHT_RES][0], (float)boxArr[LIGHT_RES][1]);
		
		double[] posX = {x, minX, maxX, x+w};
		double[] posY = {y, minY, maxY, y+h};
		
		// System.out.println(x + " " + (x+h));
		
		marker.fill(SHADE_COLOR[0], SHADE_COLOR[1], SHADE_COLOR[2], SHADE_COLOR[3]);
		marker.stroke(SHADE_COLOR[0], SHADE_COLOR[1], SHADE_COLOR[2], SHADE_COLOR[3]);
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				if (DoubleX.equal(posX[i], minX) && DoubleX.equal(posY[j], minY))
					continue;
				marker.rect((float)posX[i], (float)posY[j], (float)(posX[i+1]-posX[i]), (float)(posY[j+1]-posY[j]));
			}
		}
		
		// marker.rect((float)x, (float)y, (float)w, (float)h);
		
		// marker.pop();
	}
	
	private double[] findEndPt(double px, double py, double[] pt, double minX, double maxX, double minY, double maxY)
	{	
		double[] endPt = new double[2];
		
		double endX1 = (pt[0] - px > 0) ? maxX : minX;
		double endY1 = py + (pt[1] - py)*Math.max((minX - px)/(pt[0] - px), (maxX - px)/(pt[0] - px));
			
		double endY2 = (pt[1] - py > 0) ? maxY : minY;
		double endX2 = px + (pt[0] - px)*Math.max((minY - py)/(pt[1] - py), (maxY - py)/(pt[1] - py));
		
		if ((endY1 <= maxY+0.01) && (endY1 >= minY-0.01))
		{endPt[0] = endX1; endPt[1] = endY1;}
		else if ((endX2 <= maxX+0.01) && (endX2 >= minX-0.01))
		{endPt[0] = endX2; endPt[1] = endY2;}
		else
		{endPt[0] = 0; endPt[1] = 0;}
		
		return endPt;
	}
	
	/**
	 * Adds an item to the maze
	 * @param e the item to be added
	 */
	public void addItem(Item e)
	{
		items.add(e);
	}
	
	/**
	 * Updates the state of the HauntedMaze at each time instant.
	 * @param mouseX the x-coordinate of the mouse in the window
	 * @param mouseY the y-coordinate of the mouse in the window
	 */
	public void update(double mouseX, double mouseY)
	{
		double px = protagonist.getX() + protagonist.getW()/2;
		double py = protagonist.getY() + protagonist.getH()/2;
		
		double relX = mouseX - px; double relY = mouseY - py;
		double dist = Math.sqrt(Math.pow(relX, 2) + Math.pow(relY, 2));
		
//		if (!audio.hasMusicStarted) {
//			audio.play(); 
//			audio.hasMusicStarted = true; 
//		}
//		
		protagonist.direction = (relY > 0) ? Math.acos(relX/dist) : (2*Math.PI - Math.acos(relX/dist));
	
		protagonist.act(this);
		villain.act(this);
		
		for (Item i : items)
		{
			i.use(this);
		}
	}
	
	

}

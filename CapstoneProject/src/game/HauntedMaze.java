package game;
import java.util.ArrayList;

import characters.Grinch;
import characters.Officer;
import items.*;
import screen.*;
import processing.core.*;


/**
 * has characters, items, maze walls
 * @author Ishwar S.
 *
 */
public class HauntedMaze extends ScreenObject {
	
	// TO-DO
		// should set Blueprints to collect
		// should set Traps in the beginning
	
	// has mazedata, actors, and items
	
	public Officer protagonist;
	public Grinch villain;
	public MazeData settingData;
	public ArrayList<Item> items;
	
	public double direction; // direction with respect to the horizontal (pointing right, clockwise) 
	public static final double LIGHT_ANGLE = Math.PI*80/180;
	public static final double LIGHT_DIST = 100;
	public static final int LIGHT_RES = 4;
	
	public static final int[] SHADE_COLOR = {0, 0, 0, 230};
	public static final int[] LIGHT_COLOR = {252, 252, 38, 100};
	
	public HauntedMaze()
	{
		super(0, 0, 300, 300);
		protagonist = new Officer(null, 10, 10);
		villain = new Grinch();
		settingData = new MazeData(20);
		items = new ArrayList<Item>(); 
		
		direction = 0;
	}
	
	public void draw(PApplet marker)
	{
		x = (marker.width-w)/2.0;
		y = (marker.height-h)/2.0;
		
		
		marker.push();
		
		// marker.rect((float)x, (float)y, (float)w, (float)h);
		marker.fill(0, 0, 0);
		marker.text("Haunted Maze", (float)(x + w/2), (float)(y + h/2));
		
		settingData.draw(marker, (float)x, (float)y, (float)w, (float)h);
		
		drawLighting(marker);
		protagonist.draw(marker);
		villain.draw(marker);
		for (Item i : items)
			i.draw(marker);
		
		marker.pop();
	}
	
	public void drawLighting(PApplet marker)
	{
		marker.push();
		
		double px = protagonist.getX() + protagonist.getW()/2;
		double py = protagonist.getY() + protagonist.getH()/2;
		
		
		double[][] pointArr = new double[LIGHT_RES+1][2];
		
		for (int i = 0; i < LIGHT_RES; i++)
		{
			double angle = direction - LIGHT_ANGLE/2 + LIGHT_ANGLE*i/LIGHT_RES;
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
		marker.stroke(SHADE_COLOR[0], SHADE_COLOR[1], SHADE_COLOR[2], SHADE_COLOR[3]/3);
		for (int i = 0; i < LIGHT_RES+1; i++)
		{
			boxArr[i] = findEndPt(px, py, pointArr[i], minX, maxX, minY, maxY);
			
			if (i > 0)
			{
				boolean ifEither = true;
				if (equal(boxArr[i-1][0], maxX) && equal(boxArr[i][1], maxY))
					{corner[0] = maxX; corner[1] = maxY;}
				else if (equal(boxArr[i-1][1], maxY) && equal(boxArr[i][0], minX))
					{corner[0] = minX; corner[1] = maxY;}
				else if (equal(boxArr[i-1][0], minX) && equal(boxArr[i][1], minY))
					{corner[0] = minX; corner[1] = minY;}
				else if (equal(boxArr[i-1][1], minY) && equal(boxArr[i][0], maxX))
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
			marker.stroke(SHADE_COLOR[0], SHADE_COLOR[1], SHADE_COLOR[2], SHADE_COLOR[3]/3);
			marker.quad((float)boxArr[i][0], (float)boxArr[i][1],
					(float)pointArr[i][0], (float)pointArr[i][1],
					(float)pointArr[i+1][0], (float)pointArr[i+1][1],
					(float)boxArr[i+1][0], (float)boxArr[i+1][1]);
		}
		
		double leftX = 0, leftY = 0;
		double rightX = 0, rightY = 0;
		
		if (equal(minX, px))
		{
			if (equal(minY, py))
			{
				leftX = maxX;
				rightX = minX;
			}
			else if (equal(maxY, py))
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
		else if (equal(maxX, px))
		{
			if (equal(minY, py))
			{
				leftX = maxX;
				rightX = minX;
			}
			else if (equal(maxY, py))
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
			if (equal(minY, py))
			{
				leftX = maxX; leftY = minY;
				rightX = minX; rightY = minY;
			}
			else if (equal(maxY, py))
			{
				leftX = minX; leftY = maxY;
				rightX = maxX; rightY = maxY;
			}
		}
		marker.triangle((float)px, (float)py, (float)leftX, (float)leftY, (float)boxArr[0][0], (float)boxArr[0][1]);
		marker.triangle((float)px, (float)py, (float)rightX, (float)rightY, (float)boxArr[LIGHT_RES][0], (float)boxArr[LIGHT_RES][1]);
		
		double[] posX = {x, minX, maxX, x+w};
		double[] posY = {y, minY, maxY, y+h};
		
		marker.fill(SHADE_COLOR[0], SHADE_COLOR[1], SHADE_COLOR[2], SHADE_COLOR[3]);
		marker.stroke(SHADE_COLOR[0], SHADE_COLOR[1], SHADE_COLOR[2], SHADE_COLOR[3]/3);
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				if (equal(posX[i], minX) && equal(posY[j], minY))
					continue;
				marker.rect((float)posX[i], (float)posY[j], (float)(posX[i+1]-posX[i]), (float)(posY[j+1]-posY[j]));
			}
		}
		
		// marker.rect((float)x, (float)y, (float)w, (float)h);
		
		marker.pop();
	}
	
	public boolean equal(double a, double b)
	{
		return Math.abs(a-b) <= 0.0000001;
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
	
	public void addItem(Item e)
	{
		items.add(e);
	}
	
	public void update(double mouseX, double mouseY)
	{
		double px = protagonist.getX() + protagonist.getW()/2;
		double py = protagonist.getY() + protagonist.getH()/2;
		
		double relX = mouseX - px; double relY = mouseY - py;
		double dist = Math.sqrt(Math.pow(relX, 2) + Math.pow(relY, 2));
		
		direction = (relY > 0) ? Math.acos(relX/dist) : (2*Math.PI - Math.acos(relX/dist));
		
		protagonist.act();
		villain.act();
	}

}

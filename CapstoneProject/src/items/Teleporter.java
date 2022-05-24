package items;

import screen.ScreenObject;
import characters.Officer;
import processing.core.*;
import game.HauntedMaze;

import java.util.*;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import core.DrawingSurface;
import processing.core.*;
import characters.*;
import items.*;
import game.*;
import screen.*;
/**
 * Represents the blueprint the officer needs to collect
 * @author akulkarni174
 *
 */
public class Teleporter extends Item {

	//deducts 20 if used
	private final int deductor = 20;
    private PImage teleporter;

    // has identifier

    /**
     * 
     * @param x is an x coordinate for blueprint
     * @param y is a y coordinate for blueprint
     * @param identify is the identification used to access img
     * @param img is the image used to show blueprint in the maze
     */
    public Teleporter(PApplet marker, double x, double y)
    {
        super(x, y, 20, 25);
        teleporter = marker.loadImage("assets/teleporter.png");

    }

    
    /**
     * Uses the teleporter in the maze
     * @param maze which is where the teleporter is used upon. 
     */
    public void use(HauntedMaze maze)
    {
    	try {
    		Blueprint picked = null;
        	for (int i = 0; i < maze.items.size(); i++)
        		if (maze.items.get(i) instanceof Blueprint) {
        			picked = (Blueprint)maze.items.get(i);
        			break;
        		}
        	
    		maze.protagonist.setPos(picked.getX() - maze.protagonist.getW()/2,
    								picked.getY() - maze.protagonist.getH()/2);
    		maze.protagonist.changeHealth(-10);
    	} catch (Exception e) {
    		return;
    	}
    	
		
    }


    /**
     * Draws information about the blueprint in the infobar
     * @param marker is the object that draws stuff
     * @param x is the x coordinate of where it is drawn
     * @param y is the y coordinate of where it is drawn
     */
    public void drawInfo(PApplet marker, double x, double y)
    {
    	marker.push();

        marker.fill(0);
        marker.image(teleporter, (float)x, (float)y, (float)w, (float)h);
        
        marker.pop();
    }

    /**
     * Draws a blueprint pin in the info bar
     * @param marker is what you draw on
     */
    public void draw(PApplet marker) {
        marker.push();

        marker.fill(0);
        marker.image(teleporter, (float)x, (float)y, (float)w, (float)h);

        
        
        marker.pop();
    }


}

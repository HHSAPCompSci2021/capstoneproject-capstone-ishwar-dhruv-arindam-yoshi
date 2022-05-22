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

	private final int deductor = 20;
    protected PImage teleporter;

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
     * 
     */
    public void use(HauntedMaze maze)
    {
        double dist = Math.sqrt(Math.pow(x-maze.protagonist.getX(), 2) + Math.pow(y-maze.protagonist.getY(), 2));
		if (dist < 20) {
			maze.protagonist.setPos(maze.items.get(0).getX(), maze.items.get(0).getY());
			maze.protagonist.changeHealth(-10);
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

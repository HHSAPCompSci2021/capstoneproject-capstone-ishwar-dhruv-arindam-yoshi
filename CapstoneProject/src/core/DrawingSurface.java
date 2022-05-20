package core;
import java.awt.Point;
import java.util.ArrayList;

import items.Blueprint;
import processing.core.PApplet;
import screen.*;


/**
 * Represents the drawing surface for the game
 * @author Ishwar S.
 *
 */
public class DrawingSurface extends PApplet {
	
	private Screen activeScreen;
	
	/**
	 * Represents an arrayList that contains all screens used in the project
	 */
	public ArrayList<Screen> screens;
	
	/**
	 * Ratios of x and y lengths in our screen
	 */
	public float ratioX, ratioY;
	/**
	 * Represents an int for a certain screen
	 */
	public static final int GAME = 0, OPTION = 1, END = 2;
	
	private ArrayList<Integer> keys;
	
	private static int PREV_MILLIS = 0;
	
	
	public static double DT = 0;
	
	/**
	 * Initializes the drawing surface for the game
	 */
	public DrawingSurface()
	{
		screens = new ArrayList<Screen>();
		
		GameScreen screen1 = new GameScreen(this);
		screens.add(screen1);
		
		OptionScreen screen2 = new OptionScreen(this);
		screens.add(screen2);
		
		EndScreen screen3 = new EndScreen(this);
		screens.add(screen3);
		
		activeScreen = screens.get(1);
		
		keys = new ArrayList<Integer>();
	}
	
	/**
	 * Sets up each screen
	 */
	public void setup()
	{
		for (Screen s : screens)
			s.setup();
	}
	
	/**
	 * Sets a specific active screen
	 * @param i is the index for screen intended to change to 
	 * @post a change in screen might occur
	 */
	public void switchScreen(int i)
	{
		
		Screen nextScreen = screens.get(i);
		if (nextScreen instanceof GameScreen)
		{
			((GameScreen)nextScreen).resume();
		}
		else
			if (activeScreen instanceof GameScreen)
				((GameScreen)activeScreen).pause();
		activeScreen = nextScreen;
		
	}
	
	/**
	 * 
	 * draws the active screen
	 */
	public void draw()
	{
		DT = (this.millis() - PREV_MILLIS)/1000.0;
		PREV_MILLIS = this.millis();
		
		
		
		ratioX = (float)width/activeScreen.DRAWING_WIDTH;
		ratioY = (float)height/activeScreen.DRAWING_HEIGHT;

		push();
		
		scale(ratioX, ratioY);
		activeScreen.draw();
		
		pop();
	}
	
	// taken from GamePhysicsDemo
	/**
	 * takes into account keys pressed, prevents processing from closing on escape key
	 */
	public void keyPressed() {
		keys.add(keyCode);
		if (key == ESC)  // This prevents a processing program from closing on escape key
			key = 0;
	}

	// taken from GamePhysicsDemo
	/**
	 * takes into account keys released
	 */
	public void keyReleased() {
		while(keys.contains(keyCode))
			keys.remove(new Integer(keyCode));
	}

	// taken from GamePhysicsDemo
	/**
	 * Checks if a key is pressed
	 * @param code is a certain code for a key
	 * @return whether the keys contains code
	 */
	public boolean isPressed(Integer code) {
		return keys.contains(code);
	}
	

	/**
	 * Presses the mouse
	 */
	public void mousePressed() {
		activeScreen.mousePressed();
	}
	
	/**
	 * Moves the mouse
	 */
	public void mouseMoved() {
		activeScreen.mouseMoved();
	}
	
	/**
	 * Drags the mouse
	 */
	public void mouseDragged() {
		activeScreen.mouseDragged();
	}
	
	/**
	 * Releases the mouse
	 */
	public void mouseReleased() {
		activeScreen.mouseReleased();
	}
	
	/**
	 * Acts on which key is typed
	 */
	public void keyTyped()
	{
		activeScreen.keyTyped();
	}

	/**
	 * Converts assumed coordinates to actual coordinates
	 * @param assumed is our initial point
	 * @return point adjusted for resizing and scaling
	 */
	public Point assumedCoordinatesToActual(Point assumed) {
		return new Point((int)(assumed.getX()*ratioX), (int)(assumed.getY()*ratioY));
	}

	/**
	 * Converts actual coordinates to assumed coordinates
	 * @param actual is our point we are at
	 * @return point adjusted through the resizing
	 */
	public Point actualCoordinatesToAssumed(Point actual) {
		return new Point((int)(actual.getX()/ratioX) , (int)(actual.getY()/ratioY));
	}
	

}

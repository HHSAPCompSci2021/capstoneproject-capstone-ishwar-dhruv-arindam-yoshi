package screen;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.*;
import core.DrawingSurface;
import processing.core.*;
import screen.Screen;

import java.awt.event.KeyEvent;


import characters.*;
import items.*;
import game.*;


/**
 * Represents the option screen class of the game 
 * @author akulkarni174
 *
 */
public class OptionScreen extends Screen {

	
	private PImage optionScreen;
	private PImage starter;
	private DrawingSurface surface;
	private PImage grinchFringe;

	public static final String OPTION_SCREEN_PATH = "assets/optionScreen.png";
	public static final String STARTER_PATH = "assets/starter.png";
	public static final String TITLE_PATH = "assets/grinchFringe.png";
	
	private Rectangle button;

	/**
	 * Represents the option screen for the game
	 * @param surface is the surface which we draw on
	 */
	public OptionScreen(DrawingSurface surface) {
		super(800,600);
		this.surface = surface;
		button = new Rectangle();
	}
	
	public void setup()
	{
		optionScreen = surface.loadImage(OptionScreen.OPTION_SCREEN_PATH);
		starter = surface.loadImage(OptionScreen.STARTER_PATH);
		grinchFringe = surface.loadImage(OptionScreen.TITLE_PATH);

		button = new Rectangle(500 - 487/4, 300, 487/2, 192/2);
	}

	/**
	 * Draws the option screen 
	 */
	public void draw() {

		surface.background(255,255,255);
		surface.image(optionScreen, 0, 0, 1000, 800);
		//surface.background(img);
		surface.fill(100, 200, 240);
		surface.image(grinchFringe, 500 - 350/4, 100, 350/2, 140);

		surface.textSize(40);
		surface.fill(137, 207, 240);
		surface.fill(0);
		surface.textSize(15);
		surface.image(starter, 500 - 487/4, 300, 487/2, 192/2);

		
	}

	/**
	 * Checks whether the mouse presses in the button, and switches screen accordingly
	 */
	public void mousePressed() {
		Point p = (new Point(surface.mouseX,surface.mouseY));
		if (button.contains(p)) {
			surface.switchScreen(0);
			surface.setup();
		}
	}
	

}


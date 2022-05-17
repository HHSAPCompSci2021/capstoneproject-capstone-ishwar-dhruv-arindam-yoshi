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
	public static final String optionScreen_PATH = "optionScreen.png";
	public static final String starter_PATH = "starter.png";


	

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

	/**
	 * Draws the option screen 
	 */
	public void draw() {

		surface.background(255,255,255);
		surface.image(optionScreen, 0, 0, 1000, 800);
		//surface.background(img);
		surface.fill(100, 200, 240);
		String str1 = "Option Screen! Welcome!";
		surface.textSize(40);
		surface.text(str1, 400 - surface.textWidth(str1)/2, 100);
		surface.fill(137, 207, 240);
		surface.fill(0);
		surface.textSize(15);
		surface.image(starter, 500 - 487/4, 200, 487/2, 192/2);

		
	}


	public void setup() {
		optionScreen = surface.loadImage(OptionScreen.optionScreen_PATH);
		//optionScreen.resize(surface.width, surface.height);
		starter = surface.loadImage(OptionScreen.starter_PATH);
		button = new Rectangle(500 - 487/4, 200, 487/2, 192/2);
	}

	/**
	 * Checks whether the mouse presses in the button, and switches screen accordingly
	 */
	public void mousePressed() {
		Point p = (new Point(surface.mouseX,surface.mouseY));
		if (button.contains(p))
			surface.switchScreen(0);
	}
	

}


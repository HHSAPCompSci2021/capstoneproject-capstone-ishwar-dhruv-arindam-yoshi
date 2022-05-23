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
public class InstructionsScreen extends Screen {

	
	private PImage instructions;
	

	
	/**
	 * Path to the option screen image
	 */
	public static final String INSTRUCTIONS_PATH = "assets/Instructions.png";
	
	
	private Rectangle button;

	/**
	 * Represents the option screen for the game
	 * @param surface is the surface which we draw on
	 */
	public InstructionsScreen(DrawingSurface surface) {
		super();
		this.surface = surface;
		button = new Rectangle();
	}
	
	/**
	 * Sets up images and a button for the option screen class
	 */
	public void setup()
	{
		instructions = surface.loadImage(InstructionsScreen.INSTRUCTIONS_PATH);
		

		button = new Rectangle(500 - 487/4, 300, 487/2, 192/2);
	}

	/**
	 * Draws the option screen 
	 */
	public void draw() {

		surface.background(255,255,255);
		//surface.background(img);
		surface.fill(100, 200, 240);
		

		surface.textSize(40);
		surface.text("Instructions: ", 100, 100);
		surface.fill(137, 207, 240);
		surface.fill(0);
		surface.textSize(15);
		surface.image(instructions, 500 - (float)487/4, 300, 487/2, 192/2);

		
	}

	/**
	 * Checks whether the mouse presses in the button, and switches screen accordingly
	 */
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (button.contains(p)) {
			surface.switchScreen(0);
			surface.setup();
		}
	}
	

}


package screen;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.*;
import core.DrawingSurface;
import processing.core.*;
import screen.Screen;
import java.awt.*;    
import java.applet.*;    
import java.awt.event.KeyEvent;


import characters.*;
import items.*;
import game.*;


/**
 * Represents the instructions screen class of the game 
 * @author akulkarni174
 *
 */
public class InstructionsScreen extends Screen {

	
	private PImage instructions;
	private PImage background;
	

	
	/**
	 * Path to the option screen image
	 */
	public static final String INSTRUCTIONS_PATH = "assets/Instructions.png";
	private static final String BACKGROUND_PATH = "assets/background.png";
	
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
	 * Sets up images and a button for the instructions screen class
	 */
	public void setup()
	{
		instructions = surface.loadImage(InstructionsScreen.INSTRUCTIONS_PATH);
		background = surface.loadImage(InstructionsScreen.BACKGROUND_PATH);
		button = new Rectangle(800, 100, 350/2, 192/2);
	}

	/**
	 * Draws the instruction screen, and the instructions of the game
	 */
	public void draw() {

		surface.background(255,255,255);
		surface.image(background, 0, 0, 1000, 800);
		surface.fill(100, 200, 240);
		

		Font f3 = new Font("Elephant",Font.ITALIC,28);

		surface.image(instructions, button.x, button.y, button.width, button.height);

		
	}

	/**
	 * Checks whether the mouse presses in the button, and switches screen accordingly
	 */
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (button.contains(p)) {
			surface.switchScreen(1);
			surface.setup();
		}
	}
	

}


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
	 * Sets up images and a button for the instructions screen class
	 */
	public void setup()
	{
		instructions = surface.loadImage(InstructionsScreen.INSTRUCTIONS_PATH);
		

		button = new Rectangle(500 - 487/4, 300, 350/2, 192/2);
	}

	/**
	 * Draws the instruction screen, and the instructions of the game
	 */
	public void draw() {

		surface.background(255,255,255);
		//surface.background(img);
		surface.fill(100, 200, 240);
		

		Font f3 = new Font("Elephant",Font.ITALIC,28);

		surface.textSize(40);
		surface.text("Instructions: ", 100, 100);
		surface.fill(137, 207, 240);
		surface.fill(0);
		surface.textSize(15);
		surface.text("In the game, there is a maze. You're an officer and must collect all the blueprints in the dark maze.\n Your flashlight is the only thing that can help you see. The grinch in the maze hates visitors, and tries to attack. \n The closer he gets, the more health you lose. If he touches you, you're dead.\n Get all blueprints and exit maze to win! \n Use WDAS keys or up/right/left/down keys to move. Mouse lets you adjust flashlight. \n Click E to collect a blueprint. Click T to teleport.", 100, 150);

		surface.image(instructions, 500 - (float)487/4, 300, 350/2, 192/2);

		
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


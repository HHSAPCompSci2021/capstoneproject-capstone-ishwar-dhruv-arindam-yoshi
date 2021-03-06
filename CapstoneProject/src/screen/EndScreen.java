package screen;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.*;
import core.DrawingSurface;
import processing.core.*;
import screen.Screen;
import screen.GameScreen;
import java.awt.event.KeyEvent;


import characters.*;
import items.*;
import game.*;


/**
 * Represents the winning or losing result screen class of the game 
 * @author akulkarni174
 *
 */
public class EndScreen extends Screen {

	
	private PImage winning;
	
	
	private static final String winningScreen_PATH = "assets/winningScreen.jpg";
	private PImage losing;
	
	/**
	 * Path to the losingScreen image
	 */
	public static final String losingScreen_PATH = "assets/losingScreen.jpg";
	private PImage playAgain;
	
	/**
	 * Path to the playAgain image
	 */
	public static final String playAgain_PATH = "assets/playAgain.png";
	private DrawingSurface surface;
	
	/**
	 * boolean on whether the game has been won or lost
	 */
	public static boolean winLose;
	
	private Rectangle button;

	/**
	 * Represents the option screen for the game
	 * @param surface is the surface which we draw on
	 */
	public EndScreen(DrawingSurface surface) {
		super();
		this.surface = surface;
		button = new Rectangle();
	}
	
	/**
	 * Sets up all images and the button for this class
	 */
	public void setup()
	{
		winning = surface.loadImage(EndScreen.winningScreen_PATH);
		losing = surface.loadImage(EndScreen.losingScreen_PATH);
		playAgain = surface.loadImage(EndScreen.playAgain_PATH);

		button = new Rectangle(200, 200, 250, 60);
	}

	/**
	 * Draws the end screen 
	 */
	public void draw() {

		if (winLose) {
			surface.push();
			surface.fill(0, 0, 0);
			surface.image(winning, 0, 0, 1000, 800);
			surface.image(playAgain, 200, 200, 250, 60);

			surface.pop();
		}
		
		else {
			surface.push();
			surface.fill(0, 0, 0);
			surface.image(losing, 0, 0, 1000, 800);
			surface.image(playAgain, 200, 200, 250, 60);
		
			surface.pop();
		
		return;

		}
		
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


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
 * Represents the option screen class of the game 
 * @author akulkarni174
 *
 */
public class EndScreen extends Screen {

	
	private PImage winning;
	public static final String winningScreen_PATH = "assets/winningScreen.jpg";
	private PImage losing;
	public static final String losingScreen_PATH = "assets/losingScreen.jpg";
	private PImage playAgain;
	public static final String playAgain_PATH = "assets/playAgain.png";
	private DrawingSurface surface;
	public static boolean winLose;
	
	private Rectangle button;

	/**
	 * Represents the option screen for the game
	 * @param surface is the surface which we draw on
	 */
	public EndScreen(DrawingSurface surface) {
		super(800,600);
		this.surface = surface;
		button = new Rectangle();
	}
	
	public void setup()
	{
		winning = surface.loadImage(EndScreen.winningScreen_PATH);
		losing = surface.loadImage(EndScreen.losingScreen_PATH);
		playAgain = surface.loadImage(EndScreen.playAgain_PATH);

		button = new Rectangle(200, 200, 250, 60);
	}

	/**
	 * Draws the option screen 
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
		Point p = (new Point(surface.mouseX,surface.mouseY));
		if (button.contains(p)) {
			
			surface.switchScreen(1);
			surface.setup();
		}
			
	}
	

}


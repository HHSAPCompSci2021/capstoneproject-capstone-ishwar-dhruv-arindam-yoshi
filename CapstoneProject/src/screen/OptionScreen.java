package screen;
import java.awt.Point;
import java.awt.Rectangle;

import core.DrawingSurface;
import processing.core.*;
import screen.Screen;


/**
 * Represents the option screen class of the game 
 * @author akulkarni174
 *
 */
public class OptionScreen extends Screen {

	private DrawingSurface surface;
	
	private Rectangle button;

	/**
	 * Represents the option screen for the game
	 * @param surface is the surface which we draw on
	 */
	public OptionScreen(DrawingSurface surface) {
		super(800,600);
		this.surface = surface;

		button = new Rectangle(800/2-100,600/2-50,200,100);
	}

	/**
	 * Draws the option screen 
	 */
	public void draw() {

		surface.background(255,255,255);
		surface.fill(100, 200, 240);
		String str1 = "Option Screen! Welcome!";
		surface.textSize(40);
		surface.text(str1, 400 - surface.textWidth(str1)/2, 100);
		surface.fill(137, 207, 240);
		surface.rect(button.x, button.y, button.width, button.height, 10, 10, 10, 10);
		surface.fill(0);
		surface.textSize(15);

		String str = "Play game!";
		float w = surface.textWidth(str);
		surface.text(str, button.x+button.width/2-w/2, button.y+button.height/2);
		
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


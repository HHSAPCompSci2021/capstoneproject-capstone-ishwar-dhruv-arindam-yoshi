package screen;
import java.util.*;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import core.DrawingSurface;
import processing.core.*;
import screens.ScreenSwitcher;
import characters.*;
import items.*;
import game.*;


/**
 * This class represents the screen in which the user is interacting with the program to play the game.
 * @author Ishwar S.
 *
 */
public class GameScreen extends Screen {
	
	// has a haunted maze object and an info bar object
	
	private HauntedMaze gameSetting;
	private InfoBar bar;

	/**
	 * Constructs a new GameScreen object using a DrawingSurface object
	 * @param surface the DrawingSurface object used to display the game and receive user input
	 */
	public GameScreen(DrawingSurface surface) {
		super(800, 600);
		this.surface = surface;
		System.out.println(surface.width + " " + surface.height);
		gameSetting = new HauntedMaze();
		bar = new InfoBar();
	}
	
	public void draw()
	{
		surface.background(255, 255, 255);
		
		gameSetting.draw(surface);
		bar.draw(surface);
		
		if (surface.isPressed(KeyEvent.VK_ESCAPE)) {
			surface.switchScreen(surface.OPTION);
			return;
		}
		if (surface.isPressed(KeyEvent.VK_LEFT))
			protagonist.setVx(-1)
		if (surface.isPressed(KeyEvent.VK_RIGHT))
			mario.walk(1);
		if (surface.isPressed(KeyEvent.VK_UP))
			mario.jump();
	}
}


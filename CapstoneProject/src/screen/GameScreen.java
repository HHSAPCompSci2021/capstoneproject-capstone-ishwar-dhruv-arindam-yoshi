package screen;
import java.util.*;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import core.DrawingSurface;
import processing.core.*;
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
		// System.out.println(surface.width + " " + surface.height);
		gameSetting = new HauntedMaze(surface);
		bar = new InfoBar(gameSetting.protagonist);
	}
	
	public void setup()
	{
		gameSetting.protagonist.setImage(surface.loadImage(Officer.IMG_PATH));
		gameSetting.addItem(new Blueprint(gameSetting.getX()+10, gameSetting.getY()+10, "A", surface.loadImage(Blueprint.pin_PATH)));
		gameSetting.addItem(new Blueprint(gameSetting.getX()+50, gameSetting.getY()+50, "B", surface.loadImage(Blueprint.pin_PATH)));
		gameSetting.addItem(new Blueprint(gameSetting.getX()+90, gameSetting.getY()+130, "C", surface.loadImage(Blueprint.pin_PATH)));
		gameSetting.setup();
	}
	
	public void draw()
	{
		surface.background(255, 255, 255);
		
		if (!gameSetting.protagonist.isAlive())
		{
			surface.push();
			surface.fill(0, 0, 0);
			surface.text("Game over :/", 300, 200);
			surface.pop();
			return;
		}
		if (gameSetting.protagonist.isSuccessful(gameSetting))
		{
			surface.push();
			surface.fill(0, 0, 0);
			surface.text("You have won :)", 300, 200);
			surface.pop();
			return;
		}
		gameSetting.update(surface.mouseX, surface.mouseY);
		
		gameSetting.draw(surface);
		bar.draw(surface);
		
		if (surface.isPressed(KeyEvent.VK_ESCAPE)) {
			surface.switchScreen(surface.OPTION);
			return;
		}
		
		if (surface.isPressed(KeyEvent.VK_A) && surface.isPressed(KeyEvent.VK_D))
			gameSetting.protagonist.setVx(0);
		else if (surface.isPressed(KeyEvent.VK_A))
			gameSetting.protagonist.setVx(-Officer.AXIS_V);
		else if (surface.isPressed(KeyEvent.VK_D))
			gameSetting.protagonist.setVx(Officer.AXIS_V);
		else
			gameSetting.protagonist.setVx(0);
			
		if (surface.isPressed(KeyEvent.VK_W) && surface.isPressed(KeyEvent.VK_S))
			gameSetting.protagonist.setVy(0);
		else if (surface.isPressed(KeyEvent.VK_W))
			gameSetting.protagonist.setVy(-Officer.AXIS_V);
		else if (surface.isPressed(KeyEvent.VK_S))
			gameSetting.protagonist.setVy(Officer.AXIS_V);
		else
			gameSetting.protagonist.setVy(0);
		
		
		if (gameSetting.protagonist.nearBlueprint(gameSetting) != null)
		{
			surface.push();
			surface.fill(0, 0, 0);
			surface.text("Press E to pick up the blueprint", 300, 100);
			surface.pop();
			if (surface.isPressed(KeyEvent.VK_E))
			{
				System.out.println("here");
				gameSetting.protagonist.takeBlueprint(gameSetting);
			}
		}	
	}
}


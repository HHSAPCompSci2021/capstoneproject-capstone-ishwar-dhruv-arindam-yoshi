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
	private PImage winning;
	public static final String winningScreen_PATH = "winningScreen.jpg";
	private PImage losing;
	public static final String losingScreen_PATH = "losingScreen.jpg";

	private InfoBar bar;
	public static int TIME_CAP = 60*1000;
	private int[] lastResumeTime;
	// lastResumeTime[0] is time last resumed since application opened
	// lastResumeTime[1] is the time remaining in the game
	private boolean isPaused;
	private int timer;

	/**
	 * Constructs a new GameScreen object using a DrawingSurface object
	 * @param surface the DrawingSurface object used to display the game and receive user input
	 */
	public GameScreen(DrawingSurface surface) {
		super(800, 600);
		this.surface = surface;
		// System.out.println(surface.width + " " + surface.height);
		gameSetting = new HauntedMaze();
		bar = new InfoBar(gameSetting.protagonist);
		
		isPaused = true;
		timer = TIME_CAP;
		lastResumeTime = new int[] {0, TIME_CAP};
	}
	
	public void setup()
	{
		winning = surface.loadImage(GameScreen.winningScreen_PATH);
		losing = surface.loadImage(GameScreen.losingScreen_PATH);

		gameSetting.protagonist.setImage(surface.loadImage(Officer.IMG_PATH));
		gameSetting.addItem(new Blueprint(gameSetting.getX()+10, gameSetting.getY()+10, "A", surface.loadImage(Blueprint.pin_PATH)));
		gameSetting.addItem(new Blueprint(gameSetting.getX()+50, gameSetting.getY()+50, "B", surface.loadImage(Blueprint.pin_PATH)));
		gameSetting.addItem(new Blueprint(gameSetting.getX()+90, gameSetting.getY()+130, "C", surface.loadImage(Blueprint.pin_PATH)));
		gameSetting.setup();
	}
	
	public void pause() {
		isPaused = true;
		lastResumeTime[1] = timer;
	}
	
	public void resume() {
		isPaused = false;
		lastResumeTime[0] = surface.millis();
	}
	
	public void draw()
	{
		if (surface.isPressed(KeyEvent.VK_ESCAPE)) {
			surface.switchScreen(surface.OPTION);
			return;
		}
		surface.background(255, 255, 255);
		
		// ending screens - will be replaced with Victory / Loss screen
		if (gameSetting.protagonist.isSuccessful(gameSetting))
		{
			surface.push();
			surface.fill(0, 0, 0);
			surface.image(winning, 0, 0, 1000, 800);

			surface.pop();
			return;
		}
		if ((timer == 0) || !gameSetting.protagonist.isAlive())
		{
			surface.push();
			surface.fill(0, 0, 0);
			surface.image(losing, 0, 0, 1000, 800);

			surface.pop();
			
			return;
		}
		
		gameSetting.draw(surface);
		bar.draw(surface);
		
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
		
		drawTimer();
		
		
		if (!isPaused)
		{
			moveOfficer();
			gameSetting.update(surface.mouseX, surface.mouseY);
		}
	}
	
	private void moveOfficer() {
		if (surface.isPressed(KeyEvent.VK_A) && surface.isPressed(KeyEvent.VK_D))
			gameSetting.protagonist.setVx(0);
		else if (surface.isPressed(KeyEvent.VK_A))
			gameSetting.protagonist.setVx(-Officer.AXIS_V);
		else if (surface.isPressed(KeyEvent.VK_D))
			gameSetting.protagonist.setVx(Officer.AXIS_V);
		else if (surface.isPressed(KeyEvent.VK_LEFT) && surface.isPressed(KeyEvent.VK_RIGHT))
			gameSetting.protagonist.setVx(0);
		else if (surface.isPressed(KeyEvent.VK_LEFT))
			gameSetting.protagonist.setVx(-Officer.AXIS_V);
		else if (surface.isPressed(KeyEvent.VK_RIGHT))
			gameSetting.protagonist.setVx(Officer.AXIS_V);
		else
			gameSetting.protagonist.setVx(0);

		

		if (surface.isPressed(KeyEvent.VK_W) && surface.isPressed(KeyEvent.VK_S))
			gameSetting.protagonist.setVy(0);
		else if (surface.isPressed(KeyEvent.VK_W))
			gameSetting.protagonist.setVy(-Officer.AXIS_V);
		else if (surface.isPressed(KeyEvent.VK_S))
			gameSetting.protagonist.setVy(Officer.AXIS_V);
		else if (surface.isPressed(KeyEvent.VK_UP) && surface.isPressed(KeyEvent.VK_DOWN))
			gameSetting.protagonist.setVy(0);
		else if (surface.isPressed(KeyEvent.VK_UP))
			gameSetting.protagonist.setVy(-Officer.AXIS_V);
		else if (surface.isPressed(KeyEvent.VK_DOWN))
			gameSetting.protagonist.setVy(Officer.AXIS_V);
		else
			gameSetting.protagonist.setVy(0);
		

	}
	
	private void drawTimer()
	{
		int intTime = timer / 1000;
		int minutes = intTime / 60;
		int seconds = intTime % 60;
		
		surface.push();
		
		surface.stroke(0); surface.noFill();
		surface.rect(	20, surface.height - 100,
						100, 70);
		
		surface.fill(0);
		
		String label = "Timer: " + minutes + ":" + seconds;
		surface.text(label, 20, surface.height - 100);
		
		surface.pop();
		
		timer = Math.max(0, lastResumeTime[0] + lastResumeTime[1] - surface.millis());
		// System.out.println(timer);
	}
	
	
}


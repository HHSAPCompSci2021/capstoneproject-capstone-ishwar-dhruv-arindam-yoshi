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
import screen.*;


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
	 * the time that the user has to play the game (in milliseconds).
	 */
	public static int TIME_CAP = 120*1000;
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
		super();
		this.surface = surface;
		
		gameSetting = null;
		bar = null;
		
		isPaused = true;
		timer = TIME_CAP;
		lastResumeTime = new int[] {0, TIME_CAP};
	}
	
	public void addMaze(MazeData m) {
		gameSetting.settingData = m;
		m.draw(surface, (float)gameSetting.x, (float)gameSetting.y, (float)gameSetting.w, (float)gameSetting.h);
	}
	
	public void setup()
	{
		gameSetting = new HauntedMaze(surface);
		bar = new InfoBar(surface, gameSetting.protagonist);
		gameSetting.setup();
		System.out.println(gameSetting == null);
		timer = TIME_CAP;
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
		int[] bc = bar.getBackground();
		surface.background(bc[0], bc[1], bc[2]);
		
		// ending screens - will be replaced with Victory / Loss screen
		if (gameSetting.protagonist.isSuccessful(gameSetting))
		{
			setup();
			surface.switchScreen(2);
			EndScreen.winLose = true;
			return;
		}
		if ((timer == 0) || !gameSetting.protagonist.isAlive())
		{
			setup();
			surface.switchScreen(2);
			EndScreen.winLose = false;
			return;
		}
		
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
		
		if (!isPaused)
		{
			moveOfficer();
			gameSetting.update(surface.mouseX, surface.mouseY);
			updateTimer();
		}
		
		drawTimer();
		gameSetting.draw(surface);
		bar.draw(surface);
		
	}
	
	private void moveOfficer() {
		if (surface.isPressed(KeyEvent.VK_SPACE))
			gameSetting.protagonist.accelerate();
		else
			gameSetting.protagonist.stopAccelerate();
		
		int codeX = 0, codeY = 0;
		
		if (surface.isPressed(KeyEvent.VK_A) || surface.isPressed(KeyEvent.VK_LEFT))
			codeX--;
		if (surface.isPressed(KeyEvent.VK_D) || surface.isPressed(KeyEvent.VK_RIGHT))
			codeX++;
		
		if (surface.isPressed(KeyEvent.VK_W) || surface.isPressed(KeyEvent.VK_UP))
			codeY--;
		if (surface.isPressed(KeyEvent.VK_S) || surface.isPressed(KeyEvent.VK_DOWN))
			codeY++;
		if (surface.isPressed(KeyEvent.VK_P)) {
			gameSetting.protagonist.usePathFinder(gameSetting);
		}
		
		gameSetting.protagonist.adjustV(codeX, codeY);
	}
	
	private void drawTimer()
	{	
		int intTime = (timer / 1000) + 1;
		int minutes = intTime / 60;
		int seconds = intTime % 60;
	
		surface.push();
		surface.stroke(0);
		
		surface.fill(0);
		if (isPaused)
			surface.text("Paused", 20, (float)(surface.height-100-10));
		
		surface.noFill();
		surface.rect(	20, surface.height - 100,
						80, 60);
		
		surface.fill(0);
		
		String minutesStr = (minutes / 10 == 0) ? ("0"+minutes) : (""+minutes);
		String secondsStr = (seconds / 10 == 0) ? ("0"+seconds) : (""+seconds);
		
		String label = "Time left\n" + minutesStr + ":" + secondsStr;
		surface.text(label, 25, surface.height - 80);
		
		surface.pop();
		
		// System.out.println(timer);
	}
	
	public void updateTimer()
	{
		timer = Math.max(0, lastResumeTime[0] + lastResumeTime[1] - surface.millis());
	}
	
	@Override
	public void keyPressed() {
		// System.out.println("been here for key"); 
		
	}
	
	
	
	@Override
	public void keyReleased()
	{
		// System.out.println("Here");
		if (surface.keyCode == KeyEvent.VK_ESCAPE)
			if (!isPaused)
				pause();
			else
				resume();
		// System.out.println(isPaused);
		
		if (!isPaused)
			if (surface.keyCode == KeyEvent.VK_T) {
				gameSetting.protagonist.useTeleporter(gameSetting);
			}
	}
}


package core;
import java.awt.Point;
import java.util.ArrayList;

import processing.core.PApplet;
import screen.*;


public class DrawingSurface extends PApplet {
	
	private Screen activeScreen;
	private ArrayList<Screen> screens;
	
	public DrawingSurface()
	{
		screens = new ArrayList<Screen>();
		
		GameScreen screen1 = new GameScreen(this);
		screens.add(screen1);
		
		OptionScreen screen2 = new OptionScreen(this);
		screens.add(screen2);
		
		activeScreen = screens.get(0);
	}
	
	public void setup()
	{
		for (Screen s : screens)
			s.setup();
	}
	
	public void switchScreen(int i)
	{
		activeScreen = screens.get(i);
	}
	
	public void draw()
	{
		push();
		
		activeScreen.draw();
		
		pop();
	}
	
	public void keyPressed()
	{
		
	}

	public void keyReleased()
	{
		
	}
	

	public void mousePressed() {
		activeScreen.mousePressed();
	}
	
	public void mouseMoved() {
		activeScreen.mouseMoved();
	}
	
	public void mouseDragged() {
		activeScreen.mouseDragged();
	}
	
	public void mouseReleased() {
		activeScreen.mouseReleased();
	}

}

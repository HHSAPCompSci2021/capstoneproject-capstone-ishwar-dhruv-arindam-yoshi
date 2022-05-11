package core;
import java.awt.Point;
import java.util.ArrayList;

import processing.core.PApplet;
import screen.*;


public class DrawingSurface extends PApplet {
	
	private int screenIndex;
	private ArrayList<Screen> screens;
	
	public DrawingSurface()
	{
		screens = new ArrayList<Screen>();
		
		GameScreen screen1 = new GameScreen(this);
		screens.add(screen1);
		
		OptionScreen screen2 = new OptionScreen(this);
		screens.add(screen2);
		
		screenIndex = 0;
	}
	
	public void setup()
	{
		for (Screen s : screens)
			s.setup();
	}
	
	public void switchScreen()
	{
		screenIndex = 1 - screenIndex;
	}
	
	public void draw()
	{
		push();
		
		screens.get(screenIndex).draw();
		
		pop();
	}
	
	public void keyPressed()
	{
		
	}

	public void keyReleased()
	{
		
	}
	
	public void mousePressed()
	{
		
	}
	
	public void mouseMoved()
	{
		
	}
	
	public void mouseDragged()
	{
		
	}
	
	public void mouseReleased()
	{
		
	}

}

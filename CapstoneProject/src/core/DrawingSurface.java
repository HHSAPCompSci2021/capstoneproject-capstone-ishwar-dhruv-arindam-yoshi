package core;
import java.awt.Point;
import java.util.ArrayList;

import processing.core.PApplet;
import screen.*;


public class DrawingSurface extends PApplet {
	
	private Screen activeScreen;
	private ArrayList<Screen> screens;
	public static final int GAME = 0, OPTION = 1;
	
	private ArrayList<Integer> keys;
	
	public DrawingSurface()
	{
		screens = new ArrayList<Screen>();
		
		GameScreen screen1 = new GameScreen(this);
		screens.add(screen1);
		
		OptionScreen screen2 = new OptionScreen(this);
		screens.add(screen2);
		
		activeScreen = screens.get(0);
		
		keys = new ArrayList<Integer>();
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
	
	// taken from GamePhysicsDemo
	public void keyPressed() {
		keys.add(keyCode);
		if (key == ESC)  // This prevents a processing program from closing on escape key
			key = 0;
	}

	// taken from GamePhysicsDemo
	public void keyReleased() {
		while(keys.contains(keyCode))
			keys.remove(new Integer(keyCode));
	}

	// taken from GamePhysicsDemo
	public boolean isPressed(Integer code) {
		return keys.contains(code);
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

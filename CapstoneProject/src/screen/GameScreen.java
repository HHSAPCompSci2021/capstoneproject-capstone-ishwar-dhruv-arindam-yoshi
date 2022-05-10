package screen;
import java.util.*;
import java.awt.Point;
import java.awt.Rectangle;

import core.DrawingSurface;
import processing.core.*;
import characters.*;
import items.*;
import game.*;



public class GameScreen extends Screen {
	
	// has a haunted maze object and an info bar object
	
	private HauntedMaze gameSetting;
	private InfoBar bar;

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
	}
}


package screen;
import java.awt.Point;
import java.awt.Rectangle;

import core.DrawingSurface;
import processing.core.*;
import screen.Screen;



public class OptionScreen extends Screen {

	private DrawingSurface surface;
	
	private Rectangle button;

	public OptionScreen(DrawingSurface surface) {
		super(800,600);
		this.surface = surface;

		button = new Rectangle(800/2-100,600/2-50,200,100);
	}


	public void draw() {

		surface.background(255,255,255);
		surface.fill(137, 207, 240);
		surface.rect(button.x, button.y, button.width, button.height, 10, 10, 10, 10);
		surface.fill(0);
		String str = "Play game!";
		float w = surface.textWidth(str);
		surface.text(str, button.x+button.width/2-w/2, button.y+button.height/2);
		
	}



	
	public void mousePressed() {
		Point p = (new Point(surface.mouseX,surface.mouseY));
		if (button.contains(p))
			surface.switchScreen(0);
	}
	

}


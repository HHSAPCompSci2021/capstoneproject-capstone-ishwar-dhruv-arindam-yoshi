package core;
import java.awt.Dimension;

import javax.swing.JFrame;

import processing.awt.PSurfaceAWT;
import processing.core.PApplet;
import screen.Screen;

public class Main {

	public static void main(String args[]) {

		DrawingSurface drawing = new DrawingSurface();
		PApplet.runSketch(new String[]{""}, drawing);
		PSurfaceAWT surf = (PSurfaceAWT) drawing.getSurface();
		PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
		JFrame window = (JFrame)canvas.getFrame();
		
		window.setTitle("Grinch Fringe");
		window.setSize(Screen.DRAWING_WIDTH, Screen.DRAWING_HEIGHT);
		window.setMinimumSize(new Dimension(Screen.DRAWING_WIDTH, Screen.DRAWING_HEIGHT));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);

		window.setVisible(true);
		
		
		canvas.requestFocus();
	}

}

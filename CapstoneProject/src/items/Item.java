package items;
import screen.ScreenObject;
import processing.core.*;
import game.HauntedMaze;
public abstract class Item extends ScreenObject {
	public Item(double x, double y, double width, double height) {
		super(x, y, width, height);
	}
	public abstract void use(HauntedMaze maze);

	public abstract void drawInfo(PApplet marker, int x, int y); 
	
	
}


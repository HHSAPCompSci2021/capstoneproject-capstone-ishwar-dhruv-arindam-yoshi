package items;
import screen.ScreenObject;
import processing.core.*;

public abstract class Item extends ScreenObject {

	public Item(int x, int y, int width, int height)
	{
		super(x, y, width, height);
	}
	
	public abstract Object use();
	
	public abstract void drawInfo(PApplet marker, int x, int y);
}

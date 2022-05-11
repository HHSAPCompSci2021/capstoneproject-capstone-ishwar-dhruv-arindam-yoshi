package items;
import screen.ScreenObject;
import processing.core.*;

public abstract class Item extends ScreenObject {

	public Item()
	{
		super(0, 0, 0, 0);
	}
	
	public abstract Object use();
	
	public abstract void drawInfo(PApplet marker, int x, int y);
}

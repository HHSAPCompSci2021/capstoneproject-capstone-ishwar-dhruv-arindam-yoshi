package items;
import screen.ScreenObject;
import characters.Officer;
import game.HauntedMaze;
import processing.core.*;

public class Trap extends Item {
	
	// has damage intensity

	public Trap(double x, double y, double width, double height)
	{
		super(x, y, width, height);
	}
	
	public void use(HauntedMaze maze)
	{
		Officer o = maze.protagonist; 
		o.changeHealth(-10);
	}
	
	/**
	 * Traps are not visible to the player, so in essense this method is purposeless. 
	 * @param marker
	 * @param x
	 * @param y
	 */
	public void draw(PApplet marker, double x, double y) {
		marker.push();	
		marker.fill(0);
		marker.image(null, (float)x, (float)y, (float)w, (float)h);
		marker.pop();
	}

	@Override
	public void drawInfo(PApplet marker, double x, double y) {
		return; 
		
	}
}

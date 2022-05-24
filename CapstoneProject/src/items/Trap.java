package items;
import screen.ScreenObject;
import characters.Officer;
import game.HauntedMaze;
import processing.core.*;

public class Trap extends Item {
	
	protected PImage image;
	// has damage intensity
	private int type; 
	private boolean isActive = true; 
	
	
	/**
	 * NEAR_DIST is a constant that represents the distance between the trap. 
	 * DEFECT_CHANCE represents the chance the trap "malfunctions" and does not harm the officer. 
	 */
	public static final double NEAR_DIST = 15;
	public static final double DEFECT_CHANCE = 0.01;
	
	// private double timeExposed = ;
	
	/**
	 * @param x the x coord of the trap
	 * @param y the y coord of the trap
	 * @param width the width of the trap
	 * @param height the height of the trap
	 * @param type the type of the trap. There are 4 types of traps. 
	 * Traps of type 1 simply change the health of the officer by 25. 
	 * Traps of type 2 decrease the speed of the officer by 2 units in the X and Y direction. 
	 * Traps of type 3 set the position of the officer back to the start
	 * Traps of type 4 makes the Grinch more powerful(not decided how). 
	 */
	public Trap(PApplet marker, double x, double y, int type)
	{
		super(x, y, 20, 20);
		this.type = type; 
		image = marker.loadImage("assets/trap.png");
	}
	
	/**
	 * Gets the type of the trap
	 * @return
	 */
	public int getType() {
		return type; 
	}
	
	/**
	 * Changes the type of the trap. 
	 * @param newType the type of the new trap. 
	 */
	public void setType(int newType) {
		type = newType; 
	}
	
	/**
	 * Uses the trap once in the maze which is passed
	 * @param the maze which the trap is acted upon. 
	 */
	public void use(HauntedMaze maze)
	{
		if (!isActive)
			return;
		
		Officer o = maze.protagonist; 
		
		double dist = Math.sqrt(Math.pow(o.getX()-x, 2) + Math.pow(o.getY()-y, 2));
		if (dist < NEAR_DIST)
		{
			System.out.println("trapped");
			if (type == 1) {
				o.changeHealth(-25);
			}else if (type == 2) {
				o.setVx(o.getVx()-2);
				o.setVy(o.getVy()-2);
			}else if (type == 3) {
				o.setPos(0, 0);
			}
			setInactive(); 
		}
	}
	
	
	
	/**
	 * returns true if the trap is active, and can still be used. 
	 * @return true if the trap is active. 
	 */
	public boolean isTrapActive() {
		return isActive; 
	}
	
	/**
	 * Sets the trap to inactive, which means it can't be used
	 */
	public void setInactive() {
		isActive = false; 
	}
	
	/**
	 * Traps are not visible to the player, so in essense this method is purposeless. 
	 * @param marker
	 * @param x
	 * @param y
	 */
	public void draw(PApplet marker) {
		double rand = Math.random();
		boolean willDraw = rand < DEFECT_CHANCE;
		willDraw &= isActive;
		
		if (willDraw)
		{
			marker.push();	
			marker.fill(0);
			marker.image(image, (float)x, (float)y, (float)w, (float)h);
			marker.pop();
		}
	}

	@Override
	public void drawInfo(PApplet marker, double x, double y) {
		return; 
		
	}
}

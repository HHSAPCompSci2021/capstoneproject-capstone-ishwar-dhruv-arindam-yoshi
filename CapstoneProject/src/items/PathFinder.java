package items;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

import characters.Officer;
import game.HauntedMaze;
import game.MazeData;
import processing.core.PApplet;

public class PathFinder extends Item{

    public ArrayList<Point> pathToshortest = null;
    
    public PathFinder(double x, double y, double width, double height) {
   	 super(x, y, width, height);
    }

    @Override
    public void use(HauntedMaze maze) {
   	 ArrayList<Item> items = maze.items;
   	 ArrayList<Blueprint> bps = new ArrayList<Blueprint>();
   	 ArrayList<Point> bpcoord = new ArrayList<Point>();
   	 char[][] grid = new char[21][41];
   	 String[][] gridS = maze.settingData.toStringArr();

   	 for (int i=0;i<gridS.length;i++){
   		 grid[i] = gridS[i][0].toCharArray();
   	 }


   	 //FILTERS ITEMS INTO THE BLUEPRINT
   	 for (Item i : items) {
   		 if (i instanceof Blueprint) {
   			 bps.add((Blueprint) i);
   			 bpcoord.add(MazeData.convertToPoint((float) (i.getX()), (float) (i.getY()), (float) maze.getX(), (float) maze.getY(), (float) maze.getW(), (float) maze.getH(), grid, false, -1, -1));
   		 }
   	 }
   	 //DONE FILTERING ITEMS
   	 
   	 //GETS THE COORDINATES OF THE ACTOR
   	 Officer o = maze.protagonist;
   	 Point officerCoord = MazeData.convertToPoint((float) (o.getX()), (float) (o.getY()), (float) maze.getX(), (float) maze.getY(), (float) maze.getW(), (float) maze.getH(), grid, true, o.getVx(), o.getVy());
   	 //END OF COORDINATE OF ACTOR
   	 
   	 
   	 //GETS THE GRID ARRAY WHICH IS THE MAZE IAMGE
   	 
   	 for (int i=0;i<gridS.length;i++){
   		 grid[i] = gridS[i][0].toCharArray();
   	 }
   	 //DONE WITH GRID ARRAY
   	 
   	 //CREATES THE DISTANCE ARRAY WITH BFS

   	 String[][] distGrid = bfs(officerCoord, grid);
   	 //END
   	 
   	 //GETS BLUEPRINT WITH SMALLEST DISTANCE TO OFFICER
   	 Point shortestDP = new Point(0,0);
   	 int shortestDist = Integer.MAX_VALUE;
   	 for (Point p : bpcoord) {
   		  if (distGrid[p.y][p.x] == null) {
   			  continue;
   		  }
   		  int distance = Integer.parseInt(distGrid[p.y][p.x]);
   		  if (distance < shortestDist) {
   			  shortestDist = distance;
   			  shortestDP = p;
   		  }
   	 }
   	 //END
   	 
   	 for (int i=0;i<gridS.length;i++){
   		 grid[i] = gridS[i][0].toCharArray();
   	 }
   	 
    
   	 
   	 //THIS GETS THE SHORTEST PATH
   	 pathToshortest = bfs(officerCoord, shortestDP, grid);
   	 //END
   	 
   	 
   	 //PRINT
//   	 for (int i=0;i<pathToshortest.size();i++) {
//   		 Point curr = pathToshortest.poll();
//   		 System.out.println("X: " + curr.x + "Y: " + curr.y);
//   	 }
   	 //END
   	 
    }
    
    
    private String[][] bfs(Point officer, char[][] grid) {
   	 Queue<Point> points = new LinkedList<>();
   	 Queue<Integer> dist = new LinkedList<>();
   	 points.add(officer);
   	 dist.add(0);
   	 
   	 String[][] finalouput = new String[21][41];
   	 
   	 
   	 while (!points.isEmpty()) {
   		 Point currP = points.poll();
   		 int currdist = dist.poll();
   		 
   		 if (currP.x < 0 || currP.x >= 41 || currP.y < 0 || currP.y >= 21) {
   			 continue;
   		 }
   		 if (grid[currP.y][currP.x] != ' ') {
   			 continue;
   		 }
   		 grid[currP.y][currP.x] = 'V';
   		 finalouput[currP.y][currP.x] = Integer.toString(currdist);
   		 
   		 
   		 points.add(new Point(currP.x-1, currP.y));  dist.add(currdist+1);
   		 points.add(new Point(currP.x+1, currP.y));  dist.add(currdist+1);
   		 points.add(new Point(currP.x, currP.y-1));  dist.add(currdist+1);
   		 points.add(new Point(currP.x, currP.y+1));  dist.add(currdist+1);
   	 }
   	 return finalouput;
    }
    
    private ArrayList<Point> bfs(Point officer, Point bp, char[][] grid) {
		ArrayList<Point> path = new ArrayList<Point>();
    	try {
    		LinkedList<Point> points = new LinkedList<>();
        	
        	Point parent[][] = new Point[30][45];
        	for (int i=0;i<parent.length;i++) {
        		for (int j=0;j<parent[0].length;j++) {
        			parent[i][j] = new Point(0,0); 
        		}
        	}
      
    		points.add(officer);
    		parent[officer.y][officer.x] = new Point(-1, -1);  
    		 
    		 
    		while (!points.isEmpty()) {
    			 Point currP = points.pop();
    			 
    			 
    			 ArrayList<Point> tempPoints = new ArrayList<Point>();  
    			 tempPoints.add(new Point(currP.x-1, currP.y));
    			 tempPoints.add(new Point(currP.x+1, currP.y));
    			 tempPoints.add(new Point(currP.x, currP.y-1));
    			 tempPoints.add(new Point(currP.x, currP.y+1));
    			 
    			 for (Point p : tempPoints) {
    				 if (grid[p.y][p.x] == ' ') {
    					 if (p.x >= 0 && p.x < 41 && p.y >= 0 && p.y < 21) {
    						 grid[p.y][p.x] = 'V';
    						 points.add(p); 
    						 parent[p.y][p.x].x = currP.x; 
    						 parent[p.y][p.x].y = currP.y;
    						 if (p.x == bp.x && p.y == bp.y) {
    							 break; 
    						 }
//    						 parent[p.y][p.x] = currP;  
    					 }
    				 }
    			 }
    		}
    		parent[officer.y][officer.x] = new Point(-1, -1);  

    		for (Point v = bp; parent[v.y][v.x].x != -1 && parent[v.y][v.x].y != -1; v=parent[v.y][v.x]) {
    	        path.add(v);
    	        if (path.size() > 1000)
    	        	break; 
    		}
    	    Collections.reverse(path);
    	    return path; 
    	} catch(Exception e) {
    		path.add(new Point(officer.x,officer.y)); 
    		return path; 
    	}
    	
    }



    @Override
    public void drawInfo(PApplet marker, double x, double y) {
    
    }
    
    public void draw(PApplet marker, double x, double y, double w, double h) {
    	
    try{
    	int lengthMaze = 4 * 10 + 1; 
    	int heightMaze = 2 * 10 + 1;
    	double xLen = w/(lengthMaze-1); 
    	double yLen = h/(heightMaze-1);  	
    	
//    	 marker.line((float)0, (float)0, 500, 500);

    	 marker.push();
    	 marker.strokeWeight(6);
    	 marker.stroke(255, 87, 51);
    	 if (pathToshortest != null && pathToshortest.size() > 0 ) {
    		 Point currP = pathToshortest.remove(0);
    		 while (pathToshortest.size() > 0) {
    			 Point nextcurrP = pathToshortest.remove(0);
    			 marker.line((float) (currP.x * xLen + x), (float) (currP.y * yLen + y), (float) (nextcurrP.x * xLen + x), (float) (nextcurrP.y * yLen + y));
    			 System.out.println("POINTS" + currP.x + " " + currP.y); 
    			 currP = nextcurrP; 
    		 }	
    	 }
       	 marker.pop();
    }catch(Exception e) {
    	return; 
    }
	
   	 
    }

}



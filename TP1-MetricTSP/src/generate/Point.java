package generate;

public class Point {
	
	private int x;
	private int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public double getDistanceTo(Point other) {
		double deltaX = x - other.x;
		double deltaY = y - other.y;
		
		return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
	}
}

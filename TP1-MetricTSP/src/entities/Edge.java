package entities;

public class Edge implements Comparable<Edge> {
	
	private int origin;
	private int destiny;
	private double cost;
	
	public Edge(int origin, int destiny, double cost) {
		this.origin = origin;
		this.destiny = destiny;
		this.cost = cost;
	}
	
	public int origin() {
		return origin;
	}
	
	public int destiny() {
		return destiny;
	}
	
	public double cost() {
		return cost;
	}

	@Override
	public int compareTo(Edge other) {
		
		return (int) (this.cost - other.cost);
		
	}

	
	/**
	 * Compare two edges
	 * Assume this is undirected edge
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Edge))
			return false;
		
		Edge other = (Edge)obj;	
		if(this.cost != other.cost)
			return false;
		if ( ( this.origin != other.origin || this.destiny != other.destiny ) 
				&& (this.origin != other.destiny || this.destiny != other.origin) )
			return false;
		
		return true;
	}
	
}

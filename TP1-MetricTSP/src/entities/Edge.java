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
	public boolean equals(Edge other) {
		if(this.cost == other.cost) {
			if(other.origin == this.origin && other.destiny == this.destiny
					||
					other.destiny == this.origin && other.origin == this.destiny)
				return true;
				
		}
		return false;
	}
	
}

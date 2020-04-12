package entities;

public class Edge implements Comparable<Edge> {
	
	private int origin;
	private int destiny;
	private int cost;
	
	public Edge(int origin, int destiny, int cost) {
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
	
	public int cost() {
		return cost;
	}

	@Override
	public int compareTo(Edge other) {
		return this.cost - other.cost;
	}
	
}

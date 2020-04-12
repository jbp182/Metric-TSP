package graph;

public class Edge implements Comparable<Edge> {
	
	private int destiny;
	private int cost;
	
	public Edge(int destiny, int cost) {
		this.destiny = destiny;
		this.cost = cost;
	}
	
	public int getDestiny() {
		return destiny;
	}
	
	public int getCost() {
		return cost;
	}

	@Override
	public int compareTo(Edge other) {
		return this.cost - other.cost;
	}
	
}

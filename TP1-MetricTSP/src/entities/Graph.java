package entities;

public class Graph {
	
	

	
	private static final int ROOT = 0;
	private static final int MAX_DISTANCE = 1000;

	private double[][] edges;
	private int[] sizes;
	private int numNodes;

	@SuppressWarnings("unchecked")
	public Graph(int numNodes) {
		this.numNodes = numNodes;
	//edges sao initialized as 0 by default
		edges = new double[numNodes][numNodes];
	
	}

	public void addEdge(int origin, int destiny, double cost) {
		edges[origin][destiny] = cost;
		sizes[origin]++;
		
		edges[destiny][origin] = cost;
		sizes[destiny]++;
		
	}

	public int root() {
		return ROOT;
	}

	public int numNodes() {
		return numNodes;
	}

	public double[] incidentEdges(int node) {
		return edges[node].clone();
	}

	public double getEdgeCost(int origin, int destiny) {
		return edges[origin][destiny]; 
	}

	public int nodeDegree(int node) {		
		return sizes[node];
	}
	public boolean isOddDegreeNode(int node) {		
		return (sizes[node] % 2) ==  1;
	}


	public boolean hasEdge(Edge edge) {
		// this method could be better
		int origin = edge.origin();
		int destiny = edge.destiny();
		
		
		return edges[origin][destiny] > 0 || edges[destiny][origin] > 0 ;
	}


	

}

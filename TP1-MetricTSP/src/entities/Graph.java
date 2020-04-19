package entities;


public class Graph {
	private static final int ROOT = 0;

	private double[][] edges;
	private int[] sizes;
	private int numNodes;

	
	public Graph() {
		//nothing
		edges = null;
		sizes = null;
		numNodes =0;
	}
	
	public Graph(int numNodes) {
		this.numNodes = numNodes;
	//edges sao initialized as 0 by default
		edges = new double[numNodes][numNodes];
		sizes = new int[numNodes];
	}

	public void addEdge(int origin, int destiny, double cost) {
		if(edges[origin][destiny] == 0 && cost > 0) {
			sizes[destiny]++;
			sizes[origin]++;
		}
		edges[origin][destiny] = cost;
		edges[destiny][origin] = cost;
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

	public boolean hasEdge(int origin,int destiny) {
		return edges[origin][destiny] > 0 || edges[destiny][origin] > 0 ;
		
	}
	
	public double[][] getMatrix(){
		double[][] mat = new double[numNodes][numNodes];
		mat = edges.clone();
		for(int i =0; i < mat.length;i++) {
			mat[i] = edges[i].clone();
		}
		return mat;
	}
	
	

	

}

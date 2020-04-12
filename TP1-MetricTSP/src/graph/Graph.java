package graph;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Graph {
	
	private static final int ROOT = 1;
	
	private List<Edge>[] edges;
	private int numNodes;
	
	@SuppressWarnings("unchecked")
	public Graph(int numNodes) {
		edges = new List[numNodes];
		this.numNodes = numNodes;
		
		for(int i = 0; i < numNodes; i++)
			edges[i] = new LinkedList<Edge>();
	}
	
	public void addEdge(int origin, int destiny, int cost) {
		edges[origin].add(new Edge(destiny, cost));
		edges[destiny].add(new Edge(origin, cost));
	}
	
	public int getRoot() {
		return ROOT;
	}
	
	public int getNumNodes() {
		return numNodes;
	}
	
	public Iterator<Edge> incidentEdges(int node) {
		return edges[node].iterator();
	}

}

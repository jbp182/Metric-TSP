package entities;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Graph {
	
	private static final int ROOT = 0;
	private static final int MAX_DISTANCE = 1000;
	
	private List<Edge>[] edges;
	private int numNodes;
	
	@SuppressWarnings("unchecked")
	public Graph(int numNodes) {
		edges = new List[numNodes];
		this.numNodes = numNodes;
		
		for(int i = 0; i < numNodes; i++)
			edges[i] = new LinkedList<Edge>();
	}
	
	public void addEdge(int origin, int destiny, double cost) {
		edges[origin].add(new Edge(origin, destiny, cost));
		edges[destiny].add(new Edge(destiny, origin, cost));
	}
	
	public int root() {
		return ROOT;
	}
	
	public int numNodes() {
		return numNodes;
	}
	
	public Iterator<Edge> incidentEdges(int node) {
		return edges[node].iterator();
	}
	
	public double getEdgeCost(int origin, int destiny) {
		Iterator<Edge> it = edges[origin].iterator();
		while(it.hasNext()) {
			Edge e = it.next();
			if (e.destiny() == destiny)
				return e.cost();
		}
		return -1;		// not supposed to happen, because it's a complete graph
	}
	
	
	public int nodeDegree(int node) {
		return this.edges[node].size();
	}
	
	public boolean isOddDegreeNode(int node) {
		return (nodeDegree(node) % 2) == 1;
	}
	
	public boolean hasEdge(Edge edge) {
		//this method could be better
		try {
		edges[edge.origin()].get(edge.destiny()).equals(edge);
		}catch (IndexOutOfBoundsException e) {
			//this means the edge does not exist in this array
			return false;
		}
		return	true;
	}
	
	/*
	 * There is only one edge between two nodes
	 */
	public void generateRandomCompleteGraph() {
		Random random = new Random(); 
		
		int numEdges = this.edges.length;
		for (int i = 0; i < numEdges; i++) {
			int origin = i;
			int destiny = -1;
			int cost = -1;
			for(int j = i+1; j < numEdges;j++) {
					destiny = j;
					cost = random.nextInt(MAX_DISTANCE);
					this.addEdge(origin, destiny, cost);
				
			}

			

		}
	}
	
	public void printEdges() {
		for(List<Edge> el: edges) {
			for(Edge e: el)
				System.out.printf("origin:%d,destiny %d, cost %f \n",e.origin(),e.destiny(),e.cost());
		}
	}


}

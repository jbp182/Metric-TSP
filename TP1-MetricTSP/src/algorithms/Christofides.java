package algorithms;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import entities.Edge;
import entities.Graph;

public class Christofides {
	private Graph originalGraph;
	private Graph mst;

	private int[] finalRoute;

	private List<Integer> circuit;
	
	private double totalCost;
	
	public Christofides(Graph g) {
		this.originalGraph = g;

		Prim p = new Prim(originalGraph);
		mst = p.mstPrimGraph();
		finalRoute = new int[g.numNodes() + 1];
		circuit = new LinkedList<Integer>();
		totalCost = 0;
		
	}

	public void solve() {
		int numNodes = mst.numNodes();
		boolean[] oddNodes = new boolean[mst.numNodes()];		// filled with false, by default
		int oddCount = 0;
		for (int i = 0; i < numNodes; i++) {
			if (mst.isOddDegreeNode(i)) {
				oddNodes[i] = true;
				oddCount++;
			}
		}
		
		
		int[] transf = new int[oddCount];
		int count = 0;
		for (int i = 0; i < numNodes; i++) {
			if (oddNodes[i]) {
				transf[count++] = i;
			}
		}
	
		minPerfectMatching(oddCount, transf);
		makeEulerCircuit();
		makeRoute();
		makeTotalCost();

	}

	private void minPerfectMatching(int oddCount, int[] transf) {
		Queue<Edge>[] edgesSubgraph = findEdgesOfOddNodes(oddCount, transf);
		boolean[] selected = new boolean[oddCount];
		
		for (int i = 0; i < oddCount; i++) {
			while (!selected[i]) {
				Edge e = edgesSubgraph[i].remove();
				if (!selected[e.destiny()]) {
					selected[i] = true;
					selected[e.destiny()] = true;
					double cost = originalGraph.getEdgeCost(transf[i], transf[e.destiny()]);
					mst.addEdge(transf[i], transf[e.destiny()], cost);
				}
			}
		}
		
		
	
	}
	
	// odd nodes are always an even number
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Queue<Edge>[] findEdgesOfOddNodes(int oddCount, int[] transf) {
		Queue[] edges = new Queue[oddCount];
		
		for (int i = 0; i < oddCount; i++) {
			edges[i] = new PriorityQueue<Edge>(oddCount);
			for (int j = i + 1; j < oddCount; j++) {		// copiar arcos. i+1 para nao repetir
				double cost = originalGraph.getEdgeCost(transf[i], transf[j]);
				edges[i].add(new Edge(i,j,cost));
			}
		}
		return edges;
	}

	// Heirholzer's Algorithm
	private void makeEulerCircuit() {
		int numNodes = mst.numNodes();


		int[] numNodesEachLine = new int[numNodes];
		double[][] edges = new double[numNodes][];

		for (int i = 0; i < numNodes; i++) {
			edges[i] = mst.incidentEdges(i);
			numNodesEachLine[i] = mst.nodeDegree(i);
		}


		Deque<Integer> curr_path = new LinkedList<Integer>();

		// vector to store final circuit

		// start from any vertex	
		int lastNode = mst.root(); // Current vertex
		int destiny = 0;
		curr_path.push(lastNode);
		while (!curr_path.isEmpty()) {
			
			if (numNodesEachLine[lastNode] > 0) {
				curr_path.push(lastNode);

				for (destiny = 0; (edges[lastNode][destiny]) <= 0; destiny++)
					;

				edges[lastNode][destiny] = 0;
				numNodesEachLine[lastNode]--;
			
				
				lastNode = destiny;
			}
			else {
				circuit.add(lastNode);
				lastNode = curr_path.pop();
			}
		}
		
	
	}

	public double getTotalCost() {
		
		return totalCost;
	}

	private void makeTotalCost() {
		totalCost = 0;
		int origin = -1;
		int destiny = -1;

		for (int i = 0; i < finalRoute.length - 1; i++) {
			origin = finalRoute[i];

			destiny = finalRoute[i + 1];
			totalCost += originalGraph.getEdgeCost(origin, destiny);
		}
		
	}
	
	private void makeRoute() {
		boolean[] visited = new boolean[mst.numNodes()];
		int i = 0;
		for(int vertex: circuit) {
			if(!visited[vertex]) {
				finalRoute[i++] = vertex;
				visited[vertex] = true;
			}
		}
		
		finalRoute[i] = mst.root();	
		
		
	}
	
	public int[] getWay() {
		return this.finalRoute.clone();
	}
	

}

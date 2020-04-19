package algorithms;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


import entities.Graph;

public class Christofides {
	private Graph originalGraph;
	private Graph mst;

	private int[] finalRoute;

	private List<Integer> circuit;
	
	public Christofides(Graph g) {
		this.originalGraph = g;

		Prim p = new Prim(originalGraph);
		mst = p.mstPrimGraph();
		finalRoute = new int[g.numNodes() + 1];
		circuit = new LinkedList<Integer>();
		

	}

	public void solve() {
		int numNodes = mst.numNodes();
		//Set<Integer> oddNodes = new HashSet<Integer>(numNodes);
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

	}

	private void minPerfectMatching(int oddCount, int[] transf) {
		Graph subGraphOfOddNodes = findSubgraphOfOddNodes(oddCount, transf);
		int[] vertexs = (new HungarianAlgorithm(subGraphOfOddNodes.getMatrix())).execute();
		for (int i = 0; i < vertexs.length; i++) {
			int origin = transf[i];
			int destiny = transf[vertexs[i]];
			double cost = originalGraph.getEdgeCost(origin, destiny);
			mst.addEdge(origin, destiny, cost);
		}
	}

	/*
	Queue<Edge> oddNodesEdges = findEdgeOfOddNodes(mst, oddNodes);
	// the edges are not in mst
	while (!oddNodes.isEmpty()) {
		Edge minEdge = oddNodesEdges.remove();
		int origin = minEdge.origin();
		if (oddNodes.remove(origin) != null) {
			int destiny = minEdge.destiny();
			double cost = minEdge.cost();
			mst.addEdge(origin, destiny, cost);
			oddNodes.remove(destiny);
		}*/
	
	// odd nodes are always an even number
	private Graph findSubgraphOfOddNodes(int oddCount, int[] transf) {
		Graph subgraph = new Graph(oddCount);
		
		for (int i = 0; i < oddCount; i++) {
			// copiar arcos
			// j = i + 1 porque basta metade dos arcos, 
			// a outra metade o Graph ja preenche, sao os simetricos
			for (int j = i + 1; j < oddCount; j++) {
				double cost = originalGraph.getEdgeCost(transf[i], transf[j]);
				subgraph.addEdge(i, j, cost);
			}
		}
		return subgraph;
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
		double sum = 0;
		int origin = -1;
		int destiny = -1;

		for (int i = 0; i < finalRoute.length - 1; i++) {
			origin = finalRoute[i];

			destiny = finalRoute[i + 1];
			sum += originalGraph.getEdgeCost(origin, destiny);
		}
		return sum;
	}

	
	private void makeRoute() {
		Set<Integer> vs = new HashSet<Integer>(mst.numNodes());
		int i = 0;
		for(int vertex: circuit) {
			if(vs.add(vertex)) {
				finalRoute[i++] = vertex;
				}
		}
		
		finalRoute[i] = mst.root();	
		
	}
	
	public int[] getWay() {
		return this.finalRoute.clone();
	}
}

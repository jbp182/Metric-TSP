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
		Set<Integer> oddNodes = new HashSet<Integer>(numNodes);
		for (int i = 0; i < numNodes; i++) {
			if (mst.isOddDegreeNode(i)) {
				oddNodes.add(i);
			}

		}
		makeMinimumPerfeitaMatching(mst, oddNodes);
		makeEulerCircuit();
		makeRoute();

	}

	private void makeMinimumPerfeitaMatching(Graph mst, Set<Integer> oddNodes) {
		Graph subGraphOfOddNodes = findSubgraphOfOddNodes(mst, oddNodes);
		int[] vertexs = (new HungarianAlgorithm(subGraphOfOddNodes.getMatriz())).execute();
		for (int i = 0; i < vertexs.length; i++) {
			int origin = i;
			int destiny = vertexs[i];
			double cost = originalGraph.getEdgeCost(origin, destiny);
			mst.addEdge(origin, destiny, cost);
		}
	}

	// oddnodes.size is always a even number
	private Graph findSubgraphOfOddNodes(Graph mst, Set<Integer> oddNodes) {
		Graph subgraph = new Graph(mst.numNodes());
		for (Integer node : oddNodes) {
			double[] incidentsEdge = originalGraph.incidentEdges(node);
			for (int destiny = 0; destiny < mst.numNodes(); destiny++) {
				double cost = incidentsEdge[destiny];
				// the condition may not matter matter the result
				if (cost > 0 && oddNodes.contains(destiny)) {
					subgraph.addEdge(node, destiny, cost);
				}
			}
		}

		return subgraph;

	}

	// Heirholzer's Algorithm

	private void makeEulerCircuit() {
		int numNodes = mst.numNodes();


		int[] edge_count = new int[numNodes];
		double[][] edges = new double[numNodes][];

		for (int i = 0; i < numNodes; i++) {
			edges[i] = mst.incidentEdges(i);
			edge_count[i] = mst.nodeDegree(i);
		}


		Deque<Integer> curr_path = new LinkedList<Integer>();

		// vector to store final circuit

		// start from any vertex
		curr_path.push(0);
		int curr_v = 0; // Current vertex
		int destiny = 0;
		while (!curr_path.isEmpty()) {
			
			// If there's remaining edge
			if (edge_count[curr_v] > 0) {
				// Push the vertex
				curr_path.push(curr_v);

				for (destiny = 0; (edges[curr_v][destiny]) <= 0; destiny++)
					;

				// Find the next vertex using an edge
				edges[curr_v][destiny] = 0;
				// and remove that edge
				edge_count[curr_v]--;

				// Move to next vertex
				curr_v = destiny;
			}

			// back-track to find remaining circuit
			else {
				circuit.add(curr_v);
				// Back-tracking
				curr_v = curr_path.pop();
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

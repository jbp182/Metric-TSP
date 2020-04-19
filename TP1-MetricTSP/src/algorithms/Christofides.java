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
		minPerfectMatching(oddNodes);
		makeEulerCircuit();
		makeRoute();

	}

	private void minPerfectMatching(Set<Integer> oddNodes) {
		Graph subGraphOfOddNodes = findSubgraphOfOddNodes(oddNodes);
		int[] vertexs = (new HungarianAlgorithm(subGraphOfOddNodes.getMatriz())).execute();
		for (int i = 0; i < vertexs.length; i++) {
			int origin = i;
			int destiny = vertexs[i];
			double cost = originalGraph.getEdgeCost(origin, destiny);
			mst.addEdge(origin, destiny, cost);
		}
	}

	// oddnodes.size is always a even number
	private Graph findSubgraphOfOddNodes(Set<Integer> oddNodes) {
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
			
			// If there's remaining edge
			if (numNodesEachLine[lastNode] > 0) {
				// Push the vertex
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

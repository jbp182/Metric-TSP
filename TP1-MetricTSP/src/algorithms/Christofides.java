package algorithms;

import java.util.HashSet;
import java.util.Set;

import javax.print.attribute.standard.Destination;

import entities.Graph;

public class Christofides {
	private Graph originalGraph;
	private Graph mst;

	private int[] finalRoute;

	public Christofides(Graph g) {
		this.originalGraph = g;

		Prim p = new Prim(originalGraph);
		mst = p.mstPrimGraph();
		finalRoute = new int[g.numNodes() + 1];

	}

	public Graph solve() {
		int numNodes = mst.numNodes();
		Map<Integer, Integer> oddNodes = new HashMap<Integer, Integer>(numNodes);
		for (int i = 0; i < numNodes; i++) {
			if (mst.isOddDegreeNode(i)) {
				oddNodes.put(i, 1);
			}

		}
		makePerfeitaMatching(mst, oddNodes);
		makeEulerCircuit();
		return mst;

	}

<<<<<<< Updated upstream
	private void makePerfeitaMatching(Graph mst, Map<Integer, Integer> oddNodes) {
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
			}
=======
	
	private void makeMinimumPerfeitaMatching(Graph mst, Set<Integer> oddNodes) {
		Graph subGraphOfOddNodes  = findSubgraphOfOddNodes(mst, oddNodes);
		int[] vertexs = (new HungarianAlgorithm(subGraphOfOddNodes.getMatriz())).execute();
		for (int i = 0; i < vertexs.length; i++) {
			int origin = i;
			int destiny = vertexs[i];
			double cost = originalGraph.getEdgeCost(origin, destiny);
			mst.addEdge(origin,destiny, cost);
>>>>>>> Stashed changes
		}
	}

	// oddnodes.size is always a even number
	private Queue<Edge> findEdgeOfOddNodes(Graph mst, Map<Integer, Integer> oddNodes) {
		Queue<Edge> edges = new PriorityQueue<Edge>(); // todo: here should have a initial size
		for (Integer node : oddNodes.keySet()) {
			double[] incidentsEdge = originalGraph.incidentEdges(node);
			for (int destiny = 0; destiny < mst.numNodes(); destiny++) {
				double cost = incidentsEdge[destiny];
				// the condition may not matter matter the result
				if (cost > 0 && oddNodes.containsKey(destiny)) {
					edges.add(new Edge(node, destiny, cost));
				}
			}
		}

		return subgraph;

	}

	// Heirholzer's Algorithm
	private void makeEulerCircuit() {
		
		int currentSize = 0;
		int lastNode = mst.root();
		int numNodes = mst.numNodes();
		finalRoute[currentSize++] = lastNode;
		//0,......,0
		//0,......,numNodes
		//1,......,numNodes+1
		
		finalRoute[numNodes] = lastNode;
		
		
		Set<Integer> nodes = new HashSet<Integer>();

		int[] numEdgesForEachNode = new int[numNodes];
		double[][] edges = new double[numNodes][];

		for (int i = 0; i < numNodes; i++) {
			edges[i] = mst.incidentEdges(i);
			numEdgesForEachNode[i] = mst.nodeDegree(i);
		}
		int destiny = 0;
		nodes.add(lastNode);
		while (currentSize < numNodes) {
			if (numEdgesForEachNode[lastNode] > 0) {
				double cost;
				destiny = 0;
				for(; (cost = edges[lastNode][destiny]) <= 0 ; destiny++);
				
				if (nodes.contains(destiny)) {
					// it is added to array
				} else {
					finalRoute[currentSize++] = destiny;
					
				}
				
				numEdgesForEachNode[lastNode]--;
				edges[lastNode][destiny] = 0;

				lastNode = destiny;
			} else {
				//impossible
				if (nodes.contains(destiny)) {
					// it is added to array
				} else {
					//finalRoute[currentSize++] = destiny;
					//the graph is connected so, here is unachievable
				}
				// Back-tracking
				while(numEdgesForEachNode[lastNode] <= 0)
					lastNode = (lastNode +1) % numNodes; 
			}
		}


	}

	public double getTotalCost() {
		double sum = 0;
		int origin =-1;
		int destiny =-1;
		
		for (int i = 0; i < finalRoute.length-1; i++) {
			origin =finalRoute[i];

			destiny =finalRoute[i+1];
			sum += originalGraph.getEdgeCost(origin, destiny);
		}
		return sum;
	}

	public int[] getWay() {
		return this.finalRoute.clone();
	}
}

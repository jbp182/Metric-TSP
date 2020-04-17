package algorithms;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import entities.Edge;
import entities.Graph;

public class Christofides {
	private Graph originalGraph;

	private int totalCost;

	public Christofides(Graph g) {
		this.originalGraph = g;

	}
	
	

	public Graph solve() {
		PrimGraph p = new PrimGraph(originalGraph);
		p.makeMstPrim();
		Graph mst = p.getMST();
		int numNodes = mst.numNodes();
		Map<Integer, Integer> oddNodes = new HashMap<Integer, Integer>(numNodes);
		for (int i = 0; i < numNodes; i++) {
			if (mst.isOddDegreeNode(i)) {
				oddNodes.put(i, 1);
			}
			
		}
		makePerfeitaMatching(mst, oddNodes);
		
		return mst;
		
	}

	private void makePerfeitaMatching(Graph mst, Map<Integer, Integer> oddNodes) {
		Queue<Edge> oddNodesEdges = findEdgeNotInMST(mst, oddNodes);
		// the edges are not in mst
		while (!oddNodes.isEmpty()) {
			Edge minEdge = oddNodesEdges.remove();
			int origin = minEdge.origin();
			if (oddNodes.remove(origin) != null) {
				int destiny = minEdge.destiny();
				int cost = minEdge.cost();
				mst.addEdge(origin, destiny, cost);
				oddNodes.remove(destiny);
			}
		}
	}

	// oddnodes.size is always a even number
	private Queue<Edge> findEdgeNotInMST(Graph mst, Map<Integer, Integer> oddNodes) {
		Queue<Edge> edges = new PriorityQueue<Edge>(); // todo: here should have a initial size
		for (Integer node : oddNodes.keySet()) {
			Iterator<Edge> it = originalGraph.incidentEdges(node);
			while (it.hasNext()) {
				Edge next = it.next();
				if (!mst.hasEdge(next) && oddNodes.containsKey((next.destiny()))) {
					edges.add(next);
				}
			}
		}

		return edges;

	}

	public int getTotalCost() {
		return totalCost;
	}

}

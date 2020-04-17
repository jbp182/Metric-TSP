package algorithms;

import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;

import entities.Edge;
import entities.Graph;

public class PrimGraph {
	
	private Graph graphOriginal;
	private Graph mstTree;
	private int numNodes;
	
	private boolean[] selected;
	private int[] cost;
	private Edge[] via;
	private Queue<Edge> connected;
	
	public PrimGraph(Graph graph) {
		this.graphOriginal = graph;
		numNodes = graph.numNodes();
		mstTree = new Graph(numNodes);
		
		selected = new boolean[numNodes];
		cost = new int[numNodes];
		via = new Edge[numNodes];
		connected = new PriorityQueue<Edge>(numNodes);
		
	}
	
	
	public Graph getMST() {
		return this.mstTree;
	}
	
	public void makeMstPrim() {
	
		for (int i = 0; i < numNodes; i++) {
			selected[i] = false;
			cost[i] = Integer.MAX_VALUE;
		}
		
		int origin = graphOriginal.root();
		cost[origin] = 0;
		connected.add(new Edge(origin, origin, 0));
		
		do {
			int node = connected.remove().destiny();
			if (!selected[node]) {
				selected[node] = true;
				if (node != origin) {
					Edge viaEdge = via[node];
					int from = viaEdge.origin();
					int to = viaEdge.destiny();
					int cost = viaEdge.cost();
					mstTree.addEdge(from,to,cost);
				}
				exploreNode(node);
			}
		} while (!connected.isEmpty());
		
	}

	private void exploreNode(int source) {
		Iterator<Edge> it = graphOriginal.incidentEdges(source);
		while (it.hasNext()) {
			Edge e = it.next();
			int node = e.destiny();
			if (!selected[node] && e.cost() < cost[node]) {
				cost[node] = e.cost();
				via[node] = e;
				connected.add(e);
			}
		}
	}

}
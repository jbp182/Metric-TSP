package algorithms;

import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;

import entities.Edge;
import entities.Graph;
import entities.Node;

public class Prim {
	
	private Graph graph;
	private int numNodes;
	
	private boolean[] selected;
	private int[] cost;
	private Edge[] via;
	private Queue<Edge> connected;
	
	private Node[] nodes;
	
	public Prim(Graph graph) {
		this.graph = graph;
		numNodes = graph.numNodes();
		
		selected = new boolean[numNodes];
		cost = new int[numNodes];
		via = new Edge[numNodes];
		connected = new PriorityQueue<Edge>(numNodes);
		
		nodes = new Node[numNodes];
		for (int i = 0; i < numNodes; i++)
			nodes[i] = null;
	}
	
	public Node mstPrim() {
		
		for (int i = 0; i < numNodes; i++) {
			selected[i] = false;
			cost[i] = Integer.MAX_VALUE;
		}
		
		int origin = graph.root();
		nodes[origin] = new Node(0, null);
		cost[origin] = 0;
		connected.add(new Edge(origin, origin, 0));
		
		do {
			int node = connected.remove().destiny();
			if (!selected[node]) {
				selected[node] = true;
				if (node != origin) {
					Edge viaEdge = via[node];
					int from = viaEdge.origin();
					int cost = viaEdge.cost();
					Node n = new Node(cost, nodes[from]);
					nodes[from].addChild(n);
					nodes[node] = n;
				}
				exploreNode(node);
			}
		} while (!connected.isEmpty());
		
		return nodes[origin];
	}

	private void exploreNode(int source) {
		Iterator<Edge> it = graph.incidentEdges(source);
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

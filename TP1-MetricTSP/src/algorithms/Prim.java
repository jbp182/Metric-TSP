package algorithms;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import graph.Edge;
import graph.Graph;
import tree.Node;
import tree.Tree;

public class Prim {
	
	private Graph graph;
	private int numNodes;
	
	private Edge[] mst;
	private int mstSize;
	private boolean[] selected;
	private int[] cost;
	private Edge[] via;
	private Queue<Edge> connected;
	
	public Prim(Graph graph) {
		this.graph = graph;
		numNodes = graph.numNodes();
		
		mst = new Edge[numNodes - 1];
		mstSize = 0;
		selected = new boolean[numNodes];
		cost = new int[numNodes];
		via = new Edge[numNodes];
		connected = new PriorityQueue<Edge>(numNodes);
	}
	
	public Tree mstPrim() {
		
		for (int i = 0; i < numNodes; i++) {
			selected[i] = false;
			cost[i] = Integer.MAX_VALUE;
		}
		
		int origin = graph.getRoot();
		cost[origin] = 0;
		connected.add(new Edge(origin, origin, 0));
		
		do {
			int node = connected.remove().destiny();
			if (!selected[node]) {
				selected[node] = true;
				if (node != origin)
					mst[mstSize++] = via[node];
				exploreNode(node);
			}
		} while (!connected.isEmpty());
		
		//return mst;
		return mstTree();
	}

	private Tree mstTree() {
		Node root = new Node(0, null);
		Node n = root;
		for(int i = 0; i < mstSize; i++) {
			Edge e = mst[i];
//			n.addChild(new Node());
		}
		
		
		return null;
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

package algorithms;

import java.util.PriorityQueue;
import java.util.Queue;

import entities.Edge;
import entities.Graph;
import entities.TreeNode;

public class Prim {
	
	private Graph graph;
	private int numNodes;
	
	private boolean[] selected;
	private double[] cost;
	private Edge[] via;
	private Queue<Edge> connected;
	
	
	public Prim(Graph graph) {
		this.graph = graph;
		numNodes = graph.numNodes();
		
		selected = new boolean[numNodes];
		cost = new double[numNodes];
		via = new Edge[numNodes];
		connected = new PriorityQueue<Edge>(numNodes);
		
	}
	
	public TreeNode mstPrimTree() {

		TreeNode[] nodes = new TreeNode[numNodes];
		for (int i = 0; i < numNodes; i++) {
			selected[i] = false;
			cost[i] = Double.MAX_VALUE;
		}
		
		int origin = graph.root();
		nodes[origin] = new TreeNode(origin, 0, null);
		cost[origin] = 0;
		connected.add(new Edge(origin, origin, 0));
		
		do {
			int node = connected.remove().destiny();
			if (!selected[node]) {
				selected[node] = true;
				if (node != origin) {
					Edge viaEdge = via[node];
					int from = viaEdge.origin();
					double cost = viaEdge.cost();
					TreeNode n = new TreeNode(node, cost, nodes[from]);
					nodes[from].addChild(n);
					nodes[node] = n;
				}
				exploreNode(node);
			}
		} while (!connected.isEmpty());
		
		return nodes[origin];
	}

	private void exploreNode(int source) {
		double[] edges = graph.incidentEdges(source);
		for(int node = 0; node < numNodes;node++) {
			double value = edges[node];
			if(value > 0)
			if (!selected[node] && value < cost[node]) {
				cost[node] = value;
				Edge e =new Edge(source, node, value);
				via[node] = e;
				connected.add(e);
			}
		}
	}
	
	public Graph mstPrimGraph() {
		Graph mstTree = new Graph(numNodes);
		
		for (int i = 0; i < numNodes; i++) {
			selected[i] = false;
			cost[i] = Double.MAX_VALUE;
		}
		
		int origin = graph.root();
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
					double cost = viaEdge.cost();
					mstTree.addEdge(from,to,cost);
				}
				exploreNode(node);
			}
		} while (!connected.isEmpty());
		return mstTree;
	}

}

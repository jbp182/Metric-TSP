package algorithms;

import java.util.Iterator;

import entities.Graph;
import entities.Tree;
import entities.TreeNode;

public class GreedyTSP {
	
	private Graph graph;
	private Iterator<Integer> preorder;
	private int totalCost;
	
	public GreedyTSP(Graph g) {
		this.graph = g;
	}
	
	public void solve() {
		Prim p = new Prim(graph);
		TreeNode mst = p.mstPrim();
		
		Tree t = new Tree(mst);
		preorder = t.preorderTraversal();
		totalCost = t.getPreorderCost();
	}

	public Iterator<Integer> preorderTraversal() {
		return preorder;
	}
	
	public int getTotalCost() {
		return totalCost;
	}

}
